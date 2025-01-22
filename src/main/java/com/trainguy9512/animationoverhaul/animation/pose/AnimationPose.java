package com.trainguy9512.animationoverhaul.animation.pose;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
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
            this.setJointPose(joint, JointTransform.ZERO);
        }
    }

    public AnimationPose(AnimationPose<L> animationPose){
        this.jointSkeleton = animationPose.jointSkeleton;
        this.jointTransforms = new HashMap<>(animationPose.jointTransforms);
        this.poseSpace = animationPose.poseSpace;
    }

    public static <L extends Enum<L>> AnimationPose<L> of(JointSkeleton<L> jointSkeleton){
        return new AnimationPose<>(jointSkeleton);
    }

    public JointSkeleton<L> getSkeleton(){
        return this.jointSkeleton;
    }

    public void applyDefaultPoseOffset(){
        for(Enum<L> locator : this.getSkeleton().getJoints()){
            JointTransform offset = JointTransform.fromPartPose(this.getSkeleton().getLocatorDefaultPose(locator));
            this.setJointPose(locator, getJointPoseCopy(locator).multiplyTransform(offset));
        }
    }

    public void setJointPose(String joint, JointTransform jointPose){
        this.jointTransforms.put(locator, jointPose);
    }

    public JointTransform getJointPoseCopy(Enum<L> locator){
        return new JointTransform(this.jointTransforms.getOrDefault(locator, JointTransform.ZERO));
    }

    public JointTransform getJointPoseReference(Enum<L> locator){
        return this.jointTransforms.getOrDefault(locator, JointTransform.ZERO);
    }

    /*
    public void subtractPose(AnimationPose animationPose){
        for(LocatorSkeleton.LocatorEntry locatorEntry : animationPose.getSkeleton().getLocatorEntries()){
            this.setLocatorPose(locatorEntry.getLocatorIdentifier(), MutablePartPose);
        }
    }

     */



    // Local to World
    public AnimationPose<L> convertSpaceLocalToEntity(Set<Enum<L>> jointsToConvert){
        this.convertChildrenSpaceLocalToEntity(this.getSkeleton().getRootJoint(), new PoseStack(), jointsToConvert);
        return this;
    }

    public AnimationPose<L> convertSpaceLocalToEntity(Enum<L> jointToConvert){
        return this.convertSpaceLocalToEntity(Set.of(jointToConvert));
    }

    public AnimationPose<L> convertSpaceLocalToEntity(){
        return this.convertSpaceLocalToEntity(this.getSkeleton().getJoints());
    }

    private void convertChildrenSpaceLocalToEntity(Enum<L> parent, PoseStack poseStack, Set<Enum<L>> jointsToConvert){
        JointTransform localParentJointPose = new JointTransform(this.getJointPoseCopy(parent));

        poseStack.pushPose();

        poseStack.mulPose(localParentJointPose.getTransformCopy());

        for (Enum<L> child : this.getSkeleton().getDirectChildrenOfJoint(parent)){
            convertChildrenSpaceLocalToEntity(child, poseStack, jointsToConvert);
        }

        for(Enum<L> joint : jointsToConvert){
            if((this.getSkeleton().jointIsParentOfChild(parent, joint) || jointsToConvert.contains(parent)) && localParentJointPose.getPoseSpace() == PoseSpace.LOCAL){
                this.setJointPose(parent, localParentJointPose.setTransform(new Matrix4f(poseStack.last().pose())).setPoseSpace(PoseSpace.ENTITY));
                break;
            }
        }
        /*
        if(jointsToConvert.contains(parent) && localParentJointPose.getPoseSpace() == PoseSpace.LOCAL){
            this.setJointPose(parent, localParentJointPose.setTransform(new Matrix4f(poseStack.last().pose())).setPoseSpace(PoseSpace.ENTITY));
        }

         */
        poseStack.popPose();
    }



    // World to Local
    public AnimationPose<L> convertSpaceEntityToLocal(Set<Enum<L>> jointsToConvert){
        this.convertChildrenSpaceEntityToLocal(this.getSkeleton().getRootJoint(), new Matrix4f(), jointsToConvert);
        return this;
    }

    public AnimationPose<L> convertSpaceEntityToLocal(Enum<L> jointToConvert){
        return this.convertSpaceEntityToLocal(Set.of(jointToConvert));
    }

    public AnimationPose<L> convertSpaceEntityToLocal(){
        return this.convertSpaceEntityToLocal(this.getSkeleton().getJoints());
    }

    private void convertChildrenSpaceEntityToLocal(Enum<L> parent, Matrix4f parentMatrix, Set<Enum<L>> jointsToConvert){
        JointTransform parentJointPose = this.getJointPoseCopy(parent);

        for (Enum<L> child : this.getSkeleton().getDirectChildrenOfJoint(parent)){
            convertChildrenSpaceEntityToLocal(child, parentJointPose.getTransformCopy(), jointsToConvert);
        }

        if(jointsToConvert.contains(parent) && parentJointPose.getPoseSpace() == PoseSpace.ENTITY){
            parentJointPose.transform(parentMatrix.invert(), TransformSpace.LOCAL).setPoseSpace(PoseSpace.LOCAL);
            this.setJointPose(parent, parentJointPose);
        }
    }




    public void blend(AnimationPose<L> animationPose, float alpha, Easing easing){
        for(Enum<L> locator : this.getSkeleton().getJoints()){
            JointTransform jointPoseA = this.getJointPoseCopy(locator);
            JointTransform jointPoseB = animationPose.getJointPoseCopy(locator);
            this.setJointPose(locator, jointPoseA.blend(jointPoseB, alpha, easing));
        }
    }

    public void blendLinear(AnimationPose<L> animationPose, float alpha){
        this.blend(animationPose, alpha, Easing.Linear.of());
    }

    public AnimationPose<L> getBlended(AnimationPose<L> animationPose, float alpha, Easing easing){
        AnimationPose<L> newAnimationPose = new AnimationPose<>(this);
        newAnimationPose.blend(animationPose, alpha, easing);
        return newAnimationPose;
    }

    public AnimationPose<L> getBlendedLinear(AnimationPose<L> animationPose, float alpha){
        return this.getBlended(animationPose, alpha, Easing.Linear.of());
    }

    public void blendByLocators(AnimationPose<L> animationPose, @NotNull List<Enum<L>> locators, float alpha, Easing easing){
        for(Enum<L> locator : locators){
            JointTransform jointPoseA = this.getJointPoseCopy(locator);
            JointTransform jointPoseB = animationPose.getJointPoseCopy(locator);
            this.setJointPose(locator, jointPoseA.blend(jointPoseB, alpha, easing));
        }
    }

    public AnimationPose<L> getBlendedByLocators(AnimationPose<L> animationPose, @NotNull List<Enum<L>> locators, float alpha, Easing easing){
        AnimationPose<L> newAnimationPose = new AnimationPose<>(this);
        newAnimationPose.blendByLocators(animationPose, locators, alpha, easing);
        return newAnimationPose;
    }

    public AnimationPose<L> getBlendedByLocatorsLinear(AnimationPose<L> animationPose, List<Enum<L>> locators, float alpha){
        return this.getBlendedByLocators(animationPose, locators, alpha, Easing.Linear.of());
    }

    public AnimationPose<L> getSelectedByLocators(AnimationPose<L> animationPose, List<Enum<L>> locators){
        return this.getBlendedByLocatorsLinear(animationPose, locators, 1);
    }

    public void inverseMultiply(AnimationPose<L> animationPose){
        for(Enum<L> locator : this.getSkeleton().getJoints()){
            this.setJointPose(locator, this.getJointPoseCopy(locator).inverseMultiplyTransform(animationPose.getJointPoseCopy(locator)));
        }
    }

    public AnimationPose<L> getInverseMultiplied(AnimationPose<L> animationPose){
        AnimationPose<L> newAnimationPose = new AnimationPose<>(this);
        newAnimationPose.inverseMultiply(animationPose);
        return newAnimationPose;
    }

    public void multiply(AnimationPose<L> animationPose){
        for(Enum<L> locator : this.getSkeleton().getJoints()){
            this.setJointPose(locator, this.getJointPoseCopy(locator).multiplyTransform(animationPose.getJointPoseCopy(locator)));
        }
    }

    public AnimationPose<L> getMultiplied(AnimationPose<L> animationPose){
        AnimationPose<L> newAnimationPose = new AnimationPose<>(this);
        newAnimationPose.multiply(animationPose);
        return newAnimationPose;
    }



    public AnimationPose<L> translateJoint(Enum<L> joint, Vector3f translation, TransformSpace transformSpace, boolean additive){
        convertSpaceEntityToLocal();
        if(transformSpace == TransformSpace.ENTITY){
            this.convertSpaceLocalToEntity(joint);
        }
        if(additive){
            this.getJointPoseReference(joint).translate(translation, transformSpace);
        } else {
            this.getJointPoseReference(joint).setTranslation(translation);
        }
        convertSpaceEntityToLocal();
        return this;
    }

    public AnimationPose<L> rotateJoint(Enum<L> joint, Vector3f rotationXYZ, TransformSpace transformSpace, boolean additive){
        convertSpaceEntityToLocal();
        if(transformSpace == TransformSpace.ENTITY){
            convertSpaceLocalToEntity(joint);
        }
        if(additive){
            this.getJointPoseReference(joint).rotate(rotationXYZ, transformSpace);
        } else {
            this.getJointPoseReference(joint).setRotation(rotationXYZ);
        }
        convertSpaceEntityToLocal();
        return this;
    }



    public void mirror(){
        this.mirrorBlended(1);
    }

    public void mirrorBlended(float alpha){
        HashMap<Enum<L>, JointTransform> mirroredPose = Maps.newHashMap();
        for(Enum<L> locator : this.getSkeleton().getJoints()){
            JointTransform jointPose = this.getJointPoseCopy(locator);
            JointTransform mirroredJointPose = this.getJointPoseCopy(this.getSkeleton().getMirrorJoint(locator));
            mirroredPose.put(locator, new JointTransform(jointPose).blendLinear(mirroredJointPose, alpha));
        }
        for(Enum<L> locator : mirroredPose.keySet()){
            this.setJointPose(locator, mirroredPose.get(locator));
        }

        /*
        for(String identifier : this.pose.keySet()){
            MutablePartPose mutablePartPose = this.pose.get(identifier);
            MutablePartPose mirroredMutablePartPose = this.pose.get(this.locatorSkeleton.getMirroredLocator(identifier));

            MutablePartPose newMutablePartPose = mutablePartPose.getCopy().blendLinear(mirroredMutablePartPose.getMirrored(), alpha);
            mirroredPose.put(identifier, newMutablePartPose);
        }
        this.pose.replaceAll((key, pose) -> mirroredPose.get(key));

         */
    }

    public static <L extends Enum<L>> AnimationPose<L> fromChannelTimeline(JointSkeleton<L> jointSkeleton, ResourceLocation resourceLocation, float time){
        AnimationPose<L> animationPose = AnimationPose.of(jointSkeleton);
        for(Enum<L> locator : jointSkeleton.getJoints()){
            animationPose.setJointPose(locator, JointTransform.getJointTransformFromAnimationSequence(resourceLocation, locator.toString(), time));
        }
        return animationPose;
    }

    public enum PoseSpace {
        ENTITY,
        LOCAL
    }

    public enum TransformSpace {
        ENTITY,
        PARENT,
        LOCAL
    }
}
