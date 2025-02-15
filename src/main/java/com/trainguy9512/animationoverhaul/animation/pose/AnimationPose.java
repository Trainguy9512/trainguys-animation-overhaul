package com.trainguy9512.animationoverhaul.animation.pose;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.joint.JointTransform;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

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
        this.jointTransforms.put(joint, jointPose);
    }

    /**
     * Retrieves a copy of the transform for the supplied joint.
     * @param joint Joint string identifier
     * @return Joint transform
     */
    public JointTransform getJointTransform(String joint){
        return new JointTransform(this.jointTransforms.getOrDefault(joint, JointTransform.ZERO));
    }

    @Deprecated
    public JointTransform getJointPoseReference(String joint){
        return this.jointTransforms.getOrDefault(joint, JointTransform.ZERO);
    }

    /*
    public void subtractPose(AnimationPose animationPose){
        for(LocatorSkeleton.LocatorEntry locatorEntry : animationPose.getSkeleton().getLocatorEntries()){
            this.setLocatorPose(locatorEntry.getLocatorIdentifier(), MutablePartPose);
        }
    }

     */




    public AnimationPose getConvertedToEntitySpace(){
        if(this.getPoseSpace() == PoseSpace.LOCAL){
            AnimationPose animationPose = new AnimationPose(this);
            animationPose.convertChildrenJointsToEntitySpace(this.getJointSkeleton().getRootJoint(), new PoseStack(), this.getJointSkeleton().getJoints());
            animationPose.setPoseSpace(PoseSpace.ENTITY);
            return animationPose;
        }
        return this;
    }

    private void convertChildrenJointsToEntitySpace(String parent, PoseStack poseStack, Set<String> jointsToConvert){
        JointTransform localParentJointPose = new JointTransform(this.getJointTransform(parent));

        poseStack.pushPose();

        poseStack.mulPose(localParentJointPose.getTransform());

        for (String child : this.getJointSkeleton().getDirectChildrenOfJoint(parent)){
            convertChildrenJointsToEntitySpace(child, poseStack, jointsToConvert);
        }

        for(String joint : jointsToConvert){
            if((this.getJointSkeleton().jointIsParentOfChild(parent, joint) || jointsToConvert.contains(parent))){
                this.setJointTransform(parent, localParentJointPose.setTransform(new Matrix4f(poseStack.last().pose())));
                break;
            }
        }
        poseStack.popPose();
    }

    public AnimationPose getConvertedToLocalSpace(){
        if(this.getPoseSpace() == PoseSpace.ENTITY){
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
            convertChildrenJointsToLocalSpace(child, parentJointPose.getTransform(), jointsToConvert);
        }

        if(jointsToConvert.contains(parent)){
            parentJointPose.multipliedBy(parentMatrix.invert(), JointTransform.TransformSpace.LOCAL);
            this.setJointTransform(parent, parentJointPose);
        }
    }

    public void blend(AnimationPose animationPose, float alpha, Easing easing){
        for(String joint : this.getJointSkeleton().getJoints()){
            JointTransform jointPoseA = this.getJointTransform(joint);
            JointTransform jointPoseB = animationPose.getJointTransform(joint);
            this.setJointTransform(joint, jointPoseA.interpolated(jointPoseB, alpha, easing));
        }
    }

    public void blendLinear(AnimationPose animationPose, float alpha){
        this.blend(animationPose, alpha, Easing.LINEAR);
    }

    public AnimationPose getBlended(AnimationPose animationPose, float alpha, Easing easing){
        AnimationPose newAnimationPose = new AnimationPose(this);
        newAnimationPose.blend(animationPose, alpha, easing);
        return newAnimationPose;
    }

    public AnimationPose getBlendedLinear(AnimationPose animationPose, float alpha){
        return this.getBlended(animationPose, alpha, Easing.LINEAR);
    }

    public void blendByJoints(AnimationPose animationPose, @NotNull List<String> joints, float alpha, Easing easing){
        for(String joint : joints){
            JointTransform jointPoseA = this.getJointTransform(joint);
            JointTransform jointPoseB = animationPose.getJointTransform(joint);
            this.setJointTransform(joint, jointPoseA.interpolated(jointPoseB, alpha, easing));
        }
    }

    public AnimationPose getBlendedByJoints(AnimationPose animationPose, @NotNull List<String> joints, float alpha, Easing easing){
        AnimationPose newAnimationPose = new AnimationPose(this);
        newAnimationPose.blendByJoints(animationPose, joints, alpha, easing);
        return newAnimationPose;
    }

    public AnimationPose getBlendedByJointsLinear(AnimationPose animationPose, List<String> joints, float alpha){
        return this.getBlendedByJoints(animationPose, joints, alpha, Easing.LINEAR);
    }

    public AnimationPose getSelectedByJoints(AnimationPose animationPose, List<String> joints){
        return this.getBlendedByJointsLinear(animationPose, joints, 1);
    }

    public void inverseMultiply(AnimationPose animationPose){
        for(String joint : this.getJointSkeleton().getJoints()){
            JointTransform jointTransform = this.getJointTransform(joint);
            jointTransform.inverseMultipliedBy(animationPose.getJointTransform(joint));
            this.setJointTransform(joint, jointTransform);
        }
    }

    public AnimationPose getInverseMultiplied(AnimationPose animationPose){
        AnimationPose newAnimationPose = new AnimationPose(this);
        newAnimationPose.inverseMultiply(animationPose);
        return newAnimationPose;
    }

    public void multiply(AnimationPose animationPose){
        for(String joint : this.getJointSkeleton().getJoints()){
            JointTransform jointTransform = this.getJointTransform(joint);
            jointTransform.multipliedBy(animationPose.getJointTransform(joint));
            this.setJointTransform(joint, jointTransform);
        }
    }

    public AnimationPose getMultiplied(AnimationPose animationPose){
        AnimationPose newAnimationPose = new AnimationPose(this);
        newAnimationPose.multiply(animationPose);
        return newAnimationPose;
    }



    public AnimationPose translateJoint(String joint, Vector3f translation, JointTransform.TransformSpace transformSpace, boolean additive){
        AnimationPose animationPose = switch(transformSpace){
            case ENTITY, PARENT -> this.getConvertedToEntitySpace();
            case LOCAL -> this.getConvertedToLocalSpace();
        };

        JointTransform jointTransform = animationPose.getJointTransform(joint);
        if(additive){
            jointTransform.translated(translation, transformSpace);
        } else {
            jointTransform.withTranslation(translation);
        }
        animationPose.setJointTransform(joint, jointTransform);

        return animationPose.getConvertedToLocalSpace();
    }

    public AnimationPose rotateJoint(String joint, Vector3f rotationXYZ, JointTransform.TransformSpace transformSpace, boolean additive){
        AnimationPose animationPose = switch(transformSpace){
            case ENTITY, PARENT -> this.getConvertedToEntitySpace();
            case LOCAL -> this.getConvertedToLocalSpace();
        };

        JointTransform jointTransform = animationPose.getJointTransform(joint);
        if(additive){
            jointTransform.rotated(rotationXYZ, transformSpace);
        } else {
            jointTransform.withRotation(rotationXYZ);
        }

        return animationPose.getConvertedToLocalSpace();
    }



    public void mirror(){
        this.mirrorBlended(1);
    }

    public void mirrorBlended(float alpha){
        HashMap<String, JointTransform> mirroredPose = Maps.newHashMap();
        for(String joint : this.getJointSkeleton().getJoints()){
            JointTransform jointPose = this.getJointTransform(joint);
            JointTransform mirroredJointPose = this.getJointTransform(this.getJointSkeleton().getJointConfiguration(joint).mirrorJoint());
            mirroredPose.put(joint, new JointTransform(jointPose).blendLinear(mirroredJointPose, alpha));
        }
        for(String joint : mirroredPose.keySet()){
            this.setJointTransform(joint, mirroredPose.get(joint));
        }
    }

    public enum PoseSpace {
        ENTITY,
        LOCAL
    }
}
