package com.trainguy9512.animationoverhaul.util.math;

import net.minecraft.util.Mth;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class RotationMatrix {
    public float[][] matrixGrid;

    public RotationMatrix(float[][] matrixGrid){
        this.matrixGrid = matrixGrid;
    }

    public Vector3f toEulerAngles(){
        float x = (float) -Math.asin(this.matrixGrid[2][0]);
        float y = (float) Math.atan2(this.matrixGrid[1][0], this.matrixGrid[0][0]);
        float z = (float) Math.atan2(this.matrixGrid[2][1], this.matrixGrid[2][2]);

        return new Vector3f(y, x, z);
    }

    public static RotationMatrix fromEulerAngles(Vector3f anglesRadian){
        float w = anglesRadian.x(); // w yaw x y x
        float v = anglesRadian.y(); // v pitch y x y
        float u = anglesRadian.z(); // u roll z z z

        float su = Mth.sin(u);
        float cu = Mth.cos(u);
        float sv = Mth.sin(v);
        float cv = Mth.cos(v);
        float sw = Mth.sin(w);
        float cw = Mth.cos(w);

        float[][] grid = new float[][]{
                {
                    cv * cw,
                    (su * sv * cw) - (cu * sw),
                    (su * sw) + (cu * sv * cw)
                },
                {
                    cv * sw,
                    (cu * cw) + (su * sv * sw),
                    (cu * sv * sw) - (su * cw)
                },
                {
                    -sv,
                    su * cv,
                    cu * cv
                }
        };
        return new RotationMatrix(grid);
    }

    public static RotationMatrix getInverse(RotationMatrix rotationMatrix){
        float[][] originalGrid = rotationMatrix.matrixGrid;
        float[][] newGrid = blankGrid();

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                newGrid[i][j] = originalGrid[j][i];
            }
        }
        return new RotationMatrix(newGrid);
    }

    public void mult(RotationMatrix secondMatrix){
        float[][] firstGrid = this.matrixGrid;
        float[][] secondGrid = secondMatrix.matrixGrid;
        float[][] finalGrid = blankGrid();

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                finalGrid[i][j] = 0;
                for(int k = 0; k < 3; k++){
                    finalGrid[i][j] += firstGrid[i][k] * secondGrid[k][j];
                }
            }
        }
        this.matrixGrid = finalGrid;
    }

    public static float[][] blankGrid(){
        return new float[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
    }

    private static float c(float x){
        return Mth.cos(x);
    }

    private static float s(float x){
        return Mth.sin(x);
    }
}
