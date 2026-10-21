package ro.academyplus.avaj.simulator;

public class Helicopter extends Aircraft {
    private WeatherTower weatherTower;

    Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        switch (weatherTower.getWeather(this.coordinates)) {
            case "SUN":
                coordinates.updateCoordinates(10, 0, 2);
                Simulator.writeLog("Helicopter#" + name + "(" + id + "): This is hot.");
                break;
            case "RAIN":
                coordinates.updateCoordinates(5, 0, 0);
                Simulator.writeLog("Helicopter#" + name + "(" + id + "): Rain is hitting the blades hard.");
                break;
            case "FOG":
                coordinates.updateCoordinates(1, 0, 0);
                Simulator.writeLog("Helicopter#" + name + "(" + id + "): Navigating carefully through the fog.");
                break;
            case "SNOW":
                coordinates.updateCoordinates(0, 0, -12);
                Simulator.writeLog("Helicopter#" + name + "(" + id + "): My rotor is going to freeze!");
                break;
        }

        if (coordinates.getHeight() == 0) {
            Simulator.writeLog("Helicopter#" + name + "(" + id + ") landing.");
            weatherTower.unregister(this);
            Simulator.writeLog("Tower says: Helicopter#" + name + "(" + id + ") unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        this.weatherTower.register(this);
        Simulator.writeLog("Tower says: Helicopter#" + name + "(" + id + ") registered to weather tower.");
    }
}
