package com.trainguy.animationoverhaul.animations;

import com.trainguy.animationoverhaul.access.EntityAccess;
import com.trainguy.animationoverhaul.util.animation.LivingEntityAnimParams;
import com.trainguy.animationoverhaul.util.animation.PartAnimationUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PlayerPartAnimator<T extends LivingEntity, M extends EntityModel<T>> extends LivingEntityPartAnimator<T, M> {

    private final PlayerModel<T> playerModel;

    private final List<ModelPart> partListAll;
    private final HashMap<ModelPart, String[]> modelPartDictionary;

    //TODO: add cases for handling inventory and hand animations
    //TODO: pass animation parameters to these

    public PlayerPartAnimator(T livingEntity, M model, LivingEntityAnimParams livingEntityAnimParams){
        super(livingEntity, model, livingEntityAnimParams);
        playerModel = (PlayerModel<T>)model;
        partListAll = Arrays.asList(playerModel.leftArm, playerModel.rightArm, playerModel.leftLeg, playerModel.rightLeg, playerModel.body, playerModel.head);

        modelPartDictionary = new HashMap<>(){{
            put(playerModel.head, new String[]{"head", "head"});
            put(playerModel.body, new String[]{"body", "body"});
            put(playerModel.leftLeg, new String[]{"leftLeg", "rightLeg"});
            put(playerModel.rightLeg, new String[]{"rightLeg", "leftLeg"});
            put(playerModel.leftArm, new String[]{"leftArm", "rightArm"});
            put(playerModel.rightArm, new String[]{"rightArm", "leftArm"});
        }};
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
        //addPoseLayerLook();
    }

    private void addPoseLayerLook(){
        float lookHorizontalTimer = 1 - Mth.clamp((livingEntityAnimParams.getHeadYRot() / Mth.HALF_PI) + 0.5F, 0, 1);
        ((EntityAccess)livingEntity).setAnimationTimer("head_yrot_raw", livingEntityAnimParams.getHeadYRot());
        ((EntityAccess)livingEntity).setAnimationTimer("head_yrot_timer", lookHorizontalTimer);
        PartAnimationUtils.animateMultiplePartsAdditive(partListAll, getTimelineGroup("player", "look_horizontal"), modelPartDictionary, lookHorizontalTimer, 1, false);
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
}
