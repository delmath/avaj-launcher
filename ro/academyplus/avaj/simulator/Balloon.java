package ro.academyplus.avaj.simulator;

public class Balloon extends Aircraft {
    private WeatherTower weatherTower;

    Balloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        switch (weatherTower.getWeather(this.coordinates)) {
            case "SUN":
                coordinates.updateCoordinates(2, 0, 4);
                Simulator.writeLog("Balloon#" + name + "(" + id + "): Let's enjoy the good weather and take some pics.");
                break;
            case "RAIN":
                coordinates.updateCoordinates(0, 0, -5);
                Simulator.writeLog("Balloon#" + name + "(" + id + "): Damn you rain! You messed up my balloon.");
                break;
            case "FOG":
                coordinates.updateCoordinates(0, 0, -3);
                Simulator.writeLog("Balloon#" + name + "(" + id + "): Fog is thick, can't see a thing.");
                break;
            case "SNOW":
                coordinates.updateCoordinates(0, 0, -15);
                Simulator.writeLog("Balloon#" + name + "(" + id + "): It's snowing. We're gonna crash.");
                break;
        }

        if (coordinates.getHeight() == 0) {
            Simulator.writeLog("Balloon#" + name + "(" + id + ") landing.");
            weatherTower.unregister(this);
            Simulator.writeLog("Tower says: Balloon#" + name + "(" + id + ") unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        this.weatherTower.register(this);
        Simulator.writeLog("Tower says: Balloon#" + name + "(" + id + ") registered to weather tower.");
    }
}
