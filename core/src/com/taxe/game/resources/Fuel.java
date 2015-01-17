package com.taxe.game.resources;

/**
 * Represents fuel resource.
 * <p>
 * Having a train requires a number of fuel points. For example, if BasicTrain needs 4 fuel points, having two
 * BasicTrains will take 8 fuel points. If building a train would exceed player's fuel cap, or if due to having fuel cap
 * reduced, player has more fuel points than fuel cap allows, a crippling punishment should be applied (e.g. all trains
 * become very slow).
 */
public class Fuel {

    private int fuelCap;
    private int usedFuel;

    /**
     * Constructs instance of Fuel with specified fuelCap and usedFuel.
     *
     * @param fuelCap  fuel cap, must be >= 0.
     * @param usedFuel used fuel, must be >= 0.
     */
    public Fuel(int fuelCap, int usedFuel) {
        this.fuelCap = fuelCap;
        this.usedFuel = usedFuel;
        validateFuel();
    }

    /**
     * Returns fuel cap.
     *
     * @return fuel cap.
     */
    public int getFuelCap() {
        return fuelCap;
    }

    /**
     * Returns the amount of currently used fuel.
     *
     * @return amount of used fuel.
     */
    public int getUsedFuel() {
        return usedFuel;
    }

    /**
     * Changes fuel cap by a set amount. If result is negative, it is clipped to 0.
     *
     * @param delta by how much fuel cap is changed.
     */
    public void changeFuelCapBy(int delta) {
        fuelCap = Integer.max(0, fuelCap + delta);
        validateFuel();
    }

    /**
     * Changes currently used fuel. If result is negative, it is clipped to 0.
     *
     * @param delta by how much currently used fuel is changed.
     */
    public void changeUsedFuelBy(int delta) {
        usedFuel = Integer.max(0, usedFuel + delta);
        validateFuel();
    }

    /**
     * Returns true if fuel cap is exceeded.
     *
     * @return true if {@link #getUsedFuel()} > {@link #getFuelCap()}.
     */
    public boolean fuelCapExceeded() {
        return usedFuel > fuelCap;
    }

    /**
     * Checks if fuelCap and usedFuel satisfy constraints.
     *
     * @throws RuntimeException if fuelCap < 0 or usedFuel < 0.
     */
    private void validateFuel() throws RuntimeException {
        if (fuelCap < 0) throw new RuntimeException("fuelCap < 0");
        if (usedFuel < 0) throw new RuntimeException("usedFuel < 0");
    }

}
