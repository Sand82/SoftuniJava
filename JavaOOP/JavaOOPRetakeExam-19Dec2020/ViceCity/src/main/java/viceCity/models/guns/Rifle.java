package viceCity.models.guns;

public class Rifle extends BaseGun {

    private int shotsBulletCount = 0;
    private static final int TOTALBULLETS = 500;
    private static final int BULLETSPERBARREL = 50;

    public Rifle(String name) {
        super(name, BULLETSPERBARREL, TOTALBULLETS);
    }

    @Override
    public int fire() { //TODO

        if (TOTALBULLETS <= 0) {


            return shotsBulletCount;
        }

        setTotalBullets(getTotalBullets() - 5);

        shotsBulletCount += 5;

        return 5;
    }
}
