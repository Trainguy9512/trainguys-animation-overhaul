package com.trainguy9512.animationoverhaul.animation.pose;

import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.joint.JointTransform;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public class LocalSpacePose extends AnimationPose {

    private LocalSpacePose(JointSkeleton jointSkeleton) {
        super(jointSkeleton);
    }

    private LocalSpacePose(AnimationPose pose){
        super(pose);
    }

    @Override
    public <P extends AnimationPose> P interpolatedFilteredByJoints(P other, float weight, Set<String> joints) {
        // If the weight is 0, don't interpolate anything and just return this.
        if(weight == 0){
            return new AnimationPose(this);
        }
        // Convert the other pose's pose space to match this pose's pose space.
        AnimationPose otherConverted = other.convertedToLocalSpace();

        AnimationPose interpolatedAnimationPose = this.convertedToLocalSpace();
        joints.stream()
                .filter(joint -> this.getJointSkeleton().containsJoint(joint))
                .forEach(joint -> interpolatedAnimationPose.setJointTransform(joint,
                        weight == 1 ? otherConverted.getJointTransform(joint) :
                                interpolatedAnimationPose.getJointTransform(joint).interpolated(other.getJointTransform(joint), weight))
                );
        return interpolatedAnimationPose;
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
     * @param timePercentage        Point of time in animation sequence
     * @return                      New animation pose
     */
    public static LocalSpacePose fromAnimationSequence(JointSkeleton jointSkeleton, ResourceLocation resourceLocation, float timePercentage){
        LocalSpacePose pose = LocalSpacePose.of(jointSkeleton);
        for(String joint : jointSkeleton.getJoints()){
            pose.setJointTransform(joint, JointTransform.ofAnimationSequenceJoint(resourceLocation, joint, timePercentage));
        }
        return pose;
    }
}
