package gg.moonflower.animationoverhaul.animations.entity;

import gg.moonflower.animationoverhaul.access.ModelAccess;
import gg.moonflower.animationoverhaul.util.animation.Locator;
import gg.moonflower.animationoverhaul.util.animation.LocatorRig;
import gg.moonflower.animationoverhaul.util.data.EntityAnimationData;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Arrays;
import java.util.List;

public class PlayerPartAnimator extends LivingEntityPartAnimator<Player, PlayerModel<Player>> {

    private Locator locatorMaster;
    private Locator locatorHead;
    private Locator locatorBody;
    private Locator locatorLeftLeg;
    private Locator locatorRightLeg;
    private Locator locatorLeftArm;
    private Locator locatorRightArm;
    private Locator locatorCloak;
    private Locator locatorLeftHand;
    private Locator locatorRightHand;

    private List<Locator> locatorListAll;
    private List<Locator> locatorListNoHands;
    private List<Locator> locatorListMaster;
    public PlayerPartAnimator(){
        super();
    }

    @Override
    protected void buildRig(LocatorRig locatorRig) {
        super.buildRig(locatorRig);


        this.locatorMaster = new Locator("master");
        this.locatorHead = new Locator("head");
        this.locatorBody = new Locator("body");
        this.locatorLeftLeg = new Locator("leftLeg");
        this.locatorRightLeg = new Locator("rightLeg");
        this.locatorLeftArm = new Locator("leftArm");
        this.locatorRightArm = new Locator("rightArm");
        this.locatorCloak = new Locator("cloak");
        this.locatorLeftHand = new Locator("leftHand");
        this.locatorRightHand = new Locator("rightHand");

        this.locatorListAll = List.of(locatorLeftArm, locatorRightArm, locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorCloak, locatorLeftHand, locatorRightHand);
        this.locatorListNoHands = List.of(locatorLeftArm, locatorRightArm, locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorCloak);
        this.locatorListMaster = List.of(locatorLeftArm, locatorRightArm, locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorCloak, locatorLeftHand, locatorRightHand, locatorMaster);

        locatorRig.addLocator(locatorMaster);
        locatorRig.addLocatorModelPart(locatorHead, "head");
        locatorRig.addLocatorModelPart(locatorBody, "body");
        locatorRig.addLocatorModelPart(locatorLeftLeg, locatorRightLeg, "left_leg", PartPose.offset(1.9f, 12.0f, 0.0f));
        locatorRig.addLocatorModelPart(locatorRightLeg, locatorLeftLeg, "right_leg", PartPose.offset(-1.9f, 12.0f, 0.0f));
        locatorRig.addLocatorModelPart(locatorLeftArm, locatorRightArm, "left_arm", PartPose.offset(5.0f, 2.0f, 0.0f));
        locatorRig.addLocatorModelPart(locatorRightArm, locatorLeftArm, "right_arm", PartPose.offset(-5.0f, 2.0f, 0.0f));
        locatorRig.addLocatorModelPart(locatorCloak, "cloak", PartPose.offsetAndRotation(0, 0, 2, 0, Mth.PI, 0));
        locatorRig.addLocator(locatorLeftHand, locatorRightHand);
        locatorRig.addLocator(locatorRightHand, locatorLeftHand);
    }

    @Override
    protected void poseLocatorRig() {
        super.poseLocatorRig();
    }

    @Override
    public void tick(LivingEntity livingEntity, EntityAnimationData entityAnimationData) {
    }

    @Override
    protected void finalizeModelParts(ModelPart rootModelPart) {
        rootModelPart.getChild("left_pants").copyFrom(rootModelPart.getChild("left_leg"));
        rootModelPart.getChild("right_pants").copyFrom(rootModelPart.getChild("right_leg"));
        rootModelPart.getChild("left_sleeve").copyFrom(rootModelPart.getChild("left_arm"));
        rootModelPart.getChild("right_sleeve").copyFrom(rootModelPart.getChild("right_arm"));
        rootModelPart.getChild("hat").copyFrom(rootModelPart.getChild("head"));
    }
}
