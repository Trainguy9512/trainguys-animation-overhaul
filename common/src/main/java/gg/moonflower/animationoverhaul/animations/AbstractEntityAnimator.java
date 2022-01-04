package gg.moonflower.animationoverhaul.animations;

import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.LivingEntity;

public abstract class AbstractEntityAnimator<T extends LivingEntity, M extends EntityModel<T>>{
    protected abstract void setProperties(T livingEntity, M model, float tickProgress);
    public abstract void adjustTimers(T livingEntity);
    protected abstract void animateParts();
    protected abstract void finalizeModel();
    protected abstract void animatePartsInventory();
}
