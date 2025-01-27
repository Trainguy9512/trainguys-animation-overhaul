package com.trainguy9512.animationoverhaul.animation.animator.entity;

import com.trainguy9512.animationoverhaul.access.ModelAccess;
import com.trainguy9512.animationoverhaul.animation.animator.JointAnimator;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;

public interface EntityJointAnimator<T extends Entity, S extends EntityRenderState> extends JointAnimator<T> {

    /**
     * Does some final operations on the entity model, similar to what would happen in the {@link EntityModel#setupAnim(EntityRenderState)} function. Called every frame after pose interpolation.\
     * @param entityRenderState Entity render state
     * @param rootModelPart     Root model part of the entity model
     */
    public void postProcessModelParts(S entityRenderState, ModelPart rootModelPart);
}
