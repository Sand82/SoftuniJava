package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    AstronautRepository astronautRepository = new AstronautRepository();
    PlanetRepository planetRepository = new PlanetRepository();

    private HashSet<String> exploredPlanets = new HashSet<>();

    @Override
    public String addAstronaut(String type, String astronautName) {

        if (!type.equals("Biologist") && !type.equals("Geodesist") && !type.equals("Meteorologist")) {

            throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }

        Astronaut astronaut = createAstronaut(type, astronautName);

        astronautRepository.add(astronaut);

        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {

        PlanetImpl planet = new PlanetImpl(planetName);

        planet.setItems(getItems(items));

        planetRepository.add(planet);

        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {

        Astronaut astronaut = astronautRepository.findByName(astronautName);

        if (astronaut == null) {

            throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }

        astronautRepository.remove(astronaut);

        return String.format(ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {

        Collection<Astronaut> suitableAstronauts = astronautRepository
                .getModels().stream().filter(a -> a.getOxygen() > 60.0).collect(Collectors.toList());

        if (suitableAstronauts.size() == 0) {

            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

        Planet planet = planetRepository.findByName(planetName);
        MissionImpl mission = new MissionImpl();
        mission.explore(planet, suitableAstronauts);

        long deadAstronautsCount = suitableAstronauts.stream().filter(a -> a.getOxygen() == 0).count();
        exploredPlanets.add(planet.getName());

        return String.format(PLANET_EXPLORED, planetName, deadAstronautsCount);
    }

    @Override
    public String report() {

        String exploredPlanetsCount = String.valueOf(exploredPlanets.size());

        StringBuilder sb = new StringBuilder();

        sb.append(String.format(REPORT_PLANET_EXPLORED, exploredPlanetsCount));
        sb.append(System.lineSeparator());
        sb.append(REPORT_ASTRONAUT_INFO);
        sb.append(System.lineSeparator());


        for (Astronaut model : astronautRepository.getModels()) {

            String bagInformation = model.getBag().getItems().size() != 0 ?
                    String.join(", ", model.getBag().getItems()) :
                    "none";

            sb.append(String.format(REPORT_ASTRONAUT_NAME, model.getName()));
            sb.append(System.lineSeparator());
            sb.append(String.format(REPORT_ASTRONAUT_OXYGEN, model.getOxygen()));
            sb.append(System.lineSeparator());
            sb.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, bagInformation));
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    private Collection<String> getItems(String[] items) {

        List<String> itemsList = new ArrayList<>();

        for (int i = 0; i < items.length; i++) {

            itemsList.add(items[i]);
        }

        return itemsList;
    }

    private Astronaut createAstronaut(String type, String name) {

        Astronaut astronaut = null;

        if (type.equals("Biologist")) {

            astronaut = new Biologist(name);
        } else if (type.equals("Meteorologist")) {

            astronaut = new Meteorologist(name);
        } else if (type.equals("Geodesist")) {

            astronaut = new Geodesist(name);
        }

        return astronaut;
    }

}
