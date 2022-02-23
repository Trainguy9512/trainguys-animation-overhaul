package gg.moonflower.animationoverhaul.animations.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import gg.moonflower.animationoverhaul.access.ModelAccess;
import gg.moonflower.animationoverhaul.animations.AnimatorDispatcher;
import gg.moonflower.animationoverhaul.util.animation.Locator;
import gg.moonflower.animationoverhaul.util.animation.LocatorRig;
import gg.moonflower.animationoverhaul.util.data.EntityAnimationData;
import gg.moonflower.animationoverhaul.util.data.TimelineGroupData;
import gg.moonflower.animationoverhaul.util.time.Easing;
import gg.moonflower.animationoverhaul.util.time.TimerProcessor;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.FlyingAnimal;

import java.util.List;
import java.util.Random;

public class LivingEntityPartAnimator<T extends LivingEntity, M extends EntityModel<T>> {

    protected T livingEntity;
    protected M entityModel;
    protected final LocatorRig locatorRig;

    protected EntityAnimationData entityAnimationData;
    protected float partialTicks = 0;
    protected final Random random = new Random();

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

        AnimatorDispatcher.INSTANCE.saveLocatorRig(livingEntity.getUUID(), this.locatorRig);
    }

    /**
     * Shortcut method for calling a timeline group
     * <p>
     * For use within the scope of {@link #poseLocatorRig()}
     * @param identifier    Mod identifier for the mod adding the timeline group
     * @param animationKey  Animation key used to identify the timeline group
     * @return              Timeline group object used for posing locator rigs
     */
    protected TimelineGroupData.TimelineGroup getTimelineGroup(String identifier, String animationKey){
        return TimelineGroupData.INSTANCE.get(identifier, this.livingEntity.getType(), animationKey);
    }

    /**
     * Shortcut method for retrieving a data value at the current tick
     * <p>
     * For use within the scope of {@link #poseLocatorRig()}
     * @param dataKey       Data key used to obtain the object from the entity animation data
     * @return              Object specified in data key object type
     */
    protected <D> D getDataValue(EntityAnimationData.DataKey<D> dataKey){
        return this.entityAnimationData.getValue(dataKey);
    }

    /**
     * Shortcut method for retrieving an interpolated float value at the current frame
     * <p>
     * For use within the scope of {@link #poseLocatorRig()}
     * @param dataKey       Float data key to obtain the value with
     * @return              Interpolated float for values inbetween ticks
     */
    protected float getDataValueLerped(EntityAnimationData.DataKey<Float> dataKey){
        return this.entityAnimationData.getLerped(dataKey, this.partialTicks);
    }

    /**
     * Shortcut method for retrieving an interpolated float value at the current frame and then applying it to an easing
     * <p>
     * For use within the scope of {@link #poseLocatorRig()}
     *
     * @param dataKey       Float data key to obtain the value with
     * @param easing        Easing object to apply the interpolated float onto
     * @return              Interpolated float for values inbetween ticks
     */
    protected float getDataValueEased(EntityAnimationData.DataKey<Float> dataKey, Easing easing){
        return this.entityAnimationData.getEased(dataKey, easing, this.partialTicks);
    }

    protected float getDataValueEasedQuad(EntityAnimationData.DataKey<Float> dataKey){
        return getDataValueEased(dataKey, Easing.CubicBezier.bezierInOutQuad());
    }

    protected float getDataValueEasedCondition(EntityAnimationData.DataKey<Float> dataKey, Easing easing1, Easing easing2, boolean condition){
        return getDataValueEased(dataKey, condition ? easing1 : easing2);
    }

    /**
     * Shortcut method for obtaining whether the current entity is left-handed or not
     * <p>
     * For use within the scope of {@link #poseLocatorRig()}
     */
    protected boolean isLeftHanded(){
        return this.livingEntity.getMainArm() == HumanoidArm.LEFT;
    }

    protected float getLookLeftRightTimer(){
        return Mth.clamp((getDataValueLerped(HEAD_Y_ROT) / Mth.HALF_PI) + 0.5F, 0, 1);
    }

    protected float getLookUpDownTimer(){
        return Mth.clamp((getDataValueLerped(HEAD_X_ROT) / Mth.PI) + 0.5F, 0, 1);
    }

    protected static final EntityAnimationData.DataKey<Float> HEAD_X_ROT = new EntityAnimationData.DataKey<>("head_x_rot", 0F);
    protected static final EntityAnimationData.DataKey<Float> HEAD_Y_ROT = new EntityAnimationData.DataKey<>("head_y_rot", 0F);
    protected static final EntityAnimationData.DataKey<Float> DELTA_Y = new EntityAnimationData.DataKey<>("delta_y", 0F);
    protected static final EntityAnimationData.DataKey<Float> DELTA_Y_OLD = new EntityAnimationData.DataKey<>("delta_y_old", 0F);
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

    protected void tickGeneralMovementTimers(LivingEntity livingEntity, EntityAnimationData entityAnimationData){
        float deltaY = (float) (livingEntity.getY() - livingEntity.yo);
        float deltaYOld = entityAnimationData.getValue(DELTA_Y);
        entityAnimationData.setValue(DELTA_Y, deltaY);
        entityAnimationData.setValue(DELTA_Y_OLD, deltaYOld);
        tickAnimationSpeedTimers(livingEntity, entityAnimationData);
    }

    private void tickAnimationSpeedTimers(LivingEntity livingEntity, EntityAnimationData entityAnimationData){
        boolean useVerticalVector = livingEntity instanceof FlyingAnimal;

        float previousAnimationSpeed = entityAnimationData.getValue(ANIMATION_SPEED);
        float previousFlyingSpeed =  entityAnimationData.getValue(FLYING_SPEED);
        float previousAnimationSpeedY =  entityAnimationData.getValue(ANIMATION_SPEED_Y);
        float previousAnimationSpeedXYZ =  entityAnimationData.getValue(ANIMATION_SPEED_XYZ);
        float previousAnimationPosition =  entityAnimationData.getValue(ANIMATION_POSITION);
        float previousFlyingPosition =  entityAnimationData.getValue(FLYING_POSITION);
        float previousAnimationPositionY =  entityAnimationData.getValue(ANIMATION_POSITION_Y);
        float previousAnimationPositionXYZ =  entityAnimationData.getValue(ANIMATION_POSITION_XYZ);

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

        float finalAnimationSpeed = previousAnimationSpeed + ((movementSquared - previousAnimationSpeed) * 0.4F);
        float finalAnimationSpeedY = previousAnimationSpeedY + ((movementSquaredY - previousAnimationSpeedY) * 0.4F);
        float finalAnimationSpeedXYZ = previousAnimationSpeedXYZ + ((movementSquaredXYZ - previousAnimationSpeedXYZ) * 0.4F);
        float finalAnimationSpeedFlying = previousFlyingSpeed + ((movementSquaredFlying - previousFlyingSpeed) * 0.4F);
        entityAnimationData.setValue(ANIMATION_SPEED, finalAnimationSpeed);
        entityAnimationData.setValue(ANIMATION_SPEED_Y, finalAnimationSpeedY);
        entityAnimationData.setValue(ANIMATION_SPEED_XYZ, finalAnimationSpeedXYZ);
        entityAnimationData.setValue(FLYING_SPEED, finalAnimationSpeedFlying);
        entityAnimationData.setValue(ANIMATION_POSITION, previousAnimationPosition + finalAnimationSpeed);
        entityAnimationData.setValue(ANIMATION_POSITION_Y, previousAnimationPositionY + finalAnimationSpeedY);
        entityAnimationData.setValue(ANIMATION_POSITION_XYZ, previousAnimationPositionXYZ + finalAnimationSpeedXYZ);
        entityAnimationData.setValue(FLYING_POSITION, previousFlyingPosition + finalAnimationSpeedFlying);
    }

    protected void tickBodyYRotTimers(LivingEntity livingEntity, EntityAnimationData entityAnimationData){
        float previousBodyYRot = entityAnimationData.getValue(BODY_Y_ROT);
        float targetBodyYRot = livingEntity.yBodyRot;
        boolean isMoving = entityAnimationData.getValue(ANIMATION_SPEED_XYZ) > 0.05F
                || livingEntity.isVisuallySwimming()
                || livingEntity.isVisuallyCrawling()
                || livingEntity.getFallFlyingTicks() > 0;
        float increment = (isMoving ? 20 : 8);

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
        entityAnimationData.incrementInTicksFromCondition(TURNING_LEFT_WEIGHT, turningLeft, 8, 8);
        entityAnimationData.incrementInTicksFromCondition(TURNING_RIGHT_WEIGHT, turningRight, 8, 8);
        entityAnimationData.setValue(BODY_Y_ROT, newBodyYRot);
    }

    protected void tickHeadTimers(LivingEntity livingEntity, EntityAnimationData entityAnimationData){
        float h = livingEntity.yBodyRot;
        float j = livingEntity.yHeadRot;
        float k = j - h;
        float o;
        if (livingEntity.isPassenger() && livingEntity.getVehicle() instanceof LivingEntity livingEntity2) {
            h = livingEntity2.yBodyRot;
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

        entityAnimationData.setValue(HEAD_X_ROT, (float) Math.toRadians(livingEntity.getXRot()));
        entityAnimationData.setValue(HEAD_Y_ROT, (float) Math.toRadians(k));
    }

    protected void tickHurtTimers(LivingEntity livingEntity, EntityAnimationData entityAnimationData, int numberOfTimers){
        entityAnimationData.incrementInTicksOrResetRandomFromCondition(HURT_TIMER, HURT_INDEX, numberOfTimers, livingEntity.hurtTime == 10, 10, this.random);
    }

    protected void tickDeathTimer(LivingEntity livingEntity, EntityAnimationData entityAnimationData){
        entityAnimationData.incrementInTicksOrResetFromCondition(DEATH_TIMER, livingEntity.deathTime == 0, 19);
    }

    protected void tickSleepTimer(LivingEntity livingEntity, EntityAnimationData entityAnimationData){
        entityAnimationData.incrementInFramesOrResetFromCondition(SLEEP_TIMER, livingEntity.getPose() != Pose.SLEEPING, 24);
    }

    protected void addPoseLayerHurt(List<TimelineGroupData.TimelineGroup> timelineGroupList, List<Locator> locatorList){
        this.locatorRig.animateMultipleLocatorsAdditive(locatorList, timelineGroupList.get(this.entityAnimationData.getValue(HURT_INDEX)), this.entityAnimationData.getLerped(HURT_TIMER, this.partialTicks), 1, false);
    }

    // Add after everything but before damage animations
    protected void addPoseLayerDeath(TimelineGroupData.TimelineGroup timelineGroup, List<Locator> locatorList){
        this.locatorRig.weightedClearTransforms(locatorList, Math.min(this.entityAnimationData.getValue(DEATH_TIMER) * 4, 1));
        this.locatorRig.animateMultipleLocatorsAdditive(locatorList, timelineGroup, this.entityAnimationData.getValue(DEATH_TIMER), 1, false);
    }
}
