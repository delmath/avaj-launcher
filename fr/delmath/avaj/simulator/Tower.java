package fr.delmath.avaj.simulator;

import java.util.ArrayList;
import java.util.List;

import fr.delmath.avaj.simulator.utils.Flyable;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable p_flyable) {
        if (!observers.contains(p_flyable))
            observers.add(p_flyable);
        return;
    }

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
        return;
    }

    protected void conditionChanged() {
        List<Flyable> copy = new ArrayList<>(observers);

        for (Flyable flyable : copy)
            flyable.updateConditions();
        return;
    }
}
