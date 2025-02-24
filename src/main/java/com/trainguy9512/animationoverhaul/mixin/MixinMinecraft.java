package com.trainguy9512.animationoverhaul.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.trainguy9512.animationoverhaul.animation.animator.JointAnimatorDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {


    @Shadow @Nullable public ClientLevel level;

    @Shadow private volatile boolean pause;

    @Shadow protected abstract boolean isLevelRunningNormally();

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;popPush(Ljava/lang/String;)V", ordinal = 5))
    public void tickJointAnimators(CallbackInfo ci, @Local ProfilerFiller profilerFiller){
        profilerFiller.popPush("jointAnimatorTick");
        if (!this.pause && this.isLevelRunningNormally()) {
            // There's a condition in Minecraft.java that only allows this to run if the level != null, but the mixin does not know this.
            assert this.level != null;
            JointAnimatorDispatcher jointAnimatorDispatcher = JointAnimatorDispatcher.getInstance();
            jointAnimatorDispatcher.tickEntityJointAnimators(this.level.entitiesForRendering());
            jointAnimatorDispatcher.tickFirstPersonPlayerJointAnimator();
        }
    }

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
