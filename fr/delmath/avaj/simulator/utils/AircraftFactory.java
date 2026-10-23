package fr.delmath.avaj.simulator.utils;

import fr.delmath.avaj.simulator.Coordinates;
import fr.delmath.avaj.simulator.aircraft.Aircraft;
import fr.delmath.avaj.simulator.aircraft.Baloon;
import fr.delmath.avaj.simulator.aircraft.Helicopter;
import fr.delmath.avaj.simulator.aircraft.JetPlane;

public class AircraftFactory {
    private static AircraftFactory aircraftFactory = new AircraftFactory();

    private AircraftFactory() {}

    public static AircraftFactory getFactory() {
        return (aircraftFactory);
    }

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        long id = Aircraft.nextId();

        switch (p_type.toLowerCase()) {
            case "balloon":
            case "baloon":
                return new Baloon(id, p_name, p_coordinates);
            case "jetplane":
                return new JetPlane(id, p_name, p_coordinates);
            case "helicopter":
                return new Helicopter(id, p_name, p_coordinates);
            default:
                return null;
        }
    }
}
