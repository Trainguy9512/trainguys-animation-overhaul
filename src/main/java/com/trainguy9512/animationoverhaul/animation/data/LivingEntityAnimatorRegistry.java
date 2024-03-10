package com.trainguy9512.animationoverhaul.animation.data;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.entity.LivingEntityAnimator;
import net.minecraft.world.entity.EntityType;

import java.util.HashMap;

public class LivingEntityAnimatorRegistry {

    private final HashMap<EntityType<?>, LivingEntityAnimator<?, ?, ?>> livingEntityPartAnimatorHashMap = Maps.newHashMap();

    public LivingEntityAnimatorRegistry(){
    }

    public void register(EntityType<?> entityType, LivingEntityAnimator<?, ?, ?> livingEntityPartAnimator){
        livingEntityPartAnimatorHashMap.put(entityType, livingEntityPartAnimator);
    }

    public boolean contains(EntityType<?> entityType){
        return livingEntityPartAnimatorHashMap.containsKey(entityType);
    }

    public LivingEntityAnimator<?, ?, ?> get(EntityType<?> entityType){
        return livingEntityPartAnimatorHashMap.get(entityType);
    }
}
