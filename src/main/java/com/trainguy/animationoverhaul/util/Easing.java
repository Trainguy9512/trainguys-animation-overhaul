package com.trainguy.animationoverhaul.util;

import net.minecraft.util.Mth;

public abstract class Easing {

    public abstract float ease(float time);

    public static class Linear extends Easing {

        @Override
        public float ease(float time) {
            return time;
        }
    }

    public static class CubicBezier extends Easing {

        float cx;
        float bx;
        float ax;

        float cy;
        float by;
        float ay;

        float startGradient;
        float endGradient;

        public CubicBezier(float p1x, float p1y, float p2x, float p2y) {
            cx = 3f * p1x;
            bx = 3f * (p2x - p1x) - cx;
            ax = 1f - cx - bx;

            cy = 3f * p1y;
            by = 3f * (p2y - p1y) - cy;
            ay = 1f - cy - by;

            if (p1x > 0)
                startGradient = p1y / p1x;
            else if (p1y == 0 && p2x > 0)
                startGradient = p2y / p2x;
            else
                startGradient = 0;

            if (p2x < 1)
                endGradient = (p2y - 1) / (p2x - 1);
            else if (p2x == 1 && p1x < 1)
                endGradient = (p1y - 1) / (p1x - 1);
            else
                endGradient = 0;
        }

        float sampleCurveX(float t) {
            return ((ax * t + bx) * t + cx) * t;
        }

        float sampleCurveY(float t) {
            return ((ay * t + by) * t + cy) * t;
        }

        float sampleCurveDerivativeX(float t) {
            return (3f * ax * t + 2f * bx) * t + cx;
        }

        // Given an x value, find a parametric value it came from.
        float solveCurveX(float x, float epsilon) {

            float t0;
            float t1;
            float t2;
            float x2;
            float d2;
            int i;

            // First try a few iterations of Newton's method -- normally very fast.
            for (t2 = x, i = 0; i < 8; i++) {
                x2 = sampleCurveX(t2) - x;
                if (Mth.abs(x2) < epsilon)
                    return t2;
                d2 = sampleCurveDerivativeX(t2);
                if (Mth.abs(d2) < 1e-6)
                    break;
                t2 = t2 - x2 / d2;
            }

            // Fall back to the bisection method for reliability.
            t0 = 0f;
            t1 = 1f;
            t2 = x;

            while (t0 < t1) {
                x2 = sampleCurveX(t2);
                if (Mth.abs(x2 - x) < epsilon)
                    return t2;
                if (x > x2)
                    t0 = t2;
                else
                    t1 = t2;
                t2 = (t1 - t0) * .5f + t0;
            }

            // Failure.
            return t2;
        }

        // Evaluates y at the given x. The epsilon parameter provides a hint as to the required
        // accuracy and is not guaranteed.
        float solve(float x, float epsilon) {
            if (x < 0f)
                return 0f + startGradient * x;
            if (x > 1f)
                return 1f + endGradient * (x - 1f);
            return sampleCurveY(solveCurveX(x, epsilon));
        }

        @Override
        public float ease(float time) {
            return solve(time, 0.01f);
        }
    }

}
