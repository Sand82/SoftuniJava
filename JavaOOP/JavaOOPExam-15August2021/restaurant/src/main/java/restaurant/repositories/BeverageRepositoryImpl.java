package restaurant.repositories;

import restaurant.entities.drinks.BaseBeverage;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.repositories.interfaces.BeverageRepository;

import java.util.ArrayList;
import java.util.Collection;

public class BeverageRepositoryImpl implements BeverageRepository<Beverages> {

    private Collection<Beverages> entities = new ArrayList<>();

    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {

        return this.entities.stream().filter(b -> b.getName().equals(drinkName) && b.getBrand().equals(drinkBrand))
                .findFirst().orElse(null);
    }

    @Override
    public Collection<Beverages> getAllEntities() {

        return this.entities;
    }

    @Override
    public void add(Beverages entity) {

        this.entities.add(entity);
    }
}
