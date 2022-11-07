package goldDigger.repositories;

import goldDigger.models.discoverer.*;
import java.util.*;

public class DiscovererRepository implements Repository<Discoverer> {

    private Collection<Discoverer> discoverers;

    public DiscovererRepository() {

        this.discoverers = new ArrayList<>();
    }

    @Override
    public Collection<Discoverer> getCollection() {

        return Collections.unmodifiableCollection(this.discoverers);
    }

    @Override
    public void add(Discoverer entity) {

        discoverers.add(entity);
    }

    @Override
    public boolean remove(Discoverer entity) {

        return discoverers.remove(entity);
    }


    @Override
    public Discoverer byName(String name) {

        return discoverers.stream()
                .filter(d -> d.getName().equals(name))
                .findFirst().orElse(null);
    }
}
