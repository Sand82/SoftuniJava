package goldDigger.core;

import goldDigger.common.ConstantMessages;
import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.Repository;
import goldDigger.repositories.SpotRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static goldDigger.common.ConstantMessages.*;
import static goldDigger.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private Repository<Discoverer> discovererRepository;
    private Repository<Spot> spotRepository;

    private Operation operation ;

    public ControllerImpl() {

        discovererRepository = new DiscovererRepository();
        spotRepository = new SpotRepository();

        operation = new OperationImpl();
    }

    private static final String [] discovererKings = {"Anthropologist", "Archaeologist", "Geologist"};
    @Override
    public String addDiscoverer(String kind, String discovererName) {

        if (!Arrays.stream(discovererKings).anyMatch(d -> d.equals(kind))) {

            throw new IllegalArgumentException(DISCOVERER_INVALID_KIND);
        }

        Discoverer discoverer = createDiscoverer(kind, discovererName);

        discovererRepository.add(discoverer);


        return String.format(DISCOVERER_ADDED, kind, discovererName);
    }


    @Override
    public String addSpot(String spotName, String... exhibits) {

        Spot spot = new SpotImpl(spotName);
        spot.getExhibits().addAll(Arrays.stream(exhibits).collect(Collectors.toList()));

        spotRepository.add(spot);

        return String.format(SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {

        Discoverer discoverer = discovererRepository
                .getCollection().stream()
                .filter(d -> d.getName().equals(discovererName))
                .findFirst().orElse(null);

        if (discoverer == null) {

            throw new IllegalArgumentException(String.format(DISCOVERER_DOES_NOT_EXIST, discovererName));
        }

        discovererRepository.remove(discoverer);

        return String.format(DISCOVERER_EXCLUDE, discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {

        List<Discoverer> discoverers = this.discovererRepository.getCollection().stream()
                .filter(d -> d.getEnergy() > 45)
                .collect(Collectors.toList());

        if (discoverers.size() == 0) {
            throw new IllegalArgumentException(SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }

        Spot spot = this.spotRepository.getCollection().stream().filter(s -> s.getName().equals(spotName)).findFirst().get();

        operation.startOperation(spot, discoverers);

        long excluded = discoverers.stream().filter(d -> d.getEnergy() == 0).count();

        return String.format(ConstantMessages.INSPECT_SPOT, spotName, excluded);
    }

    @Override
    public String getStatistics() {

        StringBuilder sb = new StringBuilder();

        int inspectedSpots = spotRepository.getCollection().size();

        sb.append(String.format("%d spots were inspected.", inspectedSpots));
        sb.append(System.lineSeparator());
        sb.append("Information for the discoverers:");
        sb.append(System.lineSeparator());

        for (Discoverer discoverer : discovererRepository.getCollection()) {
            String discoveredItems = discoverer.getMuseum().getExhibits().size() == 0
                    ? "None"
                    : discoverer.getMuseum().getExhibits().stream().collect(Collectors.joining(", "));

            sb.append(String.format("Name: %s", discoverer.getName()));
            sb.append(System.lineSeparator());
            sb.append(String.format("Energy: %.0f", discoverer.getEnergy()));
            sb.append(System.lineSeparator());
            sb.append(String.format("Museum exhibits: %s", discoveredItems));
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    private Discoverer createDiscoverer(String kind, String name) {

        Discoverer discoverer = null;

        if (kind.equals("Anthropologist")) {

            discoverer = new Anthropologist(name);
        } else if (kind.equals("Archaeologist")) {

            discoverer = new Archaeologist(name);
        } else if (kind.equals("Geologist")) {

            discoverer = new Geologist(name);
        }

        return discoverer;
    }

}
