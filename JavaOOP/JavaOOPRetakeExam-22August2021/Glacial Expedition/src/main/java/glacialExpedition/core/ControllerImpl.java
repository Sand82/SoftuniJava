package glacialExpedition.core;

import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    ExplorerRepository explorerRepository = new ExplorerRepository();
    StateRepository stateRepository = new StateRepository();

    private static HashSet<String> exploredState = new HashSet<>();

    @Override
    public String addExplorer(String type, String explorerName) {

        if (!type.equals("AnimalExplorer") && !type.equals("GlacierExplorer") && !type.equals("NaturalExplorer")) {

            throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);
        }

        Explorer explorer = createExplorer(type, explorerName);

        explorerRepository.add(explorer);

        return String.format(EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {

        StateImpl state = new StateImpl(stateName);

        List<String> exhibitsArr = getExhibits(exhibits);

        state.setExhibits(exhibitsArr);
        stateRepository.add(state);

        return String.format(STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {

        Explorer explorer = explorerRepository.byName(explorerName);

        if (explorer == null) {

            throw new IllegalArgumentException(String.format(EXPLORER_DOES_NOT_EXIST, explorerName));
        }

        explorerRepository.remove(explorer);

        return String.format(EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {

        List<Explorer> explorers = explorerRepository.getCollection()
                .stream().filter(e -> e.getEnergy() > 50)
                .collect(Collectors.toList());

        int totalExplorersCount = explorers.size();

        if (explorers.size() == 0) {

            throw new IllegalArgumentException(STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        State state = stateRepository.byName(stateName);

        Mission mission = new MissionImpl();
        mission.explore(state, explorers);

        exploredState.add(state.getName());

        explorers.stream().filter(e -> e.getEnergy() == 0).forEach(e -> this.retireExplorer(e.getName()));

        int retiredExplorer = totalExplorersCount - explorers.size();

        return String.format(STATE_EXPLORER, stateName, retiredExplorer);
    }

    @Override
    public String finalResult() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format(FINAL_STATE_EXPLORED, exploredState.size()));
        sb.append(System.lineSeparator());
        sb.append(FINAL_EXPLORER_INFO);
        sb.append(System.lineSeparator());

        for (Explorer explorer : explorerRepository.getCollection()) {

            String explorerExhibits = explorer.getSuitcase().getExhibits().size() != 0 ?
                    explorer.getSuitcase().getExhibits().stream().collect(Collectors.joining(", ")) :
                    "None";

            sb.append(String.format(FINAL_EXPLORER_NAME, explorer.getName()));
            sb.append(System.lineSeparator());
            sb.append(String.format(FINAL_EXPLORER_ENERGY, explorer.getEnergy()));
            sb.append(System.lineSeparator());
            sb.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, explorerExhibits));
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    private static List<String> getExhibits(String[] exhibits) {

        List<String> exhibitsArr = new ArrayList<>();

        for (String exhibit : exhibits) {
            exhibitsArr.add(exhibit);
        }
        return exhibitsArr;
    }

    private Explorer createExplorer(String type, String explorerName) {

        Explorer explorer = null;

        if (type.equals("AnimalExplorer")) {

            explorer = new AnimalExplorer(explorerName);
        }else if(type.equals("GlacierExplorer")) {

            explorer = new GlacierExplorer(explorerName);
        }else if(type.equals("NaturalExplorer")) {

            explorer = new NaturalExplorer(explorerName);
        }

        return explorer;
    }
}
