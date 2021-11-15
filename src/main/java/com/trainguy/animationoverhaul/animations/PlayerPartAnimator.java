package com.trainguy.animationoverhaul.animations;

import com.trainguy.animationoverhaul.access.EntityAccess;
import com.trainguy.animationoverhaul.util.PartAnimationUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PlayerPartAnimator<T extends LivingEntity, M extends EntityModel<T>> extends LivingEntityPartAnimator<T, M> {

    private final PlayerModel<T> playerModel;

    private final List<ModelPart> partListAll;

    //TODO: add cases for handling inventory and hand animations
    //TODO: pass animation parameters to these

    public PlayerPartAnimator(T livingEntity, M model){
        super(livingEntity, model);
        playerModel = (PlayerModel<T>)model;

        partListAll = Arrays.asList(playerModel.leftArm, playerModel.rightArm, playerModel.leftLeg, playerModel.rightLeg, playerModel.body, playerModel.head);
    }

    @Override
    protected void initModel() {
        for(ModelPart part : partListAll){
            part.setPos(0, 0, 0);
            part.setRotation(0, 0, 0);
        }
    }

    @Override
    protected void adjustTimers() {

    }

    @Override
    protected void animateParts() {

        ((EntityAccess) livingEntity).resetTimerOnCondition("test", livingEntity.isCrouching(), 83);
        float testTimer = ((EntityAccess) livingEntity).getAnimationTimer("test");
        PartAnimationUtils.animateMultiplePartsAdditive(partListAll, getTimelineGroup("player", "test"), getModelPartDictionary(), testTimer, 1, true);

    }

    @Override
    protected void finalizeModel() {
        playerModel.leftArm.x += 5;
        playerModel.leftArm.y += 2;
        playerModel.rightArm.x += -5;
        playerModel.rightArm.y += 2;
        playerModel.leftLeg.x += 1.95F;
        playerModel.leftLeg.y += 12;
        playerModel.rightLeg.x += -1.95F;
        playerModel.rightLeg.y += 12;
    }

    protected HashMap<ModelPart, String[]> getModelPartDictionary(){
        HashMap<ModelPart, String[]> hashMap = new HashMap<>();
        hashMap.put(playerModel.head, new String[]{"head", "head"});
        hashMap.put(playerModel.body, new String[]{"body", "body"});
        hashMap.put(playerModel.leftLeg, new String[]{"leftLeg", "rightLeg"});
        hashMap.put(playerModel.rightLeg, new String[]{"rightLeg", "leftLeg"});
        hashMap.put(playerModel.leftArm, new String[]{"leftArm", "rightArm"});
        hashMap.put(playerModel.rightArm, new String[]{"rightArm", "leftArm"});
        return hashMap;
    }
}
