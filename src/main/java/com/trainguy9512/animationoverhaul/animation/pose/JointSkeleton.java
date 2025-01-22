package com.trainguy9512.animationoverhaul.animation.pose;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import net.minecraft.client.model.geom.PartPose;
import org.apache.commons.compress.utils.Lists;
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
     * Returns a new Joint Skeleton builder.
     * @param rootJoint Name of the joint to use as the root.
     * @return Joint skeleton builder
     */
    public static JointSkeleton.Builder of(String rootJoint){
        return new JointSkeleton.Builder(rootJoint);
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

    @SuppressWarnings("unused")
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

    /**
     * Retrieves the root joint of the skeleton.
     * @return String identifier of the root joint
     */
    public String getRootJoint(){
        return this.rootJoint;
    }

    /**
     * Returns a set of all joints used by the joint skeleton.
     * @return Set of string joint identifiers
     */
    public Set<String> getJoints(){
        return joints.keySet();
    }

    /**
     * Retrieves the joint configuration for the supplied joint.
     * @param joint Joint string identifier to get a joint configuration for.
     * @return Joint configuration for the supplied joint string identifier
     */
    public JointConfiguration getJointConfiguration(String joint){
        return this.joints.get(joint);
    }

    public static class Builder {

        private final HashMap<String, JointConfiguration.Builder> joints = Maps.newHashMap();
        private final String rootJoint;

        protected Builder(String rootJoint){
            this.rootJoint = rootJoint;
            this.joints.put(rootJoint, JointConfiguration.Builder.of(null));
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
            private final ArrayList<String> children;
            private boolean usesMirrorJoint;
            private String mirrorJoint;
            private boolean usesModelPart;
            private String modelPartIdentifier;
            private PartPose modelPartOffset;

            private Builder(String parent){
                this.isRoot = parent == null;
                this.parent = parent;
                this.children = Lists.newArrayList();
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
