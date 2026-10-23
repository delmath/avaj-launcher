package fr.delmath.avaj.simulator.aircraft;

import fr.delmath.avaj.simulator.Coordinates;
import fr.delmath.avaj.simulator.Simulator;

public class JetPlane extends Aircraft {

    public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        switch (weatherTower.getWeather(this.coordinates)) {
            case "SUN":
                coordinates.updateCoordinates(0, 10, 2);
                Simulator.writeLog(this + ": It's sunny, time to break the sound barrier!");
                break;
            case "RAIN":
                coordinates.updateCoordinates(0, 5, 0);
                Simulator.writeLog(this + ": It's raining. Better watch out for lightings.");
                break;
            case "FOG":
                coordinates.updateCoordinates(0, 1, 0);
                Simulator.writeLog(this + ": Flying blind in the fog.");
                break;
            case "SNOW":
                coordinates.updateCoordinates(0, 0, -7);
                Simulator.writeLog(this + ": OMG! Winter is coming!");
                break;
        }
        checkLanding();
    }
}
