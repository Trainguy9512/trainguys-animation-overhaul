package com.trainguy9512.animationoverhaul.animation.pose;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.joint.JointTransform;
import org.joml.*;

import java.util.*;

public abstract class AnimationPose {

    protected final JointSkeleton jointSkeleton;
    protected final Map<String, JointTransform> jointTransforms;
    private final Map<String, Matrix4f> jointParentMatrices;

    protected AnimationPose(JointSkeleton jointSkeleton){
        this.jointSkeleton = jointSkeleton;
        this.jointTransforms = Maps.newHashMap();
        this.jointParentMatrices = Maps.newHashMap();

        for(String joint : jointSkeleton.getJoints()){
            this.setJointTransform(joint, JointTransform.ZERO);
        }
    }

    protected AnimationPose(AnimationPose animationPose){
        this.jointSkeleton = animationPose.jointSkeleton;
        this.jointTransforms = new HashMap<>(animationPose.jointTransforms);
        this.jointParentMatrices = new HashMap<>(animationPose.jointParentMatrices);
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

        Matrix4f componentSpaceMatrix = new Matrix4f(poseStack.last().pose());
        this.jointParentMatrices.put(parent, componentSpaceMatrix);
        this.setJointTransform(parent, JointTransform.of(componentSpaceMatrix));
        poseStack.popPose();
    }

    protected void convertChildrenJointsToLocalSpace(String parent, PoseStack poseStack){

        Matrix4f offsetMatrix = this.getJointTransform(parent).getTransform().mul(this.jointParentMatrices.get(parent).invert());
        poseStack.pushPose();
        poseStack.mulPose(offsetMatrix);

        //Matrix4f childParentMatrix = offsetMatrix.mul(parentMatrix);

        this.getJointSkeleton().getDirectChildrenOfJoint(parent).ifPresent(children -> children.forEach(child -> this.convertChildrenJointsToLocalSpace(child, poseStack)));

        //parentJointPose
        //parentJointPose.multiply(parentMatrix.invert(), JointTransform.TransformSpace.LOCAL);
        //this.setJointTransform(parent, JointTransform.of(offsetMatrix.mul(this.jointParentMatrices.get(parent).invert())));
        this.setJointTransform(parent, JointTransform.of(this.jointTransforms.get(parent).getTransform().invert().mul(poseStack.last().pose())));
        poseStack.popPose();
    }
}
