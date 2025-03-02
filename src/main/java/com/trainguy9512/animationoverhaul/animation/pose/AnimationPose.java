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
        poseStack.mulPose(localParentJointPose.getTransform());

        this.getJointSkeleton().getDirectChildrenOfJoint(parent).ifPresent(children -> children.forEach(child -> this.convertChildrenJointsToComponentSpace(child, poseStack)));

        this.setJointTransform(parent, JointTransform.of(new Matrix4f(poseStack.last().pose())));
        poseStack.popPose();
    }

    protected void convertChildrenJointsToLocalSpace(String parent, Matrix4f parentMatrix){
        JointTransform parentJointPose = this.getJointTransform(parent);

        this.getJointSkeleton().getDirectChildrenOfJoint(parent).ifPresent(children -> children.forEach(child -> this.convertChildrenJointsToLocalSpace(child, parentJointPose.getTransform())));

        parentJointPose.multiply(parentMatrix.invert(), JointTransform.TransformSpace.LOCAL);
        this.setJointTransform(parent, parentJointPose);
    }

    /**
     * Creates an animation pose from a point in time within the provided animation sequence
     * @param jointSkeleton         Template joint skeleton
     * @param resourceLocation      Animation sequence resource location
     * @param timeInTicks           Point of time in the animation to get.
     * @param looping               Whether the animation should be looped or not.
     * @return                      New animation pose
     */
    public static LocalSpacePose fromAnimationSequence(JointSkeleton jointSkeleton, ResourceLocation resourceLocation, float timeInTicks, boolean looping){
        LocalSpacePose pose = LocalSpacePose.of(jointSkeleton);
        for(String joint : jointSkeleton.getJoints()){
            pose.setJointTransform(joint, JointTransform.ofAnimationSequenceJoint(resourceLocation, joint, timeInTicks, looping));
        }
        return pose;
    }

    public void mirror(){
        this.mirrorWeighted(1);
    }

    public void mirrorWeighted(float weight){
        this.jointTransforms.forEach((joint, transform) -> {
            JointTransform mirroredTransform = this.getJointTransform(this.getJointSkeleton().getJointConfiguration(joint).mirrorJoint()).mirrored();
            this.setJointTransform(joint, transform.interpolated(mirroredTransform, weight));
        });
    }

    /**
     * Returns a new animation pose interpolated between this pose and the provided pose.
     * @param other     Animation pose to interpolate to.
     * @param weight    Weight value, 0 is the original pose and 1 is the other pose.
     * @return          New interpolated animation pose.
     */
    public LocalSpacePose interpolated(LocalSpacePose other, float weight){
        return interpolatedFilteredByJoints(other, weight, this.jointSkeleton.getJoints());
    }

    /**
     * Returns a new animation pose interpolated between this pose and the provided pose, only on the specified joints.
     * @param other     Animation pose to interpolate to.
     * @param weight    Weight value, 0 is the original pose and 1 is the other pose.
     * @param joints    Set of joints to interpolate.
     * @return          New interpolated animation pose.
     */
    public LocalSpacePose interpolatedFilteredByJoints(LocalSpacePose other, float weight, Set<String> joints) {
        LocalSpacePose pose = LocalSpacePose.of(this);
        // If the weight is 0, don't interpolate anything and just return this.
        if(weight == 0){
            return pose;
        }

        joints.forEach(joint -> {
            if(this.getJointSkeleton().containsJoint(joint)){
                pose.setJointTransform(joint, weight == 1 ? other.getJointTransform(joint) :
                        pose.getJointTransform(joint).interpolated(other.getJointTransform(joint), weight));
            }
        });
        return pose;
    }

    /**
     * Returns a new animation pose with the other pose filtered by the selected joints.
     * @param other     Animation pose to filter.
     * @param joints    Set of joints to filter by.
     * @return          New filtered animation pose.
     */
    public LocalSpacePose filteredByJoints(LocalSpacePose other, Set<String> joints){
        return interpolatedFilteredByJoints(other, 1, joints);
    }
}
