package gg.moonflower.animationoverhaul.util.data;

import com.google.common.collect.Maps;
import gg.moonflower.animationoverhaul.animations.LivingEntityAnimator;
import net.minecraft.world.entity.EntityType;

import java.util.HashMap;

public class LivingEntityAnimatorRegistry {

    private final HashMap<EntityType<?>, LivingEntityAnimator<?, ?>> livingEntityAnimatorHashMap = Maps.newHashMap();

    public LivingEntityAnimatorRegistry(){
    }

    public void register(EntityType<?> entityType, LivingEntityAnimator<?, ?> livingEntityAnimator){
        livingEntityAnimatorHashMap.put(entityType, livingEntityAnimator);
    }

    public boolean contains(EntityType<?> entityType){
        return livingEntityAnimatorHashMap.containsKey(entityType);
    }

    public LivingEntityAnimator<?, ?> get(EntityType<?> entityType){
        return livingEntityAnimatorHashMap.get(entityType);
    }
}
