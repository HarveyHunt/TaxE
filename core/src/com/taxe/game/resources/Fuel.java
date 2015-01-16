package com.taxe.game.resources;

/**
 * Represents players fuel resource.
 * Having a train requires a number of fuel points. If the fuel cap is exceeded, a crippling punishment is applied.
 * Fuel cap and used fuel must always be non-negative.
 */
public class Fuel {

    private int fuelCap;
    private int usedFuel;

    /**
     * Constructs instance of Fuel with specified fuelCap and usedFuel.
     * @param fuelCap fuel cap.
     * @param usedFuel used fuel.
     */
    public Fuel(int fuelCap, int usedFuel) {
        this.fuelCap = fuelCap;
        this.usedFuel = usedFuel;
        validateFuel();
    }

    /**
     * Returns fuel cap.
     * @return fuel cap.
     */
    public int getFuelCap() {
        return fuelCap;
    }

    /**
     * Returns the amount of currently used fuel.
     * @return amount of used fuel.
     */
    public int getUsedFuel() {
        return usedFuel;
    }

    /**
     * Changes fuel cap by a set amount.
     * @param delta by how much fuel cap is changed.
     */
    public void changeFuelCapBy(int delta) {
        fuelCap = Integer.max(0, fuelCap + delta);
        validateFuel();
    }

    /**
     * Changes currently used fuel
     * @param delta by how much currently used fuel is changed
     */
    public void changeUsedFuelBy(int delta) {
        usedFuel = Integer.max(0, delta);
        validateFuel();
    }

    /**
     * Returns true if fuel cap is exceeded
     * @return true if {@link #getUsedFuel()} > {@link #getFuelCap()}
     */
    public boolean fuelCapExceeded() {
        return usedFuel > fuelCap;
    }

    /**
     * Checks if fuelCap and usedFuel satisfy constraints
     * @throws AssertionError if fuelCap < 0 or usedFuel < 0
     */
    private void validateFuel() throws AssertionError {
        assert fuelCap >= 0;
        assert usedFuel >= 0;
    }

}
