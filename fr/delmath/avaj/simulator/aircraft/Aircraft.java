package fr.delmath.avaj.simulator.aircraft;

import fr.delmath.avaj.simulator.Coordinates;
import fr.delmath.avaj.simulator.Simulator;
import fr.delmath.avaj.simulator.utils.Flyable;

public abstract class Aircraft extends Flyable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;

    private static long idCounter = 0;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinate;
    }

    public static long nextId() {
        return ++idCounter;
    }

    protected void checkLanding() {
        if (coordinates.getHeight() == 0) {
            Simulator.writeLog(this + " landing.");
            weatherTower.unregister(this);
            Simulator.writeLog("Tower says: " + this + " unregistered from weather tower.");
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "#" + name + "(" + id + ")";
    }
}
