package com.trainguy9512.locomotion.animation.driver;

import com.trainguy9512.locomotion.util.Interpolator;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.function.Supplier;

/**
 * Driver that acts as a variable that can be updated each tick and then is interpolated.
 * @param <D>
 */
public final class VariableDriver<D> implements Driver<D>{

    private final Supplier<D> defaultValue;
    private final Interpolator<D> interpolator;

    private D valueCurrent;
    private D valuePrevious;


    private VariableDriver(Supplier<D> defaultValue, Interpolator<D> interpolator){
        this.defaultValue = defaultValue;
        this.interpolator = interpolator;

        this.valueCurrent = defaultValue.get();
        this.valuePrevious = defaultValue.get();
    }

    @Override
    public void tick() {

    }

    @Override
    public D getValueInterpolated(float partialTicks){
        boolean valueHasNotChanged = this.valueCurrent.equals(this.valuePrevious);
        boolean gettingValueFromCurrentTick = partialTicks == 1;
        boolean gettingValueFromPreviousTick = partialTicks == 0;

        if(valueHasNotChanged || gettingValueFromCurrentTick){
            return this.valueCurrent;
        }
        if(gettingValueFromPreviousTick){
            return this.valuePrevious;
        }
        return interpolator.interpolate(this.valuePrevious, this.valueCurrent, partialTicks);
    }

    public D getValuePrevious(){
        return this.valuePrevious;
    }

    public D getValueCurrent(){
        return this.valueCurrent;
    }

    /**
     * Sets the value for the current tick.
     * If the new value is null, the default value is used instead.
     * @param newValue      Value to load for the current tick.
     * @implNote            Ensure that any mutable values inputted here are copies of themselves!
     */
    public void setValue(D newValue){
        this.valueCurrent = newValue != null ? newValue : this.defaultValue.get();
    }

    /**
     * Pushes the current value to the previous tick's value.
     */
    public void prepareForNextTick(){
        this.valuePrevious = this.valueCurrent;
    }

    /**
     * Loads the driver with the driver's default value.
     */
    public void resetValue(){
        this.setValue(this.defaultValue.get());
    }

    /**
     * Creates a driver of the given data type that can be interpolated between ticks.
     * @param defaultValue      Default value set from the start and set upon resetting the driver.
     * @param interpolator      Interpolation function for the data type
     */
    public static <D> VariableDriver<D> ofInterpolatable(Supplier<D> defaultValue, Interpolator<D> interpolator){
        return new VariableDriver<>(defaultValue, interpolator);
    }

    /**
     * Creates a driver of the given data type that will pass the latest non-interpolated tick value when accessed.
     * @param defaultValue      Default value set from the start and set upon resetting the driver.
     */
    public static <D> VariableDriver<D> ofConstant(Supplier<D> defaultValue){
        return VariableDriver.ofInterpolatable(defaultValue, Interpolator.constant());
    }

    /**
     * Creates a boolean driver that will pass the latest non-interpolated tick value when accessed.
     * @param defaultValue      Default value set from the start and set upon resetting the driver.
     */
    public static VariableDriver<Boolean> ofBoolean(Supplier<Boolean> defaultValue){
        return VariableDriver.ofInterpolatable(defaultValue, Interpolator.BOOLEAN_BLEND);
    }

    /**
     * Creates a float driver that will be linearly interpolated between ticks.
     * @param defaultValue      Default value set from the start and set upon resetting the driver.
     */
    public static VariableDriver<Float> ofFloat(Supplier<Float> defaultValue){
        return VariableDriver.ofInterpolatable(defaultValue, Interpolator.FLOAT);
    }

    /**
     * Creates a vector driver that will be linearly interpolated between ticks.
     * @param defaultValue      Default value set from the start and set upon resetting the driver.
     */
    public static VariableDriver<Vector3f> ofVector(Supplier<Vector3f> defaultValue){
        return VariableDriver.ofInterpolatable(defaultValue, Interpolator.VECTOR);
    }
}