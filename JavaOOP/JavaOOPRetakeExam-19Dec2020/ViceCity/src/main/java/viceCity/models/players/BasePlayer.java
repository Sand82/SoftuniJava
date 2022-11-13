package viceCity.models.players;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.GunRepository;
import viceCity.repositories.interfaces.Repository;

import static viceCity.common.ExceptionMessages.*;

public abstract class BasePlayer implements Player {

    private String name;
    private int lifePoints;

    private GunRepository gunRepository = new GunRepository();

    public BasePlayer(String name, int lifePoints) {

        this.setName(name);
        this.setLifePoints(lifePoints);
    }

    public void setName(String name) {

        if (name == null || name.trim().isEmpty()) {

            throw new IllegalArgumentException(PLAYER_NULL_USERNAME);
        }
        this.name = name;
    }

    public void setLifePoints(int lifePoints) {

        if (lifePoints < 0) {

            throw new IllegalArgumentException(PLAYER_LIFE_POINTS_LESS_THAN_ZERO);

        }
        this.lifePoints = lifePoints;
    }

    @Override
    public String getName() {

        return this.name;
    }

    @Override
    public int getLifePoints() {

        return this.lifePoints;
    }

    @Override
    public boolean isAlive() {

        return lifePoints > 0;
    }

    @Override
    public Repository<Gun> getGunRepository() {

        return this.gunRepository;
    }

    @Override
    public void takeLifePoints(int points) {

        lifePoints -= points;

        if (lifePoints < 0) {

            lifePoints = 0;
        }
    }
}
