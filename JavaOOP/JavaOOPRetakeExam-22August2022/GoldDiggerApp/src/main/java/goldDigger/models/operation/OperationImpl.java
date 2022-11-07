package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.Collection;
import java.util.Collections;

public class OperationImpl implements Operation {

    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {

        Collection<String> exhibits = spot.getExhibits();

        discoverers.stream().allMatch(Discoverer::canDig);

        for (Discoverer discoverer : discoverers) {

            for (String exhibit: exhibits) {

                if (discoverer.canDig()) {

                    discoverer.dig();
                    discoverer.getMuseum().getExhibits().add(exhibit);
                }
            }           
        }
    }
}

