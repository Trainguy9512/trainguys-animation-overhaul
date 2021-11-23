package com.trainguy.animationoverhaul.animations;

import com.trainguy.animationoverhaul.util.data.AnimationData;
import com.trainguy.animationoverhaul.util.animation.LivingEntityAnimParams;
import com.trainguy.animationoverhaul.util.animation.PartAnimationUtils;
import com.trainguy.animationoverhaul.util.time.TimerProcessor;
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

    private static final String SPRINT_WEIGHT = "sprint_weight";
    private static final String CROUCH_WEIGHT = "crouch_weight";
    private static final String DIRECTION_SHIFT = "direction_shift";

    //TODO: add cases for handling inventory and hand animations
    //TODO: pass animation parameters to these

    public PlayerPartAnimator(T livingEntity, M model, LivingEntityAnimParams livingEntityAnimParams){
        super(livingEntity, model, livingEntityAnimParams);
        playerModel = (PlayerModel<T>)model;
        partListAll = Arrays.asList(playerModel.leftArm, playerModel.rightArm, playerModel.leftLeg, playerModel.rightLeg, playerModel.body, playerModel.head, playerModel.cloak);

        modelPartDictionary = new HashMap<>(){{
            put(playerModel.head, new String[]{"head", "head"});
            put(playerModel.body, new String[]{"body", "body"});
            put(playerModel.leftLeg, new String[]{"leftLeg", "rightLeg"});
            put(playerModel.rightLeg, new String[]{"rightLeg", "leftLeg"});
            put(playerModel.leftArm, new String[]{"leftArm", "rightArm"});
            put(playerModel.rightArm, new String[]{"rightArm", "leftArm"});
            put(playerModel.cloak, new String[]{"cloak", "cloak"});
        }};
    }

    //TODO: local translation from the body to offset the cloak when armor is being worn

    @Override
    protected void initModel() {
        for(ModelPart part : partListAll){
            part.setPos(0, 0, 0);
            part.setRotation(0, 0, 0);
        }
    }

    @Override
    protected void adjustTimers() {
        incrementAnimationTimer(SPRINT_WEIGHT, livingEntity.isSprinting(), 10, -10);
        incrementAnimationTimer(CROUCH_WEIGHT, livingEntity.isCrouching(), 10, -10);

        // Direction shift
        float previousDirectionShift = getAnimationTimer(DIRECTION_SHIFT);
        float animationSpeed = animationParameters.getAnimationSpeed();
        float moveAngleX = -Mth.sin(livingEntity.yBodyRot * Mth.PI / 180);
        float moveAngleZ = Mth.cos(livingEntity.yBodyRot * Mth.PI / 180);
        if(animationSpeed > 0.01){
            if(
                    (moveAngleX >= 0 && livingEntity.getDeltaMovement().x < 0 - 0.02 - animationSpeed * 0.03) ||
                            (moveAngleX <= 0 && livingEntity.getDeltaMovement().x > 0 + 0.02 + animationSpeed * 0.03) ||
                            (moveAngleZ >= 0 && livingEntity.getDeltaMovement().z < 0 - 0.02 - animationSpeed * 0.03) ||
                            (moveAngleZ <= 0 && livingEntity.getDeltaMovement().z > 0 + 0.02 + animationSpeed * 0.03)
            ){
                previousDirectionShift = Mth.clamp(previousDirectionShift + 0.125F * animationParameters.getDelta(), 0, 1);
            } else {
                previousDirectionShift = Mth.clamp(previousDirectionShift - 0.125F * animationParameters.getDelta(), 0, 1);;
            }
        }
        setAnimationTimer(DIRECTION_SHIFT, previousDirectionShift);
    }

    @Override
    protected void animateParts() {
        addPoseLayerLook();
        addPoseLayerWalk();
    }

    private void addPoseLayerLook(){
        setAnimationTimer("animation_speed", animationParameters.getAnimationSpeed());
        playerModel.head.xRot = animationParameters.getHeadXRot();
        playerModel.head.yRot = animationParameters.getHeadYRot();

        float lookHorizontalTimer = 1 - Mth.clamp((animationParameters.getHeadYRot() / Mth.HALF_PI) + 0.5F, 0, 1);
        float lookVerticalTimer = 1 - Mth.clamp((animationParameters.getHeadXRot() / Mth.PI) + 0.5F, 0, 1);

        PartAnimationUtils.animateMultiplePartsAdditive(partListAll, getTimelineGroup("look_horizontal"), modelPartDictionary, lookHorizontalTimer, 1, false);
        PartAnimationUtils.animateMultiplePartsAdditive(partListAll, getTimelineGroup("look_vertical"), modelPartDictionary, lookVerticalTimer, 1, false);
    }

    private void addPoseLayerWalk(){
        // Walking          0.85
        // Sprinting        1
        // Crouching        0.25

        AnimationData.TimelineGroup walkNormalTimelineGroup = getTimelineGroup("walk_normal");
        AnimationData.TimelineGroup walkCrouchTimelineGroup = getTimelineGroup("walk_crouch");

        float walkNormalTimer = new TimerProcessor(animationParameters.getAnimationPosition())
                .speedUp(1.4F)
                .repeat(walkNormalTimelineGroup)
                .getValue();
        float walkCrouchTimer = new TimerProcessor(animationParameters.getAnimationPosition())
                .speedUp(2.5F)
                .repeat(walkCrouchTimelineGroup)
                .getValue();

        float walkNormalWeight = (1 - getAnimationTimerEasedSine(SPRINT_WEIGHT)) * Math.min(animationParameters.getAnimationSpeed() / 0.86F, 1) * (1 - getAnimationTimerEasedSine(CROUCH_WEIGHT));
        float walkCrouchWeight = (1 - getAnimationTimerEasedSine(SPRINT_WEIGHT)) * Math.min(animationParameters.getAnimationSpeed() / 0.26F, 1) * getAnimationTimerEasedSine(CROUCH_WEIGHT);

        PartAnimationUtils.animateMultiplePartsAdditive(partListAll, walkNormalTimelineGroup, modelPartDictionary, walkNormalTimer, walkNormalWeight, false);
        PartAnimationUtils.animateMultiplePartsAdditive(partListAll, walkCrouchTimelineGroup, modelPartDictionary, walkCrouchTimer, walkCrouchWeight, false);
    }

    @Override
    protected void finalizeModel() {
        if(livingEntity.isCrouching()){
            for(ModelPart part : partListAll){
                part.y -= 2;
            }
        }
        playerModel.leftArm.x += 5;
        playerModel.leftArm.y += 2;
        playerModel.rightArm.x -= 5;
        playerModel.rightArm.y += 2;
        playerModel.leftLeg.x += 1.95;
        playerModel.leftLeg.y += 12;
        playerModel.rightLeg.x -= 1.95;
        playerModel.rightLeg.y += 12;
        playerModel.cloak.z += 2;
        playerModel.cloak.yRot += Mth.PI;
        playerModel.cloak.xRot = -playerModel.cloak.xRot;
    }

    @Override
    protected void adjustTimersInventory() {

    }

    @Override
    protected void animatePartsInventory() {

    }
}
