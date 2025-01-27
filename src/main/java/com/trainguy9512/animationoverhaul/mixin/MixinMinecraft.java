package com.trainguy9512.animationoverhaul.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    /*

    @Inject(method = "startAttack", at = @At("HEAD"))
    public void injectOnStartAttack(CallbackInfoReturnable<Boolean> cir){
        FirstPersonPlayerJointAnimator.INSTANCE.localAnimationDataContainer.setValue(FirstPersonPlayerJointAnimator.IS_ATTACKING, true);
    }

    @Inject(method = "startUseItem", at = @At("HEAD"))
    public void injectOnStartUseItem(CallbackInfo ci){
        FirstPersonPlayerJointAnimator.INSTANCE.localAnimationDataContainer.setValue(FirstPersonPlayerJointAnimator.IS_USING_ITEM, true);
    }

    @Inject(method = "continueAttack", at = @At("HEAD"))
    public void injectOnContinueAttackIsNotMining(boolean bl, CallbackInfo ci){
        FirstPersonPlayerJointAnimator.INSTANCE.localAnimationDataContainer.setValue(FirstPersonPlayerJointAnimator.IS_MINING, false);
    }

    @Inject(method = "continueAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleEngine;crack(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)V"))
    public void injectOnContinueAttackIsMining(boolean bl, CallbackInfo ci){
        FirstPersonPlayerJointAnimator.INSTANCE.localAnimationDataContainer.setValue(FirstPersonPlayerJointAnimator.IS_MINING, true);
    }

     */
}
