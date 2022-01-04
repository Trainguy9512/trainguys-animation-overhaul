package gg.moonflower.animationoverhaul.animations;

import gg.moonflower.animationoverhaul.AnimationOverhaulMain;
import gg.moonflower.animationoverhaul.util.animation.Locator;
import gg.moonflower.animationoverhaul.util.data.TimelineGroupData;
import gg.moonflower.animationoverhaul.util.time.TimerProcessor;
import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.monster.ZombifiedPiglin;

import java.util.Arrays;
import java.util.List;

public class ZombifiedPiglinAnimator extends LivingEntityAnimator<ZombifiedPiglin, PiglinModel<ZombifiedPiglin>> {

    private final Locator locatorMaster = new Locator("master");
    private final Locator locatorHead = new Locator("head");
    private final Locator locatorBody = new Locator("body");
    private final Locator locatorLeftLeg = new Locator("leftLeg");
    private final Locator locatorRightLeg = new Locator("rightLeg");
    private final Locator locatorLeftArm = new Locator("leftArm");
    private final Locator locatorRightArm = new Locator("rightArm");
    private final Locator locatorLeftHand = new Locator("leftHand");
    private final Locator locatorRightHand = new Locator("rightHand");
    private final Locator locatorLeftEar = new Locator("leftEar");
    private final Locator locatorRightEar = new Locator("rightEar");

    private ModelPart leftEarModelPart;

    private final List<Locator> locatorListAll = Arrays.asList(locatorLeftArm, locatorRightArm, locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorLeftHand, locatorRightHand, locatorLeftEar, locatorRightEar);

    public ZombifiedPiglinAnimator() {
    }

    @Override
    public void setProperties(ZombifiedPiglin zombifiedPiglin, PiglinModel<ZombifiedPiglin> model, float tickProgress) {
        super.setProperties(zombifiedPiglin, model, tickProgress);

        this.leftEarModelPart = this.model.head.getChild("left_ear");

        this.locatorRig.addLocator(locatorMaster);
        this.locatorRig.addLocatorModelPart(locatorHead, this.model.head);
        this.locatorRig.addLocatorModelPart(locatorBody, this.model.body);
        this.locatorRig.addLocatorModelPart(locatorLeftLeg, locatorRightLeg, this.model.leftLeg);
        this.locatorRig.addLocatorModelPart(locatorRightLeg, locatorLeftLeg, this.model.rightLeg);
        this.locatorRig.addLocatorModelPart(locatorLeftArm, locatorRightArm, this.model.leftArm);
        this.locatorRig.addLocatorModelPart(locatorRightArm, locatorLeftArm, this.model.rightArm);
        this.locatorRig.addLocatorModelPart(locatorLeftEar, locatorRightEar, this.leftEarModelPart);
        this.locatorRig.addLocatorModelPart(locatorRightEar, locatorLeftEar, this.model.rightEar);
        this.locatorRig.addLocator(locatorLeftHand, locatorRightHand);
        this.locatorRig.addLocator(locatorRightHand, locatorLeftHand);

        AnimationOverhaulMain.debugEntity = zombifiedPiglin;
    }

    @Override
    public void adjustTimers(ZombifiedPiglin zombifiedPiglin) {
        super.adjustTimers(zombifiedPiglin);
    }

    @Override
    protected void animateParts() {
        addPoseLayerLook();
        addPoseLayerIdle();
        addPoseLayerWalk();
    }

    private void addPoseLayerLook(){
        locatorHead.xRot = this.headXRot;
        locatorHead.yRot = this.headYRot;
    }

    private void addPoseLayerIdle(){
        TimelineGroupData.TimelineGroup idleTimelineGroup = getTimelineGroup("idle_1");
        float idleWeight = 1 - (Math.min(getAnimationTimer(ANIMATION_SPEED) / 0.5F, 1));
        float idleTimer = new TimerProcessor(this.tickAtFrame)
                .repeat(idleTimelineGroup)
                .getValue();

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, idleTimelineGroup, idleTimer, idleWeight, isLeftHanded());
    }

    private void addPoseLayerWalk(){
        TimelineGroupData.TimelineGroup walkTimelineGroup = getTimelineGroup("walk_1");
        float walkWeight = Math.min(getAnimationTimer(ANIMATION_SPEED) / 0.5F, 1);
        float walkTimer = new TimerProcessor(getAnimationTimer(ANIMATION_POSITION))
                .speedUp(2.5F)
                .repeat(walkTimelineGroup)
                .getValue();

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, walkTimelineGroup, walkTimer, walkWeight, isLeftHanded());
    }

    @Override
    protected void finalizeModel() {
        this.model.leftArm.x += 5;
        this.model.leftArm.y += 2;
        this.model.rightArm.x -= 5;
        this.model.rightArm.y += 2;
        this.model.leftLeg.x += 1.95;
        this.model.leftLeg.y += 12;
        this.model.rightLeg.x -= 1.95;
        this.model.rightLeg.y += 12;
        this.leftEarModelPart.x += 5;
        this.leftEarModelPart.y -= 6;
        this.model.rightEar.x -= 5;
        this.model.rightEar.y -= 6;

        this.model.hat.copyFrom(this.model.head);
        this.model.leftPants.copyFrom(this.model.leftLeg);
        this.model.rightPants.copyFrom(this.model.rightLeg);
        this.model.leftSleeve.copyFrom(this.model.leftArm);
        this.model.rightSleeve.copyFrom(this.model.rightArm);
        this.model.jacket.copyFrom(this.model.body);
    }
}
