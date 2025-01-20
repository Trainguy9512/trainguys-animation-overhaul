package com.trainguy9512.animationoverhaul.animation;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.animator.entity.EntityJointAnimator;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public class LivingEntityAnimatorRegistry {

    private final HashMap<EntityType<?>, EntityJointAnimator<?, ?, ?, ?>> livingEntityPartAnimatorHashMap;

    public LivingEntityAnimatorRegistry(){
        this.livingEntityPartAnimatorHashMap = Maps.newHashMap();
    }

    public <T extends Entity> void register(EntityType<T> entityType, EntityJointAnimator<T, ?, ?, ?> livingEntityPartAnimator){
        livingEntityPartAnimatorHashMap.put(entityType, livingEntityPartAnimator);
    }

    public boolean contains(EntityType<?> entityType){
        return livingEntityPartAnimatorHashMap.containsKey(entityType);
    }

    public EntityJointAnimator<?, ?, ?, ?> get(EntityType<?> entityType){
        return this.livingEntityPartAnimatorHashMap.get(entityType);
    }
}
