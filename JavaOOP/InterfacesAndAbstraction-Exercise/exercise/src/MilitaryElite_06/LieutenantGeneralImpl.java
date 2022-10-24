package MilitaryElite_06;

import java.util.ArrayList;
import java.util.List;

public class LieutenantGeneralImpl extends PrivateImpl implements LieutenantGeneral, Solder {

    private List<Private> privates;

    public LieutenantGeneralImpl(String firstName, String lastName, int id, double salary ) {
        super(firstName, lastName, id, salary);
        this.privates = this.privates = new ArrayList<>();;
    }

    public void addPrivate (Private priv) {
        privates.add(priv);
    }
}
