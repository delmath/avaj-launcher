package ro.academyplus.avaj.simulator;

public class JetPlane extends Aircraft {
    private WeatherTower weatherTower;

    JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        switch (weatherTower.getWeather(this.coordinates)) {
            case "SUN":
                coordinates.updateCoordinates(0, 10, 2);
                Simulator.writeLog("JetPlane#" + name + "(" + id + "): It's sunny, time to break the sound barrier!");
                break;
            case "RAIN":
                coordinates.updateCoordinates(0, 5, 0);
                Simulator.writeLog("JetPlane#" + name + "(" + id + "): It's raining. Better watch out for lightings.");
                break;
            case "FOG":
                coordinates.updateCoordinates(0, 1, 0);
                Simulator.writeLog("JetPlane#" + name + "(" + id + "): Flying blind in the fog.");
                break;
            case "SNOW":
                coordinates.updateCoordinates(0, 0, -7);
                Simulator.writeLog("JetPlane#" + name + "(" + id + "): OMG! Winter is coming!");
                break;
        }

        if (coordinates.getHeight() == 0) {
            Simulator.writeLog("JetPlane#" + name + "(" + id + ") landing.");
            weatherTower.unregister(this);
            Simulator.writeLog("Tower says: JetPlane#" + name + "(" + id + ") unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        this.weatherTower.register(this);
        Simulator.writeLog("Tower says: JetPlane#" + name + "(" + id + ") registered to weather tower.");
    }
}
