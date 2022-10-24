package MilitaryElite_06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LieutenantGeneralImpl extends PrivateImpl implements LieutenantGeneral, Solder {

    private List<Private> privates = new ArrayList<>();

    public LieutenantGeneralImpl(String firstName, String lastName, int id, double salary, List<Private> privates) {
        super(firstName, lastName, id, salary);
        this.privates.addAll(privates);
    }

    public void addPrivate (Private priv) {
        privates.add(priv);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(System.lineSeparator());
        sb.append("Privates:").append(System.lineSeparator());

        Collections.sort(privates, Comparator.comparingInt(Private::getId).reversed());

        for (Private aPrivate : privates) {
            sb.append("  " + aPrivate.toString() + System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
