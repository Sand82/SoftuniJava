package MilitaryElite_06;

import java.util.List;

public class LieutenantGeneralImpl extends PrivateImpl implements LieutenantGeneral, Solder {

    private List<Private> privates;

    public LieutenantGeneralImpl(String firstName, String lastName, int id, double salary, List<Private> privates) {
        super(firstName, lastName, id, salary);
        this.privates = privates;
    }

    public void addPrivate (Private priv) {
        privates.add(priv);
    }
}
