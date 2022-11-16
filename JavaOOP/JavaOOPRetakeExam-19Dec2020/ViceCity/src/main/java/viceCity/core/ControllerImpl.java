package viceCity.core;

import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {

    private List<Gun> guns = new ArrayList<>();
    private Map<String, Player> players = new LinkedHashMap<>();
    private MainPlayer mainPlayer = new MainPlayer();

    @Override
    public String addPlayer(String name) {

        players.put(name, new CivilPlayer(name));

        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {

        if (!type.equals("Pistol") && !type.equals("Rifle")) {

            return GUN_TYPE_INVALID;
        }

        Gun gun = createGun(type, name);

        guns.add(gun);

        return String.format(GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {

        Gun gun = guns.stream().findFirst().orElse(null);

        if (gun == null) {

            return GUN_QUEUE_IS_EMPTY;
        }

        if (name.equals("Vercetti")) {

            mainPlayer.getGunRepository().add(gun);
        }else {

            Player player = players.get(name);

            if (player == null) {

                return CIVIL_PLAYER_DOES_NOT_EXIST;
            }
            player.getGunRepository().add(gun);
        }

        guns.remove(gun);

        String playerType = name.equals("Vercetti") ? "Main Player: Tommy Vercetti" : "Civil Player: " + name;

        return String.format("Successfully added %s to the %s", gun.getName(), playerType);
    }

    @Override
    public String fight() {

        List<Player> civilPlayers = players.values().stream().collect(Collectors.toList());

        if (mainPlayer.isAlive() && mainPlayer.getGunRepository().getModels().size() == 0) {

            return FIGHT_HOT_HAPPENED;
        }

        GangNeighbourhood gangNeighbourhood = new GangNeighbourhood();
        gangNeighbourhood.action(mainPlayer, players.values());

        StringBuilder sb = new StringBuilder();

        int civilPlayersKilled = (int) civilPlayers.stream().filter(c -> c.getLifePoints() > 0).count();
        int civilPlayersAlive = civilPlayers.size() - civilPlayersKilled;

        sb.append(FIGHT_HAPPENED);
        sb.append(System.lineSeparator());
        sb.append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints()));
        sb.append(System.lineSeparator());
        sb.append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, civilPlayersKilled));
        sb.append(System.lineSeparator());
        sb.append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, civilPlayersAlive));

        return sb.toString();
    }

    private Gun createGun(String type, String name) {

        Gun gun = null;

        if (type.equals("Pistol")) {

            gun = new Pistol(name);
        } else if (type.equals("Rifle")) {

            gun = new Rifle(name);
        }
        return gun;
    }
}
