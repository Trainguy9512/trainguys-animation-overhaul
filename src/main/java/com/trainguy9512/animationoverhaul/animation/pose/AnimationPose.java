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

    public AnimationPose(AnimationPose animationPose){
        this.jointSkeleton = animationPose.jointSkeleton;
        this.jointTransforms = new HashMap<>(animationPose.jointTransforms);
        this.poseSpace = animationPose.poseSpace;
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
    public void setJointPose(String joint, JointTransform jointPose){
        this.jointTransforms.put(joint, jointPose);
    }

    /**
     * Retrieves a copy of the transform for the supplied joint.
     * @param joint Joint string identifier
     * @return Joint transform
     */
    public JointTransform getJointPose(String joint){
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
        JointTransform localParentJointPose = new JointTransform(this.getJointPose(parent));

        poseStack.pushPose();

        poseStack.mulPose(localParentJointPose.getTransform());

        for (String child : this.getJointSkeleton().getDirectChildrenOfJoint(parent)){
            convertChildrenJointsToEntitySpace(child, poseStack, jointsToConvert);
        }

        for(String joint : jointsToConvert){
            if((this.getJointSkeleton().jointIsParentOfChild(parent, joint) || jointsToConvert.contains(parent))){
                this.setJointPose(parent, localParentJointPose.setTransform(new Matrix4f(poseStack.last().pose())));
                break;
            }
        }
        poseStack.popPose();
    }



    // World to Local
    public AnimationPose<L> convertSpaceEntityToLocal(Set<Enum<L>> jointsToConvert){
        this.convertChildrenSpaceEntityToLocal(this.getJointSkeleton().getRootJoint(), new Matrix4f(), jointsToConvert);
        return this;
    }

    public AnimationPose<L> convertSpaceEntityToLocal(Enum<L> jointToConvert){
        return this.convertSpaceEntityToLocal(Set.of(jointToConvert));
    }

    public AnimationPose<L> convertSpaceEntityToLocal(){
        return this.convertSpaceEntityToLocal(this.getJointSkeleton().getJoints());
    }

    private void convertChildrenSpaceEntityToLocal(Enum<L> parent, Matrix4f parentMatrix, Set<Enum<L>> jointsToConvert){
        JointTransform parentJointPose = this.getJointPose(parent);

        for (Enum<L> child : this.getJointSkeleton().getDirectChildrenOfJoint(parent)){
            convertChildrenSpaceEntityToLocal(child, parentJointPose.getTransformCopy(), jointsToConvert);
        }

        if(jointsToConvert.contains(parent) && parentJointPose.getPoseSpace() == PoseSpace.ENTITY){
            parentJointPose.transform(parentMatrix.invert(), TransformSpace.LOCAL).setPoseSpace(PoseSpace.LOCAL);
            this.setJointPose(parent, parentJointPose);
        }
    }




    public void blend(AnimationPose<L> animationPose, float alpha, Easing easing){
        for(Enum<L> locator : this.getJointSkeleton().getJoints()){
            JointTransform jointPoseA = this.getJointPose(locator);
            JointTransform jointPoseB = animationPose.getJointPose(locator);
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
            JointTransform jointPoseA = this.getJointPose(locator);
            JointTransform jointPoseB = animationPose.getJointPose(locator);
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
        for(Enum<L> locator : this.getJointSkeleton().getJoints()){
            this.setJointPose(locator, this.getJointPose(locator).inverseMultiplyTransform(animationPose.getJointPose(locator)));
        }
    }

    public AnimationPose<L> getInverseMultiplied(AnimationPose<L> animationPose){
        AnimationPose<L> newAnimationPose = new AnimationPose<>(this);
        newAnimationPose.inverseMultiply(animationPose);
        return newAnimationPose;
    }

    public void multiply(AnimationPose<L> animationPose){
        for(Enum<L> locator : this.getJointSkeleton().getJoints()){
            this.setJointPose(locator, this.getJointPose(locator).multiplyTransform(animationPose.getJointPose(locator)));
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
            this.getConvertedToEntitySpace(joint);
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
            getConvertedToEntitySpace(joint);
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
        for(Enum<L> locator : this.getJointSkeleton().getJoints()){
            JointTransform jointPose = this.getJointPose(locator);
            JointTransform mirroredJointPose = this.getJointPose(this.getJointSkeleton().getMirrorJoint(locator));
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
}
