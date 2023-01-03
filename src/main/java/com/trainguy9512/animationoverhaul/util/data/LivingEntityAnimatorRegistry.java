package com.trainguy9512.animationoverhaul.util.data;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.entity.LivingEntityPartAnimator;
import net.minecraft.world.entity.EntityType;

import java.util.HashMap;

public class LivingEntityAnimatorRegistry {

    private final HashMap<EntityType<?>, LivingEntityPartAnimator<?, ?>> livingEntityPartAnimatorHashMap = Maps.newHashMap();

    public LivingEntityAnimatorRegistry(){
    }

    public void register(EntityType<?> entityType, LivingEntityPartAnimator<?, ?> livingEntityPartAnimator){
        livingEntityPartAnimatorHashMap.put(entityType, livingEntityPartAnimator);
    }

    public boolean contains(EntityType<?> entityType){
        return livingEntityPartAnimatorHashMap.containsKey(entityType);
    }

    public LivingEntityPartAnimator<?, ?> get(EntityType<?> entityType){
        return livingEntityPartAnimatorHashMap.get(entityType);
    }
}
