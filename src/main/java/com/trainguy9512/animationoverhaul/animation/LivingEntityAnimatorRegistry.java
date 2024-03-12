package com.trainguy9512.animationoverhaul.animation;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.animator.entity.EntityJointAnimator;
import net.minecraft.world.entity.EntityType;

import java.util.HashMap;

public class LivingEntityAnimatorRegistry {

    private final HashMap<EntityType<?>, EntityJointAnimator<?, ?, ?>> livingEntityPartAnimatorHashMap = Maps.newHashMap();

    public LivingEntityAnimatorRegistry(){
    }

    public void register(EntityType<?> entityType, EntityJointAnimator<?, ?, ?> livingEntityPartAnimator){
        livingEntityPartAnimatorHashMap.put(entityType, livingEntityPartAnimator);
    }

    public boolean contains(EntityType<?> entityType){
        return livingEntityPartAnimatorHashMap.containsKey(entityType);
    }

    public EntityJointAnimator<?, ?, ?> get(EntityType<?> entityType){
        return livingEntityPartAnimatorHashMap.get(entityType);
    }
}
