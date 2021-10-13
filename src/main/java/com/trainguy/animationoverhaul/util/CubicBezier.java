package com.trainguy.animationoverhaul.util;

import com.mojang.math.Vector3f;
import net.fabricmc.loader.util.sat4j.core.Vec;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec2;

import java.awt.Point;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;

public class CubicBezier {
    private Point2D.Float point1;
    private Point2D.Float point2;
    private Point2D.Float point3;
    private Point2D.Float point4;

    public CubicBezier(float x1, float y1, float x2, float y2) {
        this.point1 = new Point2D.Float(0, 0);
        this.point2 = new Point2D.Float(x1, y1);
        this.point3 = new Point2D.Float(x2, y2);
        this.point4 = new Point2D.Float(1, 1);
    }

    public CubicBezier(Point2D.Float point1, Point2D.Float point2, Point2D.Float point3, Point2D.Float point4) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
    }

    private float getY(float t){
        return (float) ((point1.y * Math.pow(1 - t, 3)) + (3 * point2.y * Math.pow(1 - t, 2) * t) + (3 * point3.y * (1 - t) * t * t) + (point4.y * t * t * t));
    }

    private float getX(float t){
        return (float) ((point1.x * Math.pow(1 - t, 3)) + (3 * point2.x * Math.pow(1 - t, 2) * t) + (3 * point3.x * (1 - t) * t * t) + (point4.x * t * t * t));
    }

    public float getValue(float t) {
        float tolerance = 0.0001F;

        float lower = 0;
        float upper = 1;
        float center = (upper + lower) / 2;

        float x = getX(center);

        System.out.println(t + " " + x + " " + lower + " " + upper);
        while(Math.abs(t - x) > tolerance){
            if(t > x){
                lower = center;
            } else {
                upper = center;
            }
            center = (upper + lower) / 2;
            x = getX(center);
        }



        /*
        float x = ((-point1.x + 3 * point2.x - 3 * point3.x + point4.x) * t * t * t) + ((3 * point1.x - 6 * point2.x + 3 * point3.x) * t * t) + ((-3 * point1.x + 3 * point2.x) * t);
        x = (float) ((t * t * t * point1.x) + (3 * t * t * (1 - t) * point2.x) + (3 * t * Math.pow(1 - t, 2) * point3.x) + (Math.pow(1 - t, 3) * point4.x));
        System.out.println(t + " " + x);
        float y = (float) ((point1.y * Math.pow(1 - x, 3)) + (3 * point2.y * Math.pow(1 - x, 2) * x) + (3 * point3.y * (1 - x) * x * x) + (point4.y * x * x * x));

         */

        return getY(center);
    }
}