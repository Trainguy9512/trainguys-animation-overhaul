package gg.moonflower.animationoverhaul.animations;

import gg.moonflower.animationoverhaul.access.EntityAccess;
import gg.moonflower.animationoverhaul.access.LivingEntityAccess;
import gg.moonflower.animationoverhaul.util.animation.Locator;
import gg.moonflower.animationoverhaul.util.animation.LocatorRig;
import gg.moonflower.animationoverhaul.util.data.EntityAnimationData;
import gg.moonflower.animationoverhaul.util.data.TimelineGroupData;
import gg.moonflower.animationoverhaul.util.time.Easing;
import gg.moonflower.animationoverhaul.util.time.TimerProcessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.FlyingAnimal;

import java.util.List;
import java.util.Random;

public class LivingEntityAnimator<T extends LivingEntity, M extends EntityModel<T>> extends AbstractEntityAnimator<T, M> {

    protected T livingEntity;
    protected M model;
    protected LocatorRig locatorRig;

    protected float partialTicks;
    protected float tickAtFrame;
    protected float headXRot;
    protected float headYRot;
    protected float delta = 0;

    protected final Random random = new Random();

    protected static final EntityAnimationData.DataKey<Float> DELTA_Y = new EntityAnimationData.DataKey<>("delta_y", 0F);
    protected static final EntityAnimationData.DataKey<Float> DELTA_Y_OLD = new EntityAnimationData.DataKey<>("delta_y_old", 0F);
    //protected static final String ANIMATION_SPEED = "animation_speed";
    protected static final EntityAnimationData.DataKey<Float> ANIMATION_SPEED = new EntityAnimationData.DataKey<>("animation_speed", 0F);
    protected static final EntityAnimationData.DataKey<Float> ANIMATION_SPEED_Y = new EntityAnimationData.DataKey<>("animation_speed_y", 0F);
    protected static final EntityAnimationData.DataKey<Float> ANIMATION_SPEED_XYZ = new EntityAnimationData.DataKey<>("animation_speed_xyz", 0F);
    protected static final EntityAnimationData.DataKey<Float> ANIMATION_POSITION = new EntityAnimationData.DataKey<>("animation_position", 0F);
    protected static final EntityAnimationData.DataKey<Float> ANIMATION_POSITION_Y = new EntityAnimationData.DataKey<>("animation_position_y", 0F);
    protected static final EntityAnimationData.DataKey<Float> ANIMATION_POSITION_XYZ = new EntityAnimationData.DataKey<>("animation_position_xyz", 0F);
    protected static final EntityAnimationData.DataKey<Float> HURT_TIMER = new EntityAnimationData.DataKey<>("hurt_timer", 0F);
    protected static final EntityAnimationData.DataKey<Integer> HURT_INDEX = new EntityAnimationData.DataKey<>("hurt_index", 0);
    protected static final EntityAnimationData.DataKey<Float> DEATH_TIMER = new EntityAnimationData.DataKey<>("death_timer", 0F);
    protected static final EntityAnimationData.DataKey<Integer> DEATH_INDEX = new EntityAnimationData.DataKey<>("death_index", 0);
    protected static final EntityAnimationData.DataKey<Float> SLEEP_TIMER = new EntityAnimationData.DataKey<>("sleep_timer", 0F);
    protected static final EntityAnimationData.DataKey<Float> FLYING_SPEED = new EntityAnimationData.DataKey<>("flying_speed", 0F);
    protected static final EntityAnimationData.DataKey<Float> FLYING_POSITION = new EntityAnimationData.DataKey<>("flying_position", 0F);
    public static final EntityAnimationData.DataKey<Float> BODY_Y_ROT = new EntityAnimationData.DataKey<>("body_y_rot", 0F);
    protected static final EntityAnimationData.DataKey<Float> TURNING_LEFT_WEIGHT = new EntityAnimationData.DataKey<>("turning_left_weight", 0F);
    protected static final EntityAnimationData.DataKey<Float> TURNING_RIGHT_WEIGHT = new EntityAnimationData.DataKey<>("turning_right_weight", 0F);

    public LivingEntityAnimator(){
    }

    public void setProperties(T livingEntity, M model, float tickProgress){
        this.model = model;
        this.livingEntity = livingEntity;
        this.partialTicks = tickProgress;
        this.locatorRig = new LocatorRig();
        setHeadVariables(tickProgress);
    }

    public void animate(T livingEntity, M model, float partialTicks){
        this.model = model;
        this.livingEntity = livingEntity;
        this.tickAtFrame = livingEntity.tickCount + partialTicks;
        this.locatorRig.resetRig();
        if(((LivingEntityAccess)livingEntity).getUseInventoryRenderer()){
            this.animatePartsInventory();
            ((LivingEntityAccess)livingEntity).setUseInventoryRenderer(false);
        } else {
            this.animateParts();
        }
        this.bakeLocatorRig();
        this.finalizeModel();
    }

    @Override
    public void tick(T livingEntity) {
        this.livingEntity = livingEntity;
        adjustGeneralMovementTimers();
        adjustAnimationSpeedTimers();
        //adjustBodyYRotTimers();
    }

    public void setPartialTicks(float f){
        this.partialTicks = f;
    }

    @Override
    protected void animateParts() {

    }

    @Override
    protected void finalizeModel() {

    }

    @Override
    protected void animatePartsInventory() {

    }

    public void bakeLocatorRig(){
        ((LivingEntityAccess)livingEntity).storeLocatorRig(this.locatorRig);
        //this.locatorRig.bakeRig();
    }

    protected void adjustGeneralMovementTimers(){
        float deltaY = (float) (livingEntity.getY() - livingEntity.yo);
        float deltaYOld = getAnimationTimer(DELTA_Y);
        setAnimationTimer(DELTA_Y, deltaY);
        setAnimationTimer(DELTA_Y_OLD, deltaYOld);
    }

    private void adjustAnimationSpeedTimers(){
        boolean useVerticalVector = livingEntity instanceof FlyingAnimal;

        float previousAnimationSpeed = getEntityAnimationData().get(ANIMATION_SPEED).get();
        float previousFlyingSpeed = getAnimationTimer(FLYING_SPEED);
        float previousAnimationSpeedY = getAnimationTimer(ANIMATION_SPEED_Y);
        float previousAnimationSpeedXYZ = getAnimationTimer(ANIMATION_SPEED_XYZ);
        float previousAnimationPosition = getAnimationTimer(ANIMATION_POSITION);
        float previousFlyingPosition = getAnimationTimer(FLYING_POSITION);
        float previousAnimationPositionY = getAnimationTimer(ANIMATION_POSITION_Y);
        float previousAnimationPositionXYZ = getAnimationTimer(ANIMATION_POSITION_XYZ);

        double deltaX = livingEntity.getX() - livingEntity.xo;
        double deltaY = livingEntity.getY() - livingEntity.yo;
        double deltaZ = livingEntity.getZ() - livingEntity.zo;
        float movementSquared = (float)Math.sqrt(deltaX * deltaX + deltaZ * deltaZ) * 4.0F;
        float movementSquaredY = (float)Math.sqrt(deltaY * deltaY) * 4.0F;
        float movementSquaredXYZ = (float)Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ) * 4.0F;
        float movementSquaredFlying = (float)Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ) * 1.5F;
        if (movementSquared > 1.0F) {
            movementSquared = 1.0F;
        }
        if (movementSquaredY > 1.0F) {
            movementSquaredY = 1.0F;
        }
        if (movementSquaredXYZ > 1.0F) {
            movementSquaredXYZ = 1.0F;
        }
        if (movementSquaredFlying > 1.0F) {
            movementSquaredFlying = 1.0F;
        }

        float finalAnimationSpeed = previousAnimationSpeed + ((movementSquared - previousAnimationSpeed) * 0.4F * this.delta);
        float finalAnimationSpeedY = previousAnimationSpeedY + ((movementSquaredY - previousAnimationSpeedY) * 0.4F * this.delta);
        float finalAnimationSpeedXYZ = previousAnimationSpeedXYZ + ((movementSquaredXYZ - previousAnimationSpeedXYZ) * 0.4F * this.delta);
        float finalAnimationSpeedFlying = previousFlyingSpeed + ((movementSquaredFlying - previousFlyingSpeed) * 0.4F * this.delta);
        getEntityAnimationData().get(ANIMATION_SPEED).set(finalAnimationSpeed);
        //setAnimationTimer(ANIMATION_SPEED, finalAnimationSpeed);
        setAnimationTimer(ANIMATION_SPEED_Y, finalAnimationSpeedY);
        setAnimationTimer(ANIMATION_SPEED_XYZ, finalAnimationSpeedXYZ);
        setAnimationTimer(FLYING_SPEED, finalAnimationSpeedFlying);
        setAnimationTimer(ANIMATION_POSITION, previousAnimationPosition + finalAnimationSpeed * this.delta);
        setAnimationTimer(ANIMATION_POSITION_Y, previousAnimationPositionY + finalAnimationSpeedY * this.delta);
        setAnimationTimer(ANIMATION_POSITION_XYZ, previousAnimationPositionXYZ + finalAnimationSpeedXYZ * this.delta);
        setAnimationTimer(FLYING_POSITION, previousFlyingPosition + finalAnimationSpeedFlying * this.delta);
    }

    private void adjustBodyYRotTimers(){
        float previousBodyYRot = getAnimationTimer(BODY_Y_ROT);
        float targetBodyYRot = livingEntity.yBodyRot;
        boolean isMoving = getAnimationTimer(ANIMATION_SPEED_XYZ) > 0.05F
                || livingEntity.isVisuallySwimming()
                || livingEntity.isVisuallyCrawling()
                || livingEntity.getFallFlyingTicks() > 0;
        float increment = this.delta * (isMoving ? 20 : 8);

        float difference = Mth.abs(targetBodyYRot - previousBodyYRot);
        float newBodyYRot;
        boolean turningLeft = false;
        boolean turningRight = false;

        if(difference < increment){
            newBodyYRot = targetBodyYRot;
        } else {
            if(targetBodyYRot < previousBodyYRot){
                turningLeft = !isMoving;
            } else {
                turningRight = !isMoving;
            }
            float additional360 = 0;
            newBodyYRot = targetBodyYRot < previousBodyYRot ? previousBodyYRot - increment - additional360 : previousBodyYRot + increment + additional360;
        }
        incrementAnimationTimer(TURNING_LEFT_WEIGHT, turningLeft, 8, -8);
        incrementAnimationTimer(TURNING_RIGHT_WEIGHT, turningRight, 8, -8);
        setAnimationTimer(BODY_Y_ROT, newBodyYRot);
    }

    private void setHeadVariables(float tickAtFrame){
        float h = Mth.rotLerp(tickAtFrame, livingEntity.yBodyRotO, livingEntity.yBodyRot);
        float j = Mth.rotLerp(tickAtFrame, livingEntity.yHeadRotO, livingEntity.yHeadRot);
        float k = j - h;
        float o;
        if (livingEntity.isPassenger() && livingEntity.getVehicle() instanceof LivingEntity livingEntity2) {
            h = Mth.rotLerp(tickAtFrame, livingEntity2.yBodyRotO, livingEntity2.yBodyRot);
            k = j - h;
            o = Mth.wrapDegrees(k);
            if (o < -85.0F) {
                o = -85.0F;
            }

            if (o >= 85.0F) {
                o = 85.0F;
            }

            h = j - o;
            if (o * o > 2500.0F) {
                h += o * 0.2F;
            }

            k = j - h;
        }

        this.headXRot = (float) Math.toRadians(Mth.lerp(tickAtFrame, livingEntity.xRotO, livingEntity.getXRot()));
        this.headYRot = (float) Math.toRadians(k);
    }

    protected TimelineGroupData.TimelineGroup getTimelineGroup(String animationKey){
        String entityKey = livingEntity.getType().toString().split("\\.")[2];
        return TimelineGroupData.loadedData.get(entityKey, animationKey);
    }

    protected EntityAnimationData getEntityAnimationData(){
        return ((EntityAccess)this.livingEntity).getEntityAnimationData();
    }

    protected <D> D getAnimationTimer(EntityAnimationData.DataKey<D> dataKey){
        return getEntityAnimationData().get(dataKey).get();
    }

    protected <D> void setAnimationTimer(EntityAnimationData.DataKey<D> dataKey, D value){
        getEntityAnimationData().get(dataKey).set(value);
    }

    protected float getEasedAnimationTimer(EntityAnimationData.DataKey<Float> dataKey, Easing easing){
        return easing.ease(getAnimationTimer(dataKey));
    }

    protected float getEasedConditionAnimationTimer(EntityAnimationData.DataKey<Float> dataKey, Easing easing1, Easing easing2, boolean condition){
        return condition ? easing1.ease(getAnimationTimer(dataKey)) : easing2.ease(getAnimationTimer(dataKey));
    }

    protected float getAnimationTimerEasedQuad(EntityAnimationData.DataKey<Float> dataKey){
        return getEasedAnimationTimer(dataKey, Easing.CubicBezier.bezierInOutQuad());
    }

    protected void incrementAnimationTimer(EntityAnimationData.DataKey<Float> dataKey, boolean isIncreasing, int ticksToIncrement, int ticksToDecrement) {
        if(ticksToIncrement != 0 && ticksToDecrement != 0){
            incrementAnimationTimer(dataKey, isIncreasing, (1/(float)ticksToIncrement), (1/(float)ticksToDecrement));
        }
    }

    protected void incrementAnimationTimer(EntityAnimationData.DataKey<Float> dataKey, boolean isIncreasing, float increment, float decrement){
        incrementAnimationTimer(dataKey, isIncreasing, increment, decrement, 0, 1);
    }

    protected void incrementAnimationTimer(EntityAnimationData.DataKey<Float> dataKey, boolean isIncreasing, float increment, float decrement, float min, float max){
        float previousTimerValue = getAnimationTimer(dataKey);
        float delta = Minecraft.getInstance().getDeltaFrameTime();
        setAnimationTimer(dataKey, Mth.clamp(previousTimerValue + (isIncreasing ? increment * delta : decrement * delta), min, max));
    }

    protected void resetTimerOnCondition(EntityAnimationData.DataKey<Float> dataKey, boolean condition, int ticksToIncrement){
        if(condition){
            setAnimationTimer(dataKey, 0F);
        } else {
            incrementAnimationTimer(dataKey, true, ticksToIncrement, 10);
        }
    }

    protected float getLookLeftRightTimer(){
        return Mth.clamp((this.headYRot / Mth.HALF_PI) + 0.5F, 0, 1);
    }

    protected float getLookUpDownTimer(){
        return Mth.clamp((this.headXRot / Mth.PI) + 0.5F, 0, 1);
    }

    protected boolean isLeftHanded(){
        return livingEntity.getMainArm() == HumanoidArm.LEFT;
    }

    @Deprecated
    protected void resetRandomAnimation(EntityAnimationData.DataKey<Float> dataKey, EntityAnimationData.DataKey<Integer> indexDataKey, boolean condition, int numberOfAnimations, int ticksToIncrement){
        if(condition && getAnimationTimer(dataKey) > 0.5 && numberOfAnimations > 1){
            setAnimationTimer(indexDataKey, random.nextInt(numberOfAnimations));
        }
        resetTimerOnCondition(dataKey, condition && getAnimationTimer(dataKey) > 0.5, ticksToIncrement);
    }

    protected void adjustHurtTimers(int numberOfTimers){
        resetRandomAnimation(HURT_TIMER, HURT_INDEX, livingEntity.hurtTime == 10, numberOfTimers, 10);
    }

    protected void adjustDeathTimer(){
        resetTimerOnCondition(DEATH_TIMER, livingEntity.deathTime == 0, 19);
    }

    protected void adjustSleepTimer(){
        resetTimerOnCondition(SLEEP_TIMER, ((Entity)this.livingEntity).getPose() != Pose.SLEEPING, (int) TimerProcessor.framesToTicks(24));
    }

    protected void addPoseLayerHurt(List<TimelineGroupData.TimelineGroup> timelineGroupList, List<Locator> locatorList){
        this.locatorRig.animateMultipleLocatorsAdditive(locatorList, timelineGroupList.get(getAnimationTimer(HURT_INDEX)), getAnimationTimer(HURT_TIMER), 1, false);
    }

    // Add after everything but before damage animations
    protected void addPoseLayerDeath(TimelineGroupData.TimelineGroup timelineGroup, List<Locator> locatorList){
        this.locatorRig.weightedClearTransforms(locatorList, Math.min(getAnimationTimer(DEATH_TIMER) * 4, 1));
        this.locatorRig.animateMultipleLocatorsAdditive(locatorList, timelineGroup, getAnimationTimer(DEATH_TIMER), 1, false);
    }
}
