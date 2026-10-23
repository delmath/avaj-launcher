package fr.delmath.avaj.simulator.utils;

import fr.delmath.avaj.simulator.Simulator;
import fr.delmath.avaj.simulator.WeatherTower;

public abstract class Flyable {
    protected WeatherTower weatherTower;

    public abstract void updateConditions();

    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        this.weatherTower.register(this);
        Simulator.writeLog("Tower says: " + this + " registered to weather tower.");
    }
}
