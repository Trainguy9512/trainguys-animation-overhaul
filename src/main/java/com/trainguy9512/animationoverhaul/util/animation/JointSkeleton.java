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

    private final HashMap<String, JointConfiguration> joints;
    private final String rootJoint;

    private JointSkeleton(HashMap<String, JointConfiguration> joints, String rootJoint){
        this.joints = joints;
        this.rootJoint = rootJoint;
    }


    /**
     * Returns a list of joint identifiers that are direct children of the supplied joint.
     * @param joint Joint to search for children of.
     * @return List of
     */
    public List<String> getDirectChildrenOfJoint(String joint){
        return this.joints.get(joint).children();
    }

    /**
     * Returns whether the supplied parent joint is a parent of the supplied child joint.
     * @param parent Parent joint identifier
     * @param child Child joint identifier
     */
    public boolean jointIsParentOfChild(String parent, String child){

        while(this.joints.get(child).parent() != null){
            String currentChildParent = this.joints.get(child).parent();
            if(Objects.equals(currentChildParent, parent)){
                return true;
            } else {
                child = currentChildParent;
            }
        }

        return false;
    }
    //TODO: SEARCH HEIARCHY FOR IF CHILD IS PARENTED UNDER JOINT!!!!!!!!! (2025 update: why?)

    public void printHierarchy(){
        printHierarchyChild(this.getRootJoint(), 1);
        AnimationOverhaulMain.LOGGER.info("--".concat(this.getRootJoint()));
    }

    private void printHierarchyChild(String joint, int size){
        String dashes = "";
        for(int i = 0; i <= size; i++){
            dashes = dashes.concat("--");
        }
        for (String child : this.getDirectChildrenOfJoint(joint)){
            AnimationOverhaulMain.LOGGER.info(dashes.concat(child));
            printHierarchyChild(child, size + 1);
        }


    }

    public String getRootJoint(){
        return this.rootJoint;
    }

    /**
     * Returns a set of all joints used by the joint skeleton.
     *
     * @return Set of string joint identifiers
     */
    public Set<String> getLocators(){
        return joints.keySet();
    }

    /**
     * Returns model part pose offset for the specified joint, used for offsetting transforms after the animation is calculated
     *
     * @return The model part pose offset
     */
    public PartPose getLocatorDefaultPose(String joint){
        return joints.get(joint).modelPartOffset();
    }

    /**
     * Returns the model part identifier of a locator enum. Used for associating locators with Minecraft entity model parts
     *
     * @return The string model part identifier
     */
    public String getLocatorModelPartIdentifier(String joint){
        return this.joints.get(joint).modelPartIdentifier();
    }

    /**
     * Returns whether a joint is associated with a model part or not.
     */
    public boolean getLocatorUsesModelPart(String joint){
        return this.joints.get(joint).usesModelPart();
    }

    /**
     * Gets the joint mirrored to the input joint. If there is no mirror joint set, it returns the input joint.
     *
     * @return The mirrored string joint
     */
    public String getMirrorJoint(String joint){
        if(this.joints.get(joint).usesMirrorJoint()){
            return this.joints.get(joint).mirrorJoint();
        } else {
            return joint;
        }
    }

    public static class Builder {

        private final HashMap<String, JointConfiguration.Builder> joints = Maps.newHashMap();
        private final String rootJoint;

        protected Builder(String root){
            this.rootJoint = root;
            this.joints.put(root, JointConfiguration.Builder.of(null));
        }

        public Builder addJointUnderRoot(String joint){
            this.joints.putIfAbsent(joint, JointConfiguration.Builder.of(this.rootJoint));
            return this;
        }

        public Builder addJointUnderParent(String joint, String parent){
            if(this.joints.containsKey(parent)){
                this.joints.putIfAbsent(joint, JointConfiguration.Builder.of(parent));
            } else {
                AnimationOverhaulMain.LOGGER.warn("Joint {} not added due to parent joint {} not being present in the skeleton.", joint, parent);
            }
            return this;
        }

        public Builder setModelPartOffset(String joint, PartPose modelPartOffset){
            if(this.joints.containsKey(joint)){
                this.joints.get(joint).setModelPartOffset(modelPartOffset);
            } else {
                AnimationOverhaulMain.LOGGER.warn("Model part offset not set during joint skeleton construction for joint {}, due to joint {} not being defined in the skeleton.", joint, joint);
            }
            return this;
        }

        public Builder setMirrorJoint(String joint, String mirrorJoint){
            if(this.joints.containsKey(joint)){
                this.joints.get(joint).setMirrorJoint(mirrorJoint);
            } else {
                AnimationOverhaulMain.LOGGER.warn("Mirror joint not set during joint skeleton construction for joint {}, due to joint {} not being defined in the skeleton.", joint, joint);
            }
            return this;
        }

        public JointSkeleton build(){
            HashMap<String, JointConfiguration> jointsBuilt = Maps.newHashMap();
            for(String joint : this.joints.keySet()){
                jointsBuilt.put(joint, this.joints.get(joint).build());
            }
            return new JointSkeleton(jointsBuilt, this.rootJoint);
        }
    }


    public record JointConfiguration(boolean isRoot, String parent, List<String> children, boolean usesMirrorJoint, String mirrorJoint, boolean usesModelPart, String modelPartIdentifier, PartPose modelPartOffset) {

        public static class Builder {
            private final boolean isRoot;
            private final String parent;
            private final List<String> children;
            private boolean usesMirrorJoint;
            private String mirrorJoint;
            private boolean usesModelPart;
            private String modelPartIdentifier;
            private PartPose modelPartOffset;

            private Builder(String parent){
                this.isRoot = parent == null;
                this.parent = parent;
                this.children = List.of();
                this.usesMirrorJoint = false;
                this.mirrorJoint = null;
                this.usesModelPart = false;
                this.modelPartIdentifier = null;
                this.modelPartOffset = PartPose.ZERO;
            }

            public static Builder of(@Nullable String parent){
                return new Builder(parent);
            }

            public Builder addChild(String child){
                this.children.add(child);
                return this;
            }

            public Builder setMirrorJoint(String mirorJoint){
                if(mirrorJoint != null){
                    this.mirrorJoint = mirorJoint;
                    this.usesMirrorJoint = true;
                }
                return this;
            }

            public Builder setModelPartIdentifier(String modelPartIdentifier){
                if(modelPartIdentifier != null){
                    this.modelPartIdentifier = modelPartIdentifier;
                    this.usesModelPart = true;
                }
                return this;
            }

            public Builder setModelPartOffset(PartPose modelPartOffset){
                this.modelPartOffset = modelPartOffset;
                return this;
            }

            protected JointConfiguration build(){
                return new JointConfiguration(this.isRoot, this.parent, this.children, this.usesMirrorJoint, this.mirrorJoint, this.usesModelPart, this.modelPartIdentifier, this.modelPartOffset);
            }
        }
    }
}
