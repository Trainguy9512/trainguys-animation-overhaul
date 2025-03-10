package com.trainguy9512.locomotion.animation.data.driver;

/**
 * Function for calculating and storing values derived from a data reference and referenced in pose functions.
 * @param <D>       Data Type
 * @author          James Pelter
 */
public interface Driver<D> {

    /**
     * Updates the driver, called once per tick after data extraction and prior to pose function tick.
     */
    void tick();

    /**
     * Returns the interpolated value between the previous tick and the current tick.
     * @param partialTicks      Percentage of a tick since the previous tick.
     * @return                  Interpolated value.
     */
    D getValueInterpolated(float partialTicks);

    /**
     * Returns the value of the previous tick.
     */
    D getValuePrevious();

    /**
     * Returns the value of the current tick.
     */
    D getValueCurrent();

    /**
     * Sets the current value to the previous value
     */
    void pushToPrevious();
}
