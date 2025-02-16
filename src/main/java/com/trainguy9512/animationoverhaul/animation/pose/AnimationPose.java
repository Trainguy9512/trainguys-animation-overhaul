package com.trainguy9512.animationoverhaul.animation.pose;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.joint.JointTransform;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.*;

import java.util.*;

public class AnimationPose {

    private final JointSkeleton jointSkeleton;
    private final HashMap<String, JointTransform> jointTransforms;
    private PoseSpace poseSpace;

    private AnimationPose(JointSkeleton jointSkeleton){
        this.jointSkeleton = jointSkeleton;
        this.jointTransforms = Maps.newHashMap();
        this.poseSpace = PoseSpace.LOCAL;

        for(String joint : jointSkeleton.getJoints()){
            this.setJointTransform(joint, JointTransform.ZERO);
        }
    }

    public AnimationPose(AnimationPose animationPose){
        this.jointSkeleton = animationPose.jointSkeleton;
        this.jointTransforms = new HashMap<>(animationPose.jointTransforms);
        this.poseSpace = animationPose.poseSpace;
    }

    /**
     * Creates an animation pose from a point in time within the provided animation sequence
     * @param jointSkeleton Template jointe skeleton
     * @param resourceLocation Animation sequence resource location
     * @param time Point of time in animation sequence
     * @return Animation pose
     */
    public static AnimationPose fromAnimationSequence(JointSkeleton jointSkeleton, ResourceLocation resourceLocation, float time){
        AnimationPose animationPose = AnimationPose.of(jointSkeleton);
        for(String joint : jointSkeleton.getJoints()){
            animationPose.setJointTransform(joint, JointTransform.ofAnimationSequenceJoint(resourceLocation, joint, time));
        }
        return animationPose;
    }

    /**
     * Creates a blank animation pose using a joint skeleton as the template.
     * @param jointSkeleton Template joint skeleton
     * @return New animation pose
     */
    public static AnimationPose of(JointSkeleton jointSkeleton){
        return new AnimationPose(jointSkeleton);
    }

    /**
     * Retrieves the animation pose's skeleton.
     * @return Joint skeleton
     */
    public JointSkeleton getJointSkeleton(){
        return this.jointSkeleton;
    }

    /**
     * Retrieves this pose's current pose space
     * @return Pose space
     */
    public PoseSpace getPoseSpace(){
        return this.poseSpace;
    }

    /**
     * Sets this pose's current pose space to the provided value.
     * @param poseSpace New pose space
     */
    private void setPoseSpace(PoseSpace poseSpace){
        this.poseSpace = poseSpace;
    }

    /**
     * Sets the transform for the supplied joint by its string identifier.
     * @param joint Joint string identifier
     * @param jointPose Joint transform
     */
    public void setJointTransform(String joint, JointTransform jointPose){
        if(this.jointSkeleton.containsJoint(joint)){
            this.jointTransforms.put(joint, jointPose);
        }
    }

    /**
     * Retrieves a copy of the transform for the supplied joint.
     * @param joint Joint string identifier
     * @return Joint transform
     */
    public JointTransform getJointTransform(String joint){
        return JointTransform.of(this.jointTransforms.getOrDefault(joint, JointTransform.ZERO));
    }

    /**
     * If this pose is in local space, returns a new pose converted to entity space.
     */
    public AnimationPose convertedToEntitySpace(){
        if(this.poseSpace == PoseSpace.LOCAL){
            AnimationPose animationPose = new AnimationPose(this);
            animationPose.convertChildrenJointsToEntitySpace(this.getJointSkeleton().getRootJoint(), new PoseStack(), this.getJointSkeleton().getJoints());
            animationPose.setPoseSpace(PoseSpace.ENTITY);
            return animationPose;
        }
        return this;
    }

    private void convertChildrenJointsToEntitySpace(String parent, PoseStack poseStack, Set<String> jointsToConvert){
        JointTransform localParentJointPose = JointTransform.of(this.getJointTransform(parent));

        poseStack.pushPose();

        poseStack.mulPose(localParentJointPose.composeMatrix());

        for (String child : this.getJointSkeleton().getDirectChildrenOfJoint(parent)){
            this.convertChildrenJointsToEntitySpace(child, poseStack, jointsToConvert);
        }

        for(String joint : jointsToConvert){
            if((this.getJointSkeleton().jointIsParentOfChild(parent, joint) || jointsToConvert.contains(parent))){
                this.setJointTransform(parent, JointTransform.of(new Matrix4f(poseStack.last().pose())));
                break;
            }
        }
        poseStack.popPose();
    }

    /**
     * If this pose is in entity space, returns a new pose converted to local space.
     */
    public AnimationPose convertedToLocalSpace(){
        if(this.poseSpace == PoseSpace.ENTITY){
            AnimationPose animationPose = new AnimationPose(this);
            animationPose.convertChildrenJointsToLocalSpace(this.getJointSkeleton().getRootJoint(), new Matrix4f(), this.getJointSkeleton().getJoints());
            animationPose.setPoseSpace(PoseSpace.LOCAL);
            return animationPose;
        }
        return this;
    }

    private void convertChildrenJointsToLocalSpace(String parent, Matrix4f parentMatrix, Set<String> jointsToConvert){
        JointTransform parentJointPose = this.getJointTransform(parent);

        for (String child : this.getJointSkeleton().getDirectChildrenOfJoint(parent)){
            this.convertChildrenJointsToLocalSpace(child, parentJointPose.composeMatrix(), jointsToConvert);
        }

        if(jointsToConvert.contains(parent)){
            parentJointPose.multiply(parentMatrix.invert(), JointTransform.TransformSpace.LOCAL);
            this.setJointTransform(parent, parentJointPose);
        }
    }

    /**
     * Returns a new animation pose interpolated between this pose and the provided pose.
     * @param other     Animation pose to interpolate to.
     * @param weight    Weight value, 0 is the original pose and 1 is the other pose.
     * @return          New interpolated animation pose.
     */
    public AnimationPose interpolated(AnimationPose other, float weight){
        return interpolatedFilteredByJoints(other, weight, this.jointSkeleton.getJoints());
    }

    /**
     * Returns a new animation pose interpolated between this pose and the provided pose, only on the specified joints.
     * @param other     Animation pose to interpolate to.
     * @param weight    Weight value, 0 is the original pose and 1 is the other pose.
     * @param joints    Set of joints to interpolate.
     * @return          New interpolated animation pose.
     */
    public AnimationPose interpolatedFilteredByJoints(AnimationPose other, float weight, Set<String> joints){
        // If the weight is 0, don't interpolate anything and just return this.
        if(weight == 0){
            return this;
        }
        // TODO: Ensure pose samplers interpolate poses correctly to preserve pose spaces.
        // Convert the other pose's pose space to match this pose's pose space.
        AnimationPose otherConverted = this.poseSpace == other.poseSpace ? other :
                this.poseSpace == PoseSpace.ENTITY ? other.convertedToEntitySpace() :
                        other.convertedToLocalSpace();

        AnimationPose interpolatedAnimationPose = new AnimationPose(this);
        joints.stream()
                .filter(joint -> this.getJointSkeleton().containsJoint(joint))
                .forEach(joint -> interpolatedAnimationPose.setJointTransform(joint,
                        weight == 1 ? otherConverted.getJointTransform(joint) :
                                this.getJointTransform(joint).interpolated(other.getJointTransform(joint), weight))
                );
        return interpolatedAnimationPose;
    }

    /**
     * Returns a new animation pose with the other pose filtered by the selected joints.
     * @param other     Animation pose to filter.
     * @param joints    Set of joints to filter by.
     * @return          New filtered animation pose.
     */
    public AnimationPose filteredByJoints(AnimationPose other, Set<String> joints){
        return interpolatedFilteredByJoints(other, 1, joints);
    }

    public AnimationPose jointTranslated(String joint, Vector3f translation, JointTransform.TransformSpace transformSpace, boolean additive){
        AnimationPose animationPose = switch(transformSpace){
            case ENTITY, PARENT -> this.convertedToEntitySpace();
            case LOCAL -> this.convertedToLocalSpace();
        };

        JointTransform jointTransform = animationPose.getJointTransform(joint);
        if(additive){
            jointTransform.translate(translation, transformSpace);
        } else {
            jointTransform.setTranslation(translation);
        }
        animationPose.setJointTransform(joint, jointTransform);

        return animationPose.convertedToLocalSpace();
    }

    public AnimationPose jointRotated(String joint, Vector3f rotationXYZ, JointTransform.TransformSpace transformSpace, boolean additive){
        AnimationPose animationPose = switch(transformSpace){
            case ENTITY, PARENT -> this.convertedToEntitySpace();
            case LOCAL -> this.convertedToLocalSpace();
        };

        JointTransform jointTransform = animationPose.getJointTransform(joint);
        if(additive){
            jointTransform.rotate(rotationXYZ, transformSpace);
        } else {
            jointTransform.setRotation(rotationXYZ);
        }

        return animationPose.convertedToLocalSpace();
    }



    public AnimationPose mirrored(){
        return this.mirroredWeighted(1);
    }

    public AnimationPose mirroredWeighted(float weight){
        // Pose mirroring should only occur on local space poses. If the pose is in entity space, have it be converted
        AnimationPose localSpaceCurrentPose = this.convertedToLocalSpace();
        AnimationPose mirroredPose = AnimationPose.of(this.jointSkeleton);
        this.jointTransforms.forEach((joint, transform) -> {

        });
        for(String joint : this.getJointSkeleton().getJoints()){
            JointTransform jointTransform = localSpaceCurrentPose.getJointTransform(joint);
            JointTransform mirroredJointTransform = localSpaceCurrentPose.getJointTransform(this.getJointSkeleton().getJointConfiguration(joint).mirrorJoint()).mirrored();
            mirroredPose.setJointTransform(joint, jointTransform.interpolated(mirroredJointTransform, weight));
        }
        return mirroredPose;
    }

    public enum PoseSpace {
        ENTITY,
        LOCAL
    }
}
