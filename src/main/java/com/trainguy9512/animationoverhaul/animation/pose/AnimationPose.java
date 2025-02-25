package com.trainguy9512.animationoverhaul.animation.pose;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.joint.JointTransform;
import net.minecraft.resources.ResourceLocation;
import org.joml.*;

import java.util.*;

public abstract class AnimationPose {

    protected final JointSkeleton jointSkeleton;
    protected final HashMap<String, JointTransform> jointTransforms;

    protected AnimationPose(JointSkeleton jointSkeleton){
        this.jointSkeleton = jointSkeleton;
        this.jointTransforms = Maps.newHashMap();

        for(String joint : jointSkeleton.getJoints()){
            this.setJointTransform(joint, JointTransform.ZERO);
        }
    }

    protected AnimationPose(AnimationPose animationPose){
        this.jointSkeleton = animationPose.jointSkeleton;
        this.jointTransforms = new HashMap<>(animationPose.jointTransforms);
    }

    /**
     * Retrieves the animation pose's skeleton.
     * @return                      Joint skeleton
     */
    public JointSkeleton getJointSkeleton(){
        return this.jointSkeleton;
    }

    /**
     * Sets the transform for the supplied joint by its string identifier.
     * @param joint                 Joint string identifier
     * @param jointTransform        Joint transform
     */
    public void setJointTransform(String joint, JointTransform jointTransform){
        if(this.jointSkeleton.containsJoint(joint)){
            this.jointTransforms.put(joint, jointTransform);
        }
    }

    /**
     * Retrieves a copy of the transform for the supplied joint.
     * @param joint                 Joint string identifier
     * @return                      Joint transform
     */
    public JointTransform getJointTransform(String joint){
        return JointTransform.of(this.jointTransforms.getOrDefault(joint, JointTransform.ZERO));
    }

    protected void convertChildrenJointsToComponentSpace(String parent, PoseStack poseStack){
        JointTransform localParentJointPose = this.getJointTransform(parent);

        poseStack.pushPose();
        poseStack.mulPose(localParentJointPose.composeMatrix());

        this.getJointSkeleton().getDirectChildrenOfJoint(parent).ifPresent(children -> children.forEach(child -> this.convertChildrenJointsToComponentSpace(child, poseStack)));

        this.setJointTransform(parent, JointTransform.of(new Matrix4f(poseStack.last().pose())));
        poseStack.popPose();
    }

    protected void convertChildrenJointsToLocalSpace(String parent, Matrix4f parentMatrix){
        JointTransform parentJointPose = this.getJointTransform(parent);

        this.getJointSkeleton().getDirectChildrenOfJoint(parent).ifPresent(children -> children.forEach(child -> this.convertChildrenJointsToLocalSpace(child, parentJointPose.composeMatrix())));

        parentJointPose.multiply(parentMatrix.invert(), JointTransform.TransformSpace.LOCAL);
        this.setJointTransform(parent, parentJointPose);
    }

    /**
     * Returns a new animation pose interpolated between this pose and the provided pose.
     * @param other     Animation pose to interpolate to.
     * @param weight    Weight value, 0 is the original pose and 1 is the other pose.
     * @return          New interpolated animation pose.
     */
    public <P extends AnimationPose> P interpolated(P other, float weight){
        return interpolatedFilteredByJoints(other, weight, this.jointSkeleton.getJoints());
    }

    /**
     * Returns a new animation pose interpolated between this pose and the provided pose, only on the specified joints.
     * @param other     Animation pose to interpolate to.
     * @param weight    Weight value, 0 is the original pose and 1 is the other pose.
     * @param joints    Set of joints to interpolate.
     * @return          New interpolated animation pose.
     */
    public abstract <P extends AnimationPose> P interpolatedFilteredByJoints(P other, float weight, Set<String> joints);

    /**
     * Returns a new animation pose with the other pose filtered by the selected joints.
     * @param other     Animation pose to filter.
     * @param joints    Set of joints to filter by.
     * @return          New filtered animation pose.
     */
    public <P extends AnimationPose> P filteredByJoints(P other, Set<String> joints){
        return interpolatedFilteredByJoints(other, 1, joints);
    }

    public AnimationPose jointTranslated(String joint, Vector3f translation, JointTransform.TransformSpace transformSpace, boolean relative){
        if(this.jointSkeleton.containsJoint(joint)){
            AnimationPose animationPose = switch(transformSpace){
                case ENTITY, PARENT -> this.convertedToEntitySpace();
                case LOCAL -> this.convertedToLocalSpace();
            };

            JointTransform jointTransform = animationPose.getJointTransform(joint);
            if(relative){
                jointTransform.translate(translation, transformSpace);
            } else {
                jointTransform.setTranslation(translation);
            }

            animationPose.setJointTransform(joint, jointTransform);
            return animationPose.convertedToLocalSpace();
        }
        return this;
    }

    public AnimationPose jointRotated(String joint, Vector3f rotationXYZ, JointTransform.TransformSpace transformSpace, boolean relative){
        if(this.jointSkeleton.containsJoint(joint)){
            AnimationPose animationPose = switch(transformSpace){
                case ENTITY, PARENT -> this.convertedToEntitySpace();
                case LOCAL -> this.convertedToLocalSpace();
            };

            JointTransform jointTransform = animationPose.getJointTransform(joint);
            if(relative){
                jointTransform.rotate(rotationXYZ, transformSpace);
            } else {
                jointTransform.setRotation(rotationXYZ);
            }

            animationPose.setJointTransform(joint, jointTransform);
            return animationPose.convertedToLocalSpace();
        }
        return this;
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
}
