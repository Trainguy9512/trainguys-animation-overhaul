package com.trainguy9512.animationoverhaul.animation.pose;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.*;

import java.util.HashMap;
import java.util.List;

public class AnimationPose<L extends Enum<L>> {

    private final LocatorSkeleton<L> locatorSkeleton;
    private final HashMap<Enum<L>, JointPose> pose;

    private AnimationPose(LocatorSkeleton<L> locatorSkeleton){
        this.locatorSkeleton = locatorSkeleton;
        this.pose = Maps.newHashMap();

        for(Enum<L> locator : locatorSkeleton.getLocators()){
            this.setJointPose(locator, JointPose.ZERO);
        }
    }

    public AnimationPose(AnimationPose<L> animationPose){
        this.locatorSkeleton = animationPose.locatorSkeleton;
        this.pose = new HashMap<>(animationPose.pose);
    }

    public static <L extends Enum<L>> AnimationPose<L> of(LocatorSkeleton<L> locatorSkeleton){
        return new AnimationPose<>(locatorSkeleton);
    }

    @Deprecated
    public AnimationPose<L> getCopy(){
        return new AnimationPose<L>(this);
        /*
        AnimationPose<L> copiedAnimationPose = new AnimationPose<>(this.locatorSkeleton);
        for(Enum<L> locator : this.getSkeleton().getLocators()){
            copiedAnimationPose.setJointPose(locator, new JointPose(this.getJointPoseCopy(locator)));
        }
        return copiedAnimationPose;

         */
    }

    public LocatorSkeleton<L> getSkeleton(){
        return this.locatorSkeleton;
    }

    public void applyDefaultPoseOffset(){
        for(Enum<L> locator : this.getSkeleton().getLocators()){
            JointPose offset = JointPose.fromPartPose(this.getSkeleton().getLocatorDefaultPose(locator));
            this.setJointPose(locator, getJointPoseCopy(locator).multiplyPose(offset));
        }
    }

    public void setJointPose(Enum<L> locator, JointPose jointPose){
        this.pose.put(locator, jointPose);
    }

    public JointPose getJointPoseCopy(Enum<L> locator){
        return new JointPose(this.pose.getOrDefault(locator, JointPose.ZERO));
    }

    /*
    public void subtractPose(AnimationPose animationPose){
        for(LocatorSkeleton.LocatorEntry locatorEntry : animationPose.getSkeleton().getLocatorEntries()){
            this.setLocatorPose(locatorEntry.getLocatorIdentifier(), MutablePartPose);
        }
    }

     */

    public AnimationPose<L> getConvertedFromLocalToWorld(){
        AnimationPose<L> animationPose = new AnimationPose<L>(this);
        //ArrayList<Quaternionf> rotationStack = new ArrayList<>();



        animationPose.transformChildren(this.getSkeleton().getRootLocator(), new PoseStack());
        //animationPose.transformChildren(this.getSkeleton().getRootLocator(), this.getJointPoseCopy(this.getSkeleton().getRootLocator()).getTransformCopy());

        //HashMap<Enum<L>, Matrix4fStack> matrixStackSet = Maps.newHashMap();
        //ArrayList<Matrix4f> rootMatrixStack = new ArrayList<>();
        //Matrix4f rootMatrix = new Matrix4f();

        //Enum<L> enumm = this.locatorSkeleton.getLocatorChildren(this.locatorSkeleton.getRootLocator()).get(1);
        //animationPose.setLocatorPose(enumm, animationPose.getLocatorPose(enumm).rotate(new Vector3f(Mth.PI, 0, 0), false));

        //animationPose.transformChildren(this.getSkeleton().getRootLocator(), rootMatrixStack, this.getCopy(), 200);

        return animationPose;
    }

    private void transformChildren(Enum<L> parent, Matrix4f parentTransform){
        JointPose parentJointPose = new JointPose(this.getJointPoseCopy(parent));


        for (Enum<L> child : this.getSkeleton().getLocatorChildren(parent)){

            JointPose childJointPose = new JointPose(this.getJointPoseCopy(child));
            Matrix4f multipliedChildTransform = childJointPose.getTransformCopy().mul(parentTransform);
            this.setJointPose(child, childJointPose.setTransform(multipliedChildTransform));

            transformChildren(child, new Matrix4f(childJointPose.getTransformCopy()));
        }
    }



    private void transformChildren(Enum<L> parent, PoseStack poseStack){
        JointPose localParentJointPose = new JointPose(this.getJointPoseCopy(parent));
        poseStack.pushPose();
        poseStack.mulPoseMatrix(localParentJointPose.getTransformCopy());



        //poseStack.translate(localParentJointPose.getTranslation().x(), localParentJointPose.getTranslation().y(), localParentJointPose.getTranslation().z());
        //poseStack.mulPose(localParentJointPose.getRotation());

        for (Enum<L> child : this.getSkeleton().getLocatorChildren(parent)){



            //Quaternionf composedRotation = new Quaternionf();
            /*
            for(Quaternionf childRotation : rotationStack){
                newChildPose.rotate(childRotation, false);
            }

             */
            //this.setLocatorPose(child, newWorldChildPose);



            /*
            PoseStack poseStack = new PoseStack();
            parentPose.transformPoseStack(poseStack, 1);
            poseStack.pushPose();
            childPose.transformPoseStack(poseStack, 1);

            Matrix4f pose = poseStack.last().pose();
            Quaternionf rotation = pose.getNormalizedRotation(new Quaternionf());
            this.setLocatorPose(child, MutablePartPose.fromTranslationAndRotation(
                    pose.m30(),
                    pose.m31(),
                    pose.m32(),
                    rotation
            ));

            poseStack.popPose();
             */
            //Quaternionf childRotation = childPose.getCopy().getRotation();


            /*
            for(Quaternionf rotation : rotationStack){
                childRotation.mul(rotation, childRotation);
            }

             */

            //MutablePartPose transformedPose = parentPose.getCopy();
            //transformedPose.translate(childPose.getTranslation(), true);
            //transformedPose.rotate(childPose.getRotation(), true);

            transformChildren(child, poseStack);

            /*
            this.setLocatorPose(child, getLocatorPose(child)
                            //.translate(childPose.getTranslation(), true)
                    //.rotate(childPose.getRotation(), true)
            );
             */
        }
        this.setJointPose(parent, localParentJointPose.setTransform(new Matrix4f(poseStack.last().pose())));
        poseStack.popPose();
    }

    public void blend(AnimationPose<L> animationPose, float alpha, Easing easing){
        for(Enum<L> locator : this.getSkeleton().getLocators()){
            JointPose jointPoseA = this.getJointPoseCopy(locator);
            JointPose jointPoseB = animationPose.getJointPoseCopy(locator);
            this.setJointPose(locator, jointPoseA.blend(jointPoseB, alpha, easing));
        }
    }

    public void blendLinear(AnimationPose<L> animationPose, float alpha){
        this.blend(animationPose, alpha, Easing.Linear.of());
    }

    public AnimationPose<L> getBlended(AnimationPose<L> animationPose, float alpha, Easing easing){
        AnimationPose<L> newAnimationPose = this.getCopy();
        newAnimationPose.blend(animationPose, alpha, easing);
        return newAnimationPose;
    }

    public AnimationPose<L> getBlendedLinear(AnimationPose<L> animationPose, float alpha){
        return this.getBlended(animationPose, alpha, Easing.Linear.of());
    }

    public void blendByLocators(AnimationPose<L> animationPose, @NotNull List<Enum<L>> locators, float alpha, Easing easing){
        for(Enum<L> locator : locators){
            JointPose jointPoseA = this.getJointPoseCopy(locator);
            JointPose jointPoseB = animationPose.getJointPoseCopy(locator);
            this.setJointPose(locator, jointPoseA.blend(jointPoseB, alpha, easing));
        }
    }

    public AnimationPose<L> getBlendedByLocators(AnimationPose<L> animationPose, @NotNull List<Enum<L>> locators, float alpha, Easing easing){
        AnimationPose<L> newAnimationPose = this.getCopy();
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
        for(Enum<L> locator : this.getSkeleton().getLocators()){
            this.setJointPose(locator, this.getJointPoseCopy(locator).inverseMultiplyPose(animationPose.getJointPoseCopy(locator)));
        }
    }

    public AnimationPose<L> getInverseMultiplied(AnimationPose<L> animationPose){
        AnimationPose<L> newAnimationPose = this.getCopy();
        newAnimationPose.inverseMultiply(animationPose);
        return newAnimationPose;
    }

    public void multiply(AnimationPose<L> animationPose){
        for(Enum<L> locator : this.getSkeleton().getLocators()){
            this.setJointPose(locator, this.getJointPoseCopy(locator).multiplyPose(animationPose.getJointPoseCopy(locator)));
        }
    }

    public AnimationPose<L> getMultiplied(AnimationPose<L> animationPose){
        AnimationPose<L> newAnimationPose = this.getCopy();
        newAnimationPose.multiply(animationPose);
        return newAnimationPose;
    }

    public void mirror(){
        this.mirrorBlended(1);
    }

    public void mirrorBlended(float alpha){
        HashMap<Enum<L>, JointPose> mirroredPose = Maps.newHashMap();
        for(Enum<L> locator : this.getSkeleton().getLocators()){
            JointPose jointPose = this.getJointPoseCopy(locator);
            JointPose mirroredJointPose = this.getJointPoseCopy(this.getSkeleton().getMirroredLocator(locator));
            mirroredPose.put(locator, new JointPose(jointPose).blendLinear(mirroredJointPose, alpha));
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

    public static <L extends Enum<L>> AnimationPose<L> fromChannelTimeline(LocatorSkeleton<L> locatorSkeleton, ResourceLocation resourceLocation, float time){
        AnimationPose<L> animationPose = AnimationPose.of(locatorSkeleton);
        for(Enum<L> locator : locatorSkeleton.getLocators()){
            animationPose.setJointPose(locator, JointPose.getJointPoseFromChannelTimeline(resourceLocation, locator.toString(), time));
        }
        return animationPose;
    }
}
