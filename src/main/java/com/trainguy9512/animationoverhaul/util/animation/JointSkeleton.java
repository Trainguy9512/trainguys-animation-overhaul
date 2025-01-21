package com.trainguy9512.animationoverhaul.util.animation;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import net.minecraft.client.model.geom.PartPose;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Structure used for associating locator enums with data such as transform hierarchy, default offset poses, model parts, and mirrors.
 */
public class JointSkeleton {

    private final HashMap<String, JointConfiguration> jointHashMap = Maps.newHashMap();
    private final Enum<L> rootLocator;

    public JointSkeleton(Enum<L> rootLocator){
        this.rootLocator = rootLocator;
        this.addLocator(rootLocator);
    }

    public JointSkeleton(){
        this.rootLocator = null;
    }

    /**
     * Initializes a locator skeleton with a root locator
     *
     * @param rootLocator The locator to be used as the hierarchical root
     * @return This locator skeleton
     */
    public static <L extends Enum<L>> JointSkeleton<L> of(Enum<L> rootLocator){
        return new JointSkeleton<L>(rootLocator);
    }


    /**
     * Adds a locator to a newly created locator skeleton
     *
     * @param locator The locator to be added
     */

    private void addLocator(Enum<L>locator){
        this.jointHashMap.put(locator, new JointConfiguration<L>(locator));
    }

    public JointSkeleton<L> addChildToParent(Enum<L> locatorChild, Enum<L> locatorParent){
        if(this.jointHashMap.containsKey(locatorParent)){
            this.addLocator(locatorChild);
            this.jointHashMap.get(locatorParent).addChild(locatorChild);
            this.jointHashMap.get(locatorChild).setParent(locatorParent);
        }
        return this;
    }

    public JointSkeleton<L> addChildToRoot(Enum<L> locatorChild){
        return this.addChildToParent(locatorChild, this.rootLocator);
    }

    public List<Enum<L>> getLocatorChildren(Enum<L> locator){
        return this.jointHashMap.get(locator).getChildren();
    }

    public boolean jointIsParentOfChild(Enum<L> parent, Enum<L> child){

        while(this.jointHashMap.get(child).getParent() != null){
            Enum<L> currentChildParent = this.jointHashMap.get(child).getParent();
            if(currentChildParent == parent){
                return true;
            } else {
                child = currentChildParent;
            }
        }

        return false;
    }



    //TODO: SEARCH HEIARCHY FOR IF CHILD IS PARENTED UNDER JOINT!!!!!!!!!








    public void printHierarchy(){
        printHierarchyChild(this.getRootLocator(), 1);
        AnimationOverhaulMain.LOGGER.info("--".concat(this.getRootLocator().toString()));
    }

    private void printHierarchyChild(Enum<L> locator, int size){
        String dashes = "";
        for(int i = 0; i <= size; i++){
            dashes = dashes.concat("--");
        }
        for (Enum<L> child : this.jointHashMap.get(locator).getChildren()){
            AnimationOverhaulMain.LOGGER.info(dashes.concat(child.toString()));
            printHierarchyChild(child, size + 1);
        }


    }

    public Enum<L> getRootLocator(){
        return this.rootLocator;
    }

    /**
     * Returns the set of locator enums from the locator skeleton
     *
     * @return The set of enums
     */
    public Set<Enum<L>> getLocators(){
        return jointHashMap.keySet();
    }

    /**
     * Returns the default part pose of a provided locator enum, used for offsetting transforms after the animation is calculated
     *
     * @param locator The locator enum to get the default pose of
     * @return The default part pose
     */
    public PartPose getLocatorDefaultPose(Enum<L> locator){
        return jointHashMap.get(locator).defaultPose;
    }

    /**
     * Called upon construction of this skeleton, this sets the mirrored locator of a specific locator.
     * For example, the mirror locator of the left leg would be the right leg.
     *
     * @param locator The enum locator target
     * @param mirrored The enum locator that the target will use as a mirror
     * @return This locator skeleton
     */
    public JointSkeleton<L> setLocatorMirror(Enum<L> locator, Enum<L> mirrored){
        this.jointHashMap.get(locator).setMirrorJoint(mirrored);
        this.jointHashMap.get(mirrored).setMirrorJoint(locator);
        return this;
    }

    /**
     * Returns the model part identifier of a locator enum. Used for associating locators with Minecraft entity model parts
     *
     * @param locator The enum locator
     * @return The string model part identifier
     */
    public String getLocatorModelPartIdentifier(Enum<L> locator){
        return this.jointHashMap.get(locator).getModelPartIdentifier();
    }

    /**
     * Gets whether a locator enum from the skeleton is associated with a model part or not.
     *
     * @param locator The enum locator target
     * @return True or false
     */
    public boolean getLocatorUsesModelPart(Enum<L> locator){
        return this.jointHashMap.get(locator).getUsesModelPart();
    }

    /**
     * Called upon construction of this skeleton, this sets the default offset pose for a locator enum.
     *
     * @param locator The enum locator target
     * @param pose The part pose used as the default pose
     * @return This skeleton
     */
    public JointSkeleton<L> setDefaultJointTransform(Enum<L> locator, PartPose pose){
        this.jointHashMap.get(locator).setDefaultPose(pose);
        return this;
    }

    /**
     * Called upon construction of this skeleton, this associates a locator enum with a Minecraft model part string identifier.
     *
     * @param locator The enum locator target
     * @param modelPartIdentifier The model part identifier associated with the locator
     * @return This skeleton
     */
    public JointSkeleton<L> setLocatorModelPart(Enum<L> locator, String modelPartIdentifier){
        this.jointHashMap.get(locator).setModelPartIdentifier(modelPartIdentifier);
        return this;
    }

    /*

    public void addLocatorModelPart(String locator, String locatorMirrored, String modelPartIdentifier, PartPose defaultPose){
        locatorHashMap.putIfAbsent(locator, new LocatorEntry(locator, locatorMirrored, modelPartIdentifier, defaultPose));
    }

    public void addLocatorModelPart(String locator, String modelPartIdentifier, PartPose defaultPose){
        addLocatorModelPart(locator, locator, modelPartIdentifier, defaultPose);
    }

    public void addLocatorModelPart(String locator, String locatorMirrored, String modelPartIdentifier){
        addLocatorModelPart(locator, locatorMirrored, modelPartIdentifier, PartPose.ZERO);
    }

    public void addLocatorModelPart(String locator, String modelPartIdentifier){
        addLocatorModelPart(locator, modelPartIdentifier, PartPose.ZERO);
    }

    public void addLocator(String locator, String locatorMirrored){
        locatorHashMap.putIfAbsent(locator, new LocatorEntry(locator, locatorMirrored, null, PartPose.ZERO));
    }

    public void addLocator(String locator){
        addLocator(locator, locator);
    }

     */

    /**
     * Gets the locator enum directly mirrored to the input locator. If there is no mirror locator set, it returns the input.
     *
     * @param locator The enum locator target
     * @return The mirrored enum locator
     */
    public String getMirroredLocator(String locator){
        return this.jointHashMap.get(locator).getMirrorJoint();
        return this.jointHashMap.get(locator)
    }

    /*
    @Nullable
    private getLocatorEntry(String identifier){
        return this.locatorHashMap.get(identifier);
    }

     */

    /*
    public boolean containsLocator(String identifier){
        return this.locatorHashMap.containsKey(identifier);
    }

     */

    public static class Builder {

        private final HashMap<String, JointConfiguration.Builder> joints = Maps.newHashMap();
        private final String rootJoint;

        protected Builder(String root){
            this.rootJoint = root;
        }
    }


    public record JointConfiguration(String parent, List<String> children, boolean usesMirrorJoint, String mirrorJoint, boolean usesModelPart, String modelPartIdentifier, PartPose modelPartOffset) {

        public static class Builder {

            private final String parent;
            private List<String> children;
            private boolean usesMirrorJoint;
            private String mirrorJoint;
            private boolean usesModelPart;
            private String modelPartIdentifier;
            private PartPose modelPartOffset;

            private Builder(String parent){
                this.parent = parent;
                this.children = List.of();
                this.usesMirrorJoint = false;
                this.mirrorJoint = null;
                this.usesModelPart = false;
                this.modelPartIdentifier = null;
                this.modelPartOffset = PartPose.ZERO;
            }

            public static Builder of(String parent){
                return new Builder(parent);
            }

        }

        public String getModelPartIdentifier(){
            return this.usesModelPart ? this.modelPartIdentifier : "null";
        }

        public boolean getUsesModelPart(){
            return this.usesModelPart;
        }

        public void addChild(Enum<L> locator){
            if(!this.children.contains(locator)){
                this.children.add(locator);
            }
        }

        public List<Enum<L>> getChildren(){
            return this.children;
        }

        public void setParent(Enum<L> joint){
            this.parent = joint;
        }

        @Nullable
        public Enum<L> getParent(){
            return this.parent;
        }
    }
}
