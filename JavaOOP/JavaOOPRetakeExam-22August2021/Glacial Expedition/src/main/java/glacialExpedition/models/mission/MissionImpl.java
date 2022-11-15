package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MissionImpl implements Mission {

    @Override
    public void explore(State state, Collection<Explorer> explorers) {

        List<Explorer> explorerOnMission = explorers.stream().filter(e -> e.getEnergy() > 0).collect(Collectors.toList());

        for (Explorer explorer : explorerOnMission) {

            while (explorer.canSearch()) {

                String exhibit = state.getExhibits().stream().findFirst().orElse(null);

                if (exhibit != null) {

                    explorer.search();
                    explorer.getSuitcase().getExhibits().add(exhibit);

                    state.getExhibits().remove(exhibit);

                } else {
                    return;
                }
            }
        }
    }
}
