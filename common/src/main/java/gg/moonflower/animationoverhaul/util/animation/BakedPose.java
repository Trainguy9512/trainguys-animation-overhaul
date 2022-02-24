package gg.moonflower.animationoverhaul.util.animation;

import com.google.common.collect.Maps;
import gg.moonflower.animationoverhaul.AnimationOverhaulMain;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.util.Mth;

import java.util.HashMap;
import java.util.Objects;

public class BakedPose {

    private HashMap<LocatorRig.LocatorEntry, PartPose> pose = Maps.newHashMap();
    private HashMap<LocatorRig.LocatorEntry, PartPose> poseOld = Maps.newHashMap();
    public boolean hasPose;

    public BakedPose(){
        this.hasPose = false;
    }

    public void pushToOld(){
        this.poseOld = pose;
    }

    public void setPose(HashMap<LocatorRig.LocatorEntry, PartPose> hashMap){
        this.pose = hashMap;
    }

    public void bakeToModelParts(ModelPart modelPart, float partialTicks){
        for(LocatorRig.LocatorEntry locatorEntry : pose.keySet()){
            if(locatorEntry.modelPartIdentifier != null){
                ModelPart finalModelPart = modelPart;
                for(String individualPartString : locatorEntry.modelPartIdentifier.split("\\.")){
                    finalModelPart = finalModelPart.getChild(individualPartString);
                }
                PartPose partPose = lerpPartPose(partialTicks, poseOld.get(locatorEntry), pose.get(locatorEntry));
                finalModelPart.loadPose(partPose);
            }
        }
    }

    private static PartPose lerpPartPose(float value, PartPose partPoseOld, PartPose partPose){
        if(partPose.xRot - partPoseOld.xRot > Mth.PI){
            partPose = PartPose.offsetAndRotation(partPose.x, partPose.y, partPose.z, partPose.xRot - Mth.TWO_PI, partPose.yRot, partPose.zRot);
        }
        if(partPose.xRot - partPoseOld.xRot < -Mth.PI){
            partPose = PartPose.offsetAndRotation(partPose.x, partPose.y, partPose.z, partPose.xRot + Mth.TWO_PI, partPose.yRot, partPose.zRot);
        }
        if(Math.abs(partPose.xRot - partPoseOld.xRot) > Mth.PI){
            AnimationOverhaulMain.LOGGER.warn("Snapping on the X axis of {} degrees", Math.toDegrees(Math.abs(partPose.xRot - partPoseOld.xRot)));
        }
        if(Math.abs(partPose.yRot - partPoseOld.yRot) > Mth.PI){
            AnimationOverhaulMain.LOGGER.warn("Snapping on the Y axis of {} degrees", Math.toDegrees(Math.abs(partPose.yRot - partPoseOld.yRot)));
        }
        if(Math.abs(partPose.zRot - partPoseOld.zRot) > Mth.PI){
            AnimationOverhaulMain.LOGGER.warn("Snapping on the Z axis of {} degrees", Math.toDegrees(Math.abs(partPose.zRot - partPoseOld.zRot)));
        }
        return PartPose.offsetAndRotation(
                Mth.lerp(value, partPoseOld.x, partPose.x),
                Mth.lerp(value, partPoseOld.y, partPose.y),
                Mth.lerp(value, partPoseOld.z, partPose.z),
                Mth.rotLerp(value, partPoseOld.xRot, partPose.xRot),
                Mth.rotLerp(value, partPoseOld.yRot, partPose.yRot),
                Mth.rotLerp(value, partPoseOld.zRot, partPose.zRot)
        );
    }

    /*
    private static Locator lerpLocator(float value, Locator locatorOld, Locator locator){
        Locator locatorNew = new Locator(locator.getIdentifier());
        locatorNew.translateX = Mth.lerp(value, locatorOld.translateX, locator.translateX);
        locatorNew.translateY = Mth.lerp(value, locatorOld.translateY, locator.translateY);
        locatorNew.translateZ = Mth.lerp(value, locatorOld.translateZ, locator.translateZ);
        locatorNew.rotateX = Mth.rotLerp(value, locatorOld.rotateX, locator.rotateX);
        locatorNew.rotateY = Mth.rotLerp(value, locatorOld.rotateY, locator.rotateY);
        locatorNew.rotateZ = Mth.rotLerp(value, locatorOld.rotateZ, locator.rotateZ);
        return locatorNew;
    }

     */

    public Locator getLocator(String identifier, float partialTicks){

        for(LocatorRig.LocatorEntry locatorEntry : this.pose.keySet()){
            if(Objects.equals(locatorEntry.getLocator().getIdentifier(), identifier)){
                return Locator.fromPartPose(lerpPartPose(partialTicks, this.poseOld.get(locatorEntry), this.pose.get(locatorEntry)), identifier);
            }
        }
        return new Locator("null");
    }

    public boolean containsLocator(String identifier){
        for(LocatorRig.LocatorEntry locatorEntry : this.pose.keySet()){
            if(locatorEntry.getLocator().getIdentifier() == identifier){
                return true;
            }
        }
        return false;
    }
}
