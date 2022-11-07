package goldDigger.models.discoverer;

import goldDigger.models.museum.Museum;

public class Anthropologist extends BaseDiscoverer {

    private static final double ENERGY = 40;

    public Anthropologist(String name) {
        super(name, ENERGY);
    }
}
