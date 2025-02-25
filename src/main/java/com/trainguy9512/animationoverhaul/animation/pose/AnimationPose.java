package com.trainguy9512.animationoverhaul.animation.pose;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.joint.JointTransform;
import com.trainguy9512.animationoverhaul.util.Interpolator;
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
}
