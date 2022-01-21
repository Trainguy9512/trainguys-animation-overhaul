package gg.moonflower.animationoverhaul.animations;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import gg.moonflower.animationoverhaul.AnimationOverhaulMain;
import gg.moonflower.animationoverhaul.animations.entity.LivingEntityPartAnimator;
import gg.moonflower.animationoverhaul.util.data.EntityAnimationData;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;
import java.util.UUID;

public class AnimatorDispatcher {
    public static final AnimatorDispatcher INSTANCE = new AnimatorDispatcher();

    private final HashMap<UUID, EntityAnimationData> entityAnimationDataMap = Maps.newHashMap();

    public AnimatorDispatcher(){
    }

    public void tickEntity(LivingEntity livingEntity, LivingEntityPartAnimator<?, ?> livingEntityPartAnimator){
        if(!entityAnimationDataMap.containsKey(livingEntity.getUUID())){
            entityAnimationDataMap.put(livingEntity.getUUID(), new EntityAnimationData());
        }
        livingEntityPartAnimator.tick(livingEntity, entityAnimationDataMap.get(livingEntity.getUUID()));
    }

    public <T extends LivingEntity, M extends EntityModel<T>> boolean animateEntity(T livingEntity, M entityModel, PoseStack poseStack, float partialTicks){
        if(entityAnimationDataMap.containsKey(livingEntity.getUUID())){
            if(AnimationOverhaulMain.ENTITY_ANIMATORS.contains(livingEntity.getType())){
                LivingEntityPartAnimator<T, M> livingEntityPartAnimator = (LivingEntityPartAnimator<T, M>) AnimationOverhaulMain.ENTITY_ANIMATORS.get(livingEntity.getType());
                livingEntityPartAnimator.animate(livingEntity, entityModel, poseStack, entityAnimationDataMap.get(livingEntity.getUUID()), partialTicks);
                return true;
            }
        }
        return false;
    }
}
