package ro.academyplus.avaj.simulator;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private List<Flyable> aircraftList = new ArrayList<>();

    public void register(Flyable p_flyable) {
        if (!aircraftList.contains(p_flyable))
            aircraftList.add(p_flyable);
        return;
    }

    public void unregister(Flyable p_flyable) {
        aircraftList.remove(p_flyable);
        return;
    }

    protected void conditionChanged() {
        List<Flyable> copy = new ArrayList<>(aircraftList);

        for (Flyable flyable : copy)
            flyable.updateConditions();
        return;
    }
}
