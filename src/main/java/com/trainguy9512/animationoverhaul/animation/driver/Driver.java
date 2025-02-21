package com.trainguy9512.animationoverhaul.animation.driver;

import com.trainguy9512.animationoverhaul.util.Interpolator;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Vector;
import java.util.function.Supplier;

public class Driver<D>{

    private D value;
    private D valuePrevious;
    private final Supplier<D> defaultValue;

    @Nullable
    private final Interpolator<D> interpolator;

    private Driver(Supplier<D> defaultValue, @Nullable Interpolator<D> interpolator){
        this.defaultValue = defaultValue;
        this.interpolator = interpolator;

        this.value = defaultValue.get();
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

    public D getValueInterpolated(float partialTicks){
        if(this.interpolator == null){
            return this.value;
        }
        return interpolator.interpolate(this.valuePrevious, this.value, partialTicks);
    }

    public D getValuePrevious(){
        return this.valuePrevious;
    }

    public D getValueCurrent(){
        return this.value;
    }

    public void loadValue(D newValue){
        this.value = newValue;
    }

    public void set(D value){
        this.value = value != null ? value : this.defaultValue.get();
    }

    public void pushValueToPrevious(){
        this.valuePrevious = this.value;
    }

    public void resetValue(){
        this.set(this.defaultValue.get());
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