package fr.delmath.avaj.simulator.aircraft;

import fr.delmath.avaj.simulator.Coordinates;
import fr.delmath.avaj.simulator.Simulator;

public class Helicopter extends Aircraft {

    public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        switch (weatherTower.getWeather(this.coordinates)) {
            case "SUN":
                coordinates.updateCoordinates(10, 0, 2);
                Simulator.writeLog(this + ": This is hot.");
                break;
            case "RAIN":
                coordinates.updateCoordinates(5, 0, 0);
                Simulator.writeLog(this + ": Rain is hitting the blades hard.");
                break;
            case "FOG":
                coordinates.updateCoordinates(1, 0, 0);
                Simulator.writeLog(this + ": Navigating carefully through the fog.");
                break;
            case "SNOW":
                coordinates.updateCoordinates(0, 0, -12);
                Simulator.writeLog(this + ": My rotor is going to freeze!");
                break;
        }
        checkLanding();
    }
}
