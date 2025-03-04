package com.trainguy9512.locomotion.animation.pose;

import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.locomotion.animation.joint.JointSkeleton;
import com.trainguy9512.locomotion.animation.joint.JointTransform;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public class LocalSpacePose extends AnimationPose {

    private LocalSpacePose(JointSkeleton jointSkeleton) {
        super(jointSkeleton);
    }

    private LocalSpacePose(AnimationPose pose){
        super(pose);
    }


    /**
     * Creates a blank animation pose using a joint skeleton as the template.
     * @param jointSkeleton         Template joint skeleton
     * @return                      New animation pose
     */
    public static LocalSpacePose of(JointSkeleton jointSkeleton){
        return new LocalSpacePose(jointSkeleton);
    }


    public static LocalSpacePose of(AnimationPose pose){
        return new LocalSpacePose(pose);
    }

    /**
     * Creates a local space pose from this component space pose.
     */
    public ComponentSpacePose convertedToComponentSpace(){
        ComponentSpacePose pose = ComponentSpacePose.of(this);
        pose.convertChildrenJointsToComponentSpace(this.getJointSkeleton().getRootJoint(), new PoseStack());
        return pose;
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
