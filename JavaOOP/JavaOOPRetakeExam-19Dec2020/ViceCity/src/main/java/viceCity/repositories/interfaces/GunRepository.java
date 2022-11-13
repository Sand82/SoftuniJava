package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class GunRepository implements Repository<Gun> {

    private Map<String, Gun> models = new LinkedHashMap<>();

    @Override
    public Collection<Gun> getModels() {

        return this.models.values();
    }

    @Override
    public void add(Gun model) {

        models.put( model.getName(),model);
    }

    @Override
    public boolean remove(Gun model) {

        return models.remove(model.getName(), model);
    }

    @Override
    public Gun find(String name) {

        return models.get(name);
    }
}
