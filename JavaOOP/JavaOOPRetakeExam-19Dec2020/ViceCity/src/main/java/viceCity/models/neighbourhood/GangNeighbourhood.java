package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.Player;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GangNeighbourhood implements Neighbourhood {


    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {

        Player player = getPlayer(civilPlayers);

        Gun gun = getGun(mainPlayer);

        while (mainPlayer.isAlive()) {

            if (!gun.canFire()) {

                gun = getGun(mainPlayer);

                if (gun == null) {

                    break;
                }
            }

            int shottedBullets = gun.fire();

            if (player.getLifePoints() <= 0) {

                player = getPlayer(civilPlayers);

                if (player == null) {
                    break;
                }
            }

            player.takeLifePoints(shottedBullets);

            int civilPlayersShots = 0;

            Gun model = getGun(player);

            if (model != null) {

                if (model.getTotalBullets() > 0) {

                    civilPlayersShots += model.fire();
                }
            }

            mainPlayer.takeLifePoints(civilPlayersShots);

            if (mainPlayer.getLifePoints() <= 0) {

                break;
            }
        }
    }

    private static Gun getGun(Player mainPlayer) {

        return mainPlayer.getGunRepository().getModels().stream().filter(g -> g.getTotalBullets() > 0).findFirst().orElse(null);
    }

    private static Player getPlayer(Collection<Player> civilPlayers) {

        return civilPlayers.stream().filter(p -> p.getLifePoints() > 0).findFirst().orElse(null);
    }
}
