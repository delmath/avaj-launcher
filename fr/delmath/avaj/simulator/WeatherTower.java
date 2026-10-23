package fr.delmath.avaj.simulator;

import fr.delmath.avaj.simulator.utils.WeatherProvider;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates p_coordinates) {
        return (WeatherProvider.getProvider().getCurrentWeather(p_coordinates));
    }

    public void changeWeather() {
        this.conditionChanged();
        return;
    }
}
