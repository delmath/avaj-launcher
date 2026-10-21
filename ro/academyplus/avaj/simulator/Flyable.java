package ro.academyplus.avaj.simulator;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}
