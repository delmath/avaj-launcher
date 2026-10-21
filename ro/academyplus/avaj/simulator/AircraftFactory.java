package ro.academyplus.avaj.simulator;

public abstract class AircraftFactory {

    public static Flyable newAircraft(String p_type, String p_name, int p_longitude, int p_latitude, int p_height) {
        Coordinates coords = new Coordinates(p_longitude, p_latitude, p_height);
        long id = Aircraft.nextId();

        switch (p_type.toLowerCase()) {
            case "balloon":
                return new Balloon(id, p_name, coords);
            case "jetplane":
                return new JetPlane(id, p_name, coords);
            case "helicopter":
                return new Helicopter(id, p_name, coords);
            default:
                return null;
        }
    }
}
