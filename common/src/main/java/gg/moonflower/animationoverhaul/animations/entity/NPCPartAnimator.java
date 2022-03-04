package gg.moonflower.animationoverhaul.animations.entity;

import gg.moonflower.animationoverhaul.util.animation.Locator;
import gg.moonflower.animationoverhaul.util.data.EntityAnimationData;
import gg.moonflower.animationoverhaul.util.data.TimelineGroupData;
import gg.moonflower.animationoverhaul.util.time.TimerProcessor;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;

import java.util.List;

public class NPCPartAnimator<T extends LivingEntity, M extends EntityModel<T>> extends LivingEntityPartAnimator<T, M> {

    protected static final EntityAnimationData.DataKey<Float> WALK_TO_STOP = new EntityAnimationData.DataKey<>("walk_to_stop", 0F);
    protected static final EntityAnimationData.DataKey<Float> AGGRO_WEIGHT = new EntityAnimationData.DataKey<>("aggro_weight", 0F);
    protected static final EntityAnimationData.DataKey<Float> AGGRO_TIMER = new EntityAnimationData.DataKey<>("aggro_timer", 0F);


    protected void tickWalkToStopTimer(EntityAnimationData entityAnimationData, float threshold, int durationInTicks){
        entityAnimationData.incrementInTicksOrResetFromCondition(WALK_TO_STOP, entityAnimationData.getValue(ANIMATION_SPEED) > threshold || entityAnimationData.getValue(ANIMATION_SPEED_Y) > 0, durationInTicks);
    }

    protected void tickAggroTimers(EntityAnimationData entityAnimationData, LivingEntity livingEntity, float aggroTimerFrameDuration, int aggroWeightTickDuration){
        if(livingEntity instanceof Monster){
            entityAnimationData.incrementInTicksFromCondition(AGGRO_WEIGHT, ((Monster) livingEntity).isAggressive(), aggroWeightTickDuration, aggroWeightTickDuration);
            entityAnimationData.incrementInFramesOrResetFromCondition(AGGRO_TIMER, !((Monster) livingEntity).isAggressive(), aggroTimerFrameDuration);
        }
    }

    protected void poseHeadRotation(Locator headLocator){
        headLocator.rotateX = getDataValue(HEAD_X_ROT);
        headLocator.rotateY = getDataValue(HEAD_Y_ROT);
    }

    protected void poseLookLean(List<Locator> locatorList, TimelineGroupData.TimelineGroup verticalTimelineGroup, TimelineGroupData.TimelineGroup horizontalTimelineGroup){
        this.locatorRig.animateMultipleLocatorsAdditive(locatorList, verticalTimelineGroup, getLookUpDownTimer(LEAN_X_ROT), 1);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorList, horizontalTimelineGroup, getLookLeftRightTimer(LEAN_Y_ROT), 1);
    }

    protected void poseWalkToStop(List<Locator> locatorList, TimelineGroupData.TimelineGroup timelineGroup){
        this.locatorRig.animateMultipleLocatorsAdditive(locatorList, timelineGroup, getDataValue(WALK_TO_STOP), 1);
    }

    protected void poseAggro(List<Locator> locatorList, TimelineGroupData.TimelineGroup loopTimelineGroup, TimelineGroupData.TimelineGroup startTimelineGroup){
        this.locatorRig.animateMultipleLocatorsAdditive(locatorList, loopTimelineGroup, new TimerProcessor(this.livingEntity.tickCount).repeat(loopTimelineGroup).getValue(), getDataValue(AGGRO_WEIGHT), false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorList, startTimelineGroup, getDataValue(AGGRO_TIMER), 1, false);
    }
}
