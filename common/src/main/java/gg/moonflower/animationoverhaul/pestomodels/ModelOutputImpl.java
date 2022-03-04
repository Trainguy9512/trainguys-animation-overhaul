package gg.moonflower.animationoverhaul.pestomodels;

import gg.moonflower.animationoverhaul.access.CubeDefinitionAccess;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.util.Mth;

public class ModelOutputImpl {

    public static void outputPartDefinition(String string, PartPose partPose, CubeListBuilder cubeListBuilder, PartPose partPose2){
        /*
        String outputStringGroup = "modelPartGroup = cmds.group(name='" + string + "', empty=True)";
        System.out.println(outputStringGroup);

        for(CubeDefinition cubeDefinition : cubeListBuilder.getCubes()){
            ((CubeDefinitionAccess)(Object)cubeDefinition).outputString();
        }

        String xformString = "cmds.xform(modelPartGroup, relative=True, translation=[" + partPose2.x + "," + -partPose2.y + "," + -partPose2.z + "], rotation=[" + Mth.RAD_TO_DEG * partPose2.xRot + "," + Mth.RAD_TO_DEG * -partPose2.yRot + "," + Mth.RAD_TO_DEG * -partPose2.zRot + "])";
        String xformString2 = "cmds.xform(modelPartGroup, relative=True, translation=[" + partPose.x + "," + -partPose.y + "," + -partPose.z + "], rotation=[" + Mth.RAD_TO_DEG * partPose.xRot + "," + Mth.RAD_TO_DEG * -partPose.yRot + "," + Mth.RAD_TO_DEG * -partPose.zRot + "])";
        System.out.println(xformString);
        System.out.println(xformString2);

         */
    }
}
