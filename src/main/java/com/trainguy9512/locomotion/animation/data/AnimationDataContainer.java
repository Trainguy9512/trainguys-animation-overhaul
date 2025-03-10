package com.trainguy9512.locomotion.animation.data;

import com.google.common.collect.Maps;
import com.trainguy9512.locomotion.animation.animator.JointAnimator;
import com.trainguy9512.locomotion.animation.driver.Driver;
import com.trainguy9512.locomotion.animation.driver.VariableDriver;
import com.trainguy9512.locomotion.animation.driver.DriverKey;
import com.trainguy9512.locomotion.animation.joint.JointSkeleton;
import com.trainguy9512.locomotion.animation.pose.LocalSpacePose;
import com.trainguy9512.locomotion.animation.pose.function.PoseFunction;
import com.trainguy9512.locomotion.animation.pose.function.cache.SavedCachedPoseContainer;
import com.trainguy9512.locomotion.util.Interpolator;

import java.util.Map;

public class AnimationDataContainer implements PoseCalculationDataContainer, OnTickDriverContainer {

    private final Map<DriverKey<? extends Driver<?>>, Driver<?>> drivers;
    private final SavedCachedPoseContainer savedCachedPoseContainer;
    private final PoseFunction<LocalSpacePose> poseFunction;

    private final JointSkeleton jointSkeleton;
    private final DriverKey<VariableDriver<LocalSpacePose>> perTickCalculatedPoseDriverKey;
    private final DriverKey<VariableDriver<Long>> gameTimeTicksDriverKey;

    private AnimationDataContainer(JointAnimator<?> jointAnimator){
        this.drivers = Maps.newHashMap();
        this.savedCachedPoseContainer = SavedCachedPoseContainer.of();
        this.poseFunction = jointAnimator.constructPoseFunction(savedCachedPoseContainer).wrapUnique();

        this.jointSkeleton = jointAnimator.buildSkeleton();
        this.perTickCalculatedPoseDriverKey = DriverKey.of("per_tick_calculated_pose", () -> VariableDriver.ofInterpolatable(() -> LocalSpacePose.of(jointSkeleton), Interpolator.LOCAL_SPACE_POSE));
        this.gameTimeTicksDriverKey = DriverKey.of("game_time", () -> VariableDriver.ofConstant(() -> 0L));
    }

    public static AnimationDataContainer of(JointAnimator<?> jointAnimator){
        return new AnimationDataContainer(jointAnimator);
    }

    @Override
    public JointSkeleton getJointSkeleton(){
        return this.jointSkeleton;
    }

    public DriverKey<VariableDriver<LocalSpacePose>> getPerTickCalculatedPoseDriverKey(){
        return this.perTickCalculatedPoseDriverKey;
    }

    public void tick(){
        this.drivers.values().forEach(Driver::tick);
        this.getDriver(this.gameTimeTicksDriverKey).setValue(this.getDriver(this.gameTimeTicksDriverKey).getValueCurrent() + 1);
        this.poseFunction.tick(PoseFunction.FunctionEvaluationState.of(this, false, this.getDriver(this.gameTimeTicksDriverKey).getValueCurrent()));
    }

    public LocalSpacePose computePose(float partialTicks){
        this.savedCachedPoseContainer.clearCaches();
        return this.poseFunction.compute(PoseFunction.FunctionInterpolationContext.of(
                this,
                partialTicks,
                (this.getDriverValue(gameTimeTicksDriverKey, 1) - (1 - partialTicks)) / 20f
        ));
    }

    @Override
    public <D, R extends Driver<D>> D getDriverValue(DriverKey<R> driverKey, float partialTicks) {
        return this.getDriver(driverKey).getValueInterpolated(partialTicks);
    }

    @Override
    public <D, R extends Driver<D>> D getDriverValue(DriverKey<R> driverKey) {
        return this.getDriverValue(driverKey, 1);
    }

    public void prepareForNextTick(){
        this.drivers.values().forEach(Driver::prepareForNextTick);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <D, R extends Driver<D>> R getDriver(DriverKey<R> driverKey) {
        return (R) this.drivers.computeIfAbsent(driverKey, DriverKey::createInstance);
    }
}
