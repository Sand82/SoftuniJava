package glacialExpedition.repositories;

import glacialExpedition.models.explorers.Explorer;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExplorerRepository implements Repository<Explorer> {

    private Map<String, Explorer> explorers = new LinkedHashMap<>();

    @Override
    public Collection<Explorer> getCollection() {

        return explorers.values();
    }

    @Override
    public void add(Explorer entity) {

        explorers.put(entity.getName(), entity);
    }

    @Override
    public boolean remove(Explorer entity) {

        return explorers.remove(entity.getName(), entity);
    }

    @Override
    public Explorer byName(String name) {

        return explorers.get(name);
    }
}
