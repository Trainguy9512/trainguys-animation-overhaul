package com.trainguy9512.animationoverhaul.animation.data.driver;

import com.trainguy9512.animationoverhaul.util.Interpolator;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.function.Supplier;

/**
 * Value
 * @param <D>
 */
public final class Driver<D>{

    private D valueCurrent;
    private D valuePrevious;
    private final Supplier<D> defaultValue;

    @Nullable
    private final Interpolator<D> interpolator;

    private Driver(Supplier<D> defaultValue, @Nullable Interpolator<D> interpolator){
        this.defaultValue = defaultValue;
        this.interpolator = interpolator;

        this.valueCurrent = defaultValue.get();
        this.valuePrevious = defaultValue.get();
    }

    /**
     * Creates a driver of the given data type that can be interpolated between ticks.
     * @param defaultValue      Default value set from the start and set upon resetting the driver.
     * @param interpolator      Interpolation function for the data type
     */
    public static <D> Driver<D> ofInterpolatable(Supplier<D> defaultValue, @Nullable Interpolator<D> interpolator){
        return new Driver<>(defaultValue, interpolator);
    }

    /**
     * Creates a driver of the given data type that will pass the latest non-interpolated tick value when accessed.
     * @param defaultValue      Default value set from the start and set upon resetting the driver.
     */
    public static <D> Driver<D> ofConstant(Supplier<D> defaultValue){
        return Driver.ofInterpolatable(defaultValue, null);
    }

    /**
     * Returns the interpolated value between the previous tick and the current tick.
     * @param partialTicks      Percentage of a tick since the previous tick.
     * @return                  Interpolated value.
     */
    public D getValueInterpolated(float partialTicks){
        if(this.interpolator == null || this.valueCurrent.equals(this.valuePrevious) || partialTicks == 1){
            return this.valueCurrent;
        }
        if(partialTicks == 0){
            return this.valuePrevious;
        }
        return interpolator.interpolate(this.valuePrevious, this.valueCurrent, partialTicks);
    }

    /**
     * Returns the value of the previous tick.
     */
    public D getValuePrevious(){
        return this.valuePrevious;
    }

    /**
     * Returns the value of the current tick.
     */
    public D getValueCurrent(){
        return this.valueCurrent;
    }

    /**
     * Loads a new value into the current tick.
     * If the new value is null, the default value is used instead.
     * @param newValue      Value to load for the current tick.
     * @implNote            Ensure that any mutable values inputted here are copies of themselves!
     */
    public void loadValue(D newValue){
        this.valueCurrent = newValue != null ? newValue : this.defaultValue.get();
    }

    /**
     * Pushes the current value to the previous tick's value and replaces it with a new value.
     * If the new value is null, the default value is used instead.
     * @param newValue      Value to load for the current tick
     */
    public void loadValueAndPush(D newValue){
        this.pushValueToPrevious();
        this.loadValue(newValue);
    }

    /**
     * Pushes the current value to the previous tick's value.
     */
    public void pushValueToPrevious(){
        this.valuePrevious = this.valueCurrent;
    }

    /**
     * Loads the driver with the driver's default value.
     */
    public void resetValue(){
        this.loadValue(this.defaultValue.get());
    }

    /**
     * Creates a boolean driver that will pass the latest non-interpolated tick value when accessed.
     * @param defaultValue      Default value set from the start and set upon resetting the driver.
     */
    public static Driver<Boolean> booleanDriver(Supplier<Boolean> defaultValue){
        return Driver.ofConstant(defaultValue);
    }

    /**
     * Creates a float driver that will be linearly interpolated between ticks.
     * @param defaultValue      Default value set from the start and set upon resetting the driver.
     */
    public static Driver<Float> floatDriver(Supplier<Float> defaultValue){
        return Driver.ofInterpolatable(defaultValue, Interpolator.FLOAT);
    }

    /**
     * Creates a vector driver that will be linearly interpolated between ticks.
     * @param defaultValue      Default value set from the start and set upon resetting the driver.
     */
    public static Driver<Vector3f> vectorDriver(Supplier<Vector3f> defaultValue){
        return Driver.ofInterpolatable(defaultValue, Interpolator.VECTOR);
    }
}