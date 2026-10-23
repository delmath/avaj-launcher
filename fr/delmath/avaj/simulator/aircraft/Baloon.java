package fr.delmath.avaj.simulator.aircraft;

import fr.delmath.avaj.simulator.Coordinates;
import fr.delmath.avaj.simulator.Simulator;

public class Baloon extends Aircraft {

    public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        switch (weatherTower.getWeather(this.coordinates)) {
            case "SUN":
                coordinates.updateCoordinates(2, 0, 4);
                Simulator.writeLog(this + ": Let's enjoy the good weather and take some pics.");
                break;
            case "RAIN":
                coordinates.updateCoordinates(0, 0, -5);
                Simulator.writeLog(this + ": Damn you rain! You messed up my baloon.");
                break;
            case "FOG":
                coordinates.updateCoordinates(0, 0, -3);
                Simulator.writeLog(this + ": Fog is thick, can't see a thing.");
                break;
            case "SNOW":
                coordinates.updateCoordinates(0, 0, -15);
                Simulator.writeLog(this + ": It's snowing. We're gonna crash.");
                break;
        }
        checkLanding();
    }
}
