package gg.moonflower.animationoverhaul.animations.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import gg.moonflower.animationoverhaul.access.ModelAccess;
import gg.moonflower.animationoverhaul.util.animation.LocatorRig;
import gg.moonflower.animationoverhaul.util.data.EntityAnimationData;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class LivingEntityPartAnimator<T extends LivingEntity, M extends EntityModel<T>> {

    protected T livingEntity;
    protected M entityModel;
    protected final LocatorRig locatorRig;

    protected EntityAnimationData entityAnimationData;
    protected float partialTicks = 0;

    public LivingEntityPartAnimator(){
        this.locatorRig = new LocatorRig();
        buildRig(this.locatorRig);
    }

    public void setEntity(T livingEntity){
        this.livingEntity = livingEntity;
    }

    public void setEntityModel(M entityModel){
        this.entityModel = entityModel;
    }

    protected void buildRig(LocatorRig locatorRig){

    }

    public void tick(LivingEntity livingEntity, EntityAnimationData entityAnimationData){

    }

    protected void poseLocatorRig(){

    }

    protected void finalizeModelParts(ModelPart rootModelPart){

    }

    public void animate(T livingEntity, M entityModel, PoseStack poseStack, EntityAnimationData entityAnimationData, float partialTicks){
        setEntity(livingEntity);
        setEntityModel(entityModel);
        this.entityAnimationData = entityAnimationData;
        this.locatorRig.resetRig();
        this.partialTicks = partialTicks;

        poseLocatorRig();
        ModelPart rootModelPart = ((ModelAccess)this.entityModel).getRootModelPart();
        this.locatorRig.bakeRig(rootModelPart);
        finalizeModelParts(rootModelPart);
    }
}
