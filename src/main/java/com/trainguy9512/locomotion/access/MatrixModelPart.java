package com.trainguy9512.locomotion.access;

import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.gen.Accessor;

public interface MatrixModelPart {
    void locomotion$setMatrix(Matrix4f matrix4f);
    Matrix4f locomotion$getMatrix();
}
