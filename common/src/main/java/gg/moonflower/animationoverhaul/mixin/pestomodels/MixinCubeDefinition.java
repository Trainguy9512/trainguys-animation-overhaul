package gg.moonflower.animationoverhaul.mixin.pestomodels;

import com.mojang.math.Vector3f;
import gg.moonflower.animationoverhaul.access.CubeDefinitionAccess;
import gg.moonflower.animationoverhaul.access.CubeDeformationAccess;
import net.minecraft.client.model.geom.builders.CubeDefinition;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(CubeDefinition.class)
public class MixinCubeDefinition implements CubeDefinitionAccess {
    @Shadow @Final private Vector3f dimensions;

    @Shadow @Final private Vector3f origin;

    @Shadow @Final private CubeDeformation grow;

    public void outputString() {
        String cubeString = "cube = cmds.polyCube(width=" + this.dimensions.x() + ", height=" + this.dimensions.y() + ", depth=" + this.dimensions.z() + ")";
        System.out.println(cubeString);
        if(((CubeDeformationAccess)this.grow).getGrow() > 0){
            String growString = "cmds.polyExtrudeFacet(cube, thickness=" + ((CubeDeformationAccess)this.grow).getGrow() + ")";
            System.out.println(growString);
        }
        String xformString = "cmds.xform(cube, relative=True, translation=[" + (-(this.dimensions.x() / 2) - this.origin.x()) + "," + (-(this.dimensions.y() / 2) - this.origin.y())  + "," + (-(this.dimensions.z() / 2) - this.origin.z())  + "])";
        System.out.println(xformString);
        String parentString = "cmds.parent(cube, modelPartGroup)";
        System.out.println(parentString);
    }
}
