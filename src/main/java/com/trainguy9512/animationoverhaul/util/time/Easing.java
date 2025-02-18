package com.trainguy9512.animationoverhaul.util.time;

import net.minecraft.util.Mth;

/**
 * @author Marvin Schürz
 */
@FunctionalInterface
public interface Easing {

    public float ease(float time);

    Easing LINEAR = time -> time;

    // Preset cubic beziers
    // https://easings.net/

    Easing SINE_IN = Easing.easeIn(time -> (float) (1f - Math.cos((time * Math.PI) / 2f)));
    Easing SINE_OUT = Easing.easeOut(SINE_IN);
    Easing SINE_IN_OUT = Easing.easeInOut(SINE_IN);

    Easing QUAD_IN = Easing.easeIn(time -> time * time);
    Easing QUAD_OUT = Easing.easeOut(QUAD_IN);
    Easing QUAD_IN_OUT = Easing.easeInOut(QUAD_IN);

    Easing CUBIC_IN = Easing.easeIn(time -> time * time * time);
    Easing CUBIC_OUT = Easing.easeOut(CUBIC_IN);
    Easing CUBIC_IN_OUT = Easing.easeInOut(CUBIC_IN);

    Easing QUART_IN = Easing.easeIn(time -> time * time * time * time);
    Easing QUART_OUT = Easing.easeOut(QUART_IN);
    Easing QUART_IN_OUT = Easing.easeInOut(QUART_IN);

    Easing QUINT_IN = Easing.easeIn(time -> time * time * time * time * time);
    Easing QUINT_OUT = Easing.easeOut(QUINT_IN);
    Easing QUINT_IN_OUT = Easing.easeInOut(QUINT_IN);

    Easing POW_IN = Easing.easeIn(time -> time == 0 ? 0 : (float) Math.pow(2, 10 * time - 10));
    Easing POW_OUT = Easing.easeOut(POW_IN);
    Easing POW_IN_OUT = Easing.easeInOut(POW_IN);

    Easing CIRC_IN = Easing.easeIn(time -> (float) Math.sqrt(1 - Math.pow(time - 1, 2)));
    Easing CIRC_OUT = Easing.easeOut(CIRC_IN);
    Easing CIRC_IN_OUT = Easing.easeInOut(CIRC_IN);

    Easing BACK_IN = Easing.easeIn(Easing.CubicBezier.easeInOf(0.36f, 0f, 0.66f, -0.56f));
    Easing BACK_OUT = Easing.easeOut(BACK_IN);
    Easing BACK_IN_OUT = Easing.easeInOut(BACK_IN);

    Easing ELASTIC_IN = Easing.easeIn(Easing.Elastic.easeInOf(18, 3f));
    Easing ELASTIC_OUT = Easing.easeOut(ELASTIC_IN);
    Easing ELASTIC_IN_OUT = Easing.easeInOut(ELASTIC_IN);

    Easing BOUNCE_IN = Easing.easeIn(Easing.inverse(time -> {
        float n1 = 7.5625f;
        float d1 = 2.75f;

        if (time < 1f / d1) {
            return n1 * time * time;
        } else if (time < 2f / d1) {
            return n1 * (time -= 1.5f / d1) * time + 0.75f;
        } else if (time < 2.5f / d1) {
            return n1 * (time -= 2.25f / d1) * time + 0.9375f;
        } else {
            return n1 * (time -= 2.625f / d1) * time + 0.984375f;
        }
    }));
    Easing BOUNCE_OUT = Easing.easeOut(BOUNCE_IN);
    Easing BOUNCE_IN_OUT = Easing.easeInOut(BOUNCE_IN);

    public static class Elastic implements Easing {

        private final float bounceFactor;
        private final float falloffExponent;

        private Elastic(float bounceFactor, float falloffExponent){
            this.bounceFactor = bounceFactor;
            this.falloffExponent = falloffExponent;
        }

        /**
         * Returns an ease-in elastic function using the given bounce factor.
         * <p>
         * Bounce factor preview graph: <a href="https://www.desmos.com/calculator/rtycpc7igu">Desmos</a>
         * @param falloffExponent   Exponent that controls how sharp the falloff is. The higher the exponent, the faster the falloff. Default is 2.
         * @param bounceFactor      Value that controls the number of waves in the elastic shape.
         */
        public static Elastic easeInOf(float bounceFactor, float falloffExponent){
            return new Elastic(bounceFactor, falloffExponent);
        }

        @Override
        public float ease(float time) {
            return time == 0 ? 0 : time == 1 ? 1 : (float) (Math.pow(time, falloffExponent) * ((2 * Math.sin(1 - time) * this.bounceFactor) / this.bounceFactor + Math.cos((1 - time) * this.bounceFactor)));
        }
    }

    public static class CubicBezier implements Easing {

        float cx;
        float bx;
        float ax;

        float cy;
        float by;
        float ay;

        float startGradient;
        float endGradient;

        private CubicBezier(float p1x, float p1y, float p2x, float p2y) {
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

        /**
         * Creates a cubic bezier easing using two handle points.
         * Values from this website may be used as parameters here: <a href="https://cubic-bezier.com/">https://cubic-bezier.com</a>
         * @param point1X   X of point 1
         * @param point1Y   Y of point 1
         * @param point2X   X of point 2
         * @param point2Y   Y of point 2
         */
        public static CubicBezier easeInOf(float point1X, float point1Y, float point2X, float point2Y){
            return new CubicBezier(point1X, point1Y, point2X, point2Y);
        }
    }

    /**
     * Returns the inverse for the provided easing.
     */
    public static Easing inverse(Easing easing){
        return time -> 1 - easing.ease(1 - time);
    }

    /**
     * Returns the ease-in equivalent of the provided ease-in function, which by default is itself.
     * @param easeIn    Ease-in function
     * @return          Ease-in function
     */
    public static Easing easeIn(Easing easeIn){
        return easeIn;
    }

    /**
     * Returns the ease-out equivalent of the provided ease-in function.
     * @param easeIn    Ease-in function
     * @return          Ease-out function
     */
    public static Easing easeOut(Easing easeIn){
        return Easing.inverse(easeIn);
    }

    /**
     * Returns the ease-in-out equivalent of the provided ease-in function.
     * @param easeIn    Ease-in function
     * @return          Ease-in-out function
     */
    public static Easing easeInOut(Easing easeIn){
        return time -> time < 0.5f ?
                Easing.easeIn(easeIn).ease(time * 2f) / 2f :
                Easing.easeOut(easeIn).ease(time * 2f - 1f) / 2f + 0.5f;
    }

}
