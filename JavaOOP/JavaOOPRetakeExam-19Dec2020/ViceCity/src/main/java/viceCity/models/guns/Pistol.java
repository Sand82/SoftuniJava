package viceCity.models.guns;

public class Pistol extends BaseGun {

    private int shotsBulletCount = 0;
    private static final int TOTALBULLETS = 100;
    private static final int BULLETSPERBARREL = 10;

    public Pistol(String name) {
        super(name, BULLETSPERBARREL, TOTALBULLETS);
    }

    @Override
    public int fire() {  //TODO

        if (TOTALBULLETS <= 0) {


            return shotsBulletCount;
        }

        setTotalBullets(getTotalBullets() - 1);

        shotsBulletCount ++;

        return 1;
    }
}
