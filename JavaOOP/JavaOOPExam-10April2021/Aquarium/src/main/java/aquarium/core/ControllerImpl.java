package aquarium.core;

import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.BaseDecoration;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.Collection;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private DecorationRepository decorations = new DecorationRepository();

    private Collection<Aquarium> aquariums = new ArrayList<>();

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {

        if (!aquariumType.equals("FreshwaterAquarium") && !aquariumType.equals("SaltwaterAquarium")) {

            throw new NullPointerException(INVALID_AQUARIUM_TYPE);
        }

        Aquarium aquarium = createAquarium(aquariumType, aquariumName);
        aquariums.add(aquarium);

        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) { //Ornament" and "Plant"

        if (!type.equals("Ornament") && !type.equals("Plant")) {

            throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }

        Decoration decoration = createDecoration(type);

        decorations.add(decoration);

        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {

        Decoration decoration = decorations.findByType(decorationType);

        if (decoration == null) {

            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));
        }

        Aquarium aquarium = getAquariumByName(aquariumName);

        aquarium.addDecoration(decoration);
        decorations.remove(decoration);

        return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {

        if (!fishType.equals("FreshwaterFish") && !fishType.equals("SaltwaterFish")) {

            throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }

        Fish fish = createFish(fishType, fishName, fishSpecies, price);

        Aquarium aquarium = getAquariumByName(aquariumName);

        String aquariumsType = aquarium.getClass().getSimpleName();
        String fishesType = fish.getClass().getSimpleName();

        boolean isSaltWaterFishAquarium = aquariumsType.equals("SaltwaterAquarium") &&
                fishesType.equals("SaltwaterFish");

        boolean isFreshWaterFishAquarium = aquariumsType.equals("FreshwaterAquarium") &&
                fishesType.equals("FreshwaterFish");

        if (!isFreshWaterFishAquarium && !isSaltWaterFishAquarium) {

            return WATER_NOT_SUITABLE;
        }

        aquarium.addFish(fish);

        return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishesType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {

        Aquarium aquarium = getAquariumByName(aquariumName);
        aquarium.feed();

        return String.format(FISH_FED, aquarium.getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {

        Aquarium aquarium = getAquariumByName(aquariumName);

        double decorationsPrice = aquarium.getDecorations().stream().mapToDouble(Decoration::getPrice).sum();
        double fishesPrice = aquarium.getFish().stream().mapToDouble(Fish::getPrice).sum();

        double totalPrice = decorationsPrice + fishesPrice;

        return String.format(VALUE_AQUARIUM, aquariumName, totalPrice);
    }

    @Override
    public String report() {

        StringBuilder sb = new StringBuilder();

        for (Aquarium aquarium : aquariums) {
            sb.append(aquarium.getInfo());
        }

        return sb.toString().trim();
    }

    private Aquarium createAquarium(String aquariumType, String aquariumName) {

        Aquarium aquarium = null;

        if (aquariumType.equals("FreshwaterAquarium")) {

            aquarium = new FreshwaterAquarium(aquariumName);
        } else if (aquariumType.equals("SaltwaterAquarium")) {

            aquarium = new SaltwaterAquarium(aquariumName);
        }

        return aquarium;
    }

    private Decoration createDecoration(String type) {

        Decoration decoration = null;

        if (type.equals("Ornament")) {

            decoration = new Ornament();
        } else if (type.equals("Plant")) {

            decoration = new Plant();
        }

        return decoration;
    }

    private Fish createFish(String fishType, String fishName, String fishSpecies, double price) {

        Fish fish = null;

        if (fishType.equals("FreshwaterFish")) {

            fish = new FreshwaterFish(fishName, fishSpecies, price);
        }else if (fishType.equals("SaltwaterFish")) {

            fish = new SaltwaterFish(fishName, fishSpecies, price);
        }

        return fish;
    }

    private Aquarium getAquariumByName(String aquariumName) {
        return aquariums.stream().filter(a -> a.getName().equals(aquariumName)).findFirst().orElse(null);
    }
}
