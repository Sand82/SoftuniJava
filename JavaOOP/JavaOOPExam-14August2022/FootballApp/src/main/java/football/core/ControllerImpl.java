package football.core;


import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

import static football.common.ConstantMessages.*;
import static football.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private SupplementRepository supplements;
    private Collection<Field> fields;

    public ControllerImpl() {

        this.supplements = new SupplementRepositoryImpl();
        this.fields = new ArrayList<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {

        if (!fieldType.equals("ArtificialTurf") && !fieldType.equals("NaturalGrass")) {

            throw new NullPointerException(INVALID_FIELD_TYPE);
        }

        Field field = createField(fieldType, fieldName);

        fields.add(field);

        return String.format(SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {

        if (!type.equals("Powdered") && !type.equals("Liquid")) {

            throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }

        Supplement supplement = createSupplement(type);

        supplements.add(supplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {

        Field field = getField(fieldName);

        Supplement supplement = supplements.findByType(supplementType);

        if (supplement == null) {

            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }

        field.addSupplement(supplement);
        supplements.remove(supplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {

        if (!playerType.equals("Men") && !playerType.equals("Women")) {

            throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }

        Player player = createPlayer(playerType, playerName, nationality, strength);

        Field field = getField(fieldName);

        String currPlayerType = player.getClass().getSimpleName();
        String currFieldType = field.getClass().getSimpleName();

        boolean isValidWomanField = currFieldType.equals("ArtificialTurf") &&
                currPlayerType.equals("Women");
        boolean isValidMenField = currFieldType.equals("NaturalGrass") &&
                currPlayerType.equals("Men");


        if (!isValidMenField && !isValidWomanField) {

            return FIELD_NOT_SUITABLE;
        }

        field.addPlayer(player);

        return String.format(SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, currPlayerType, fieldName);
    }

    @Override
    public String dragPlayer(String fieldName) {

        Field field = getField(fieldName);
        int playersCount = field.getPlayers().size();

        return String.format(PLAYER_DRAG, playersCount);
    }

    @Override
    public String calculateStrength(String fieldName) {

        Field field = getField(fieldName);

        int fieldStrength = field.getPlayers().stream().mapToInt(Player::getStrength).sum();

        return String.format(STRENGTH_FIELD, fieldName, fieldStrength);
    }

    @Override
    public String getStatistics() {

        StringBuilder sb = new StringBuilder();

        for (Field field : fields) {
            sb.append(field.getInfo());
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    private Player createPlayer(String playerType, String playerName, String nationality, int strength) {
        Player player = null;

        if (playerType.equals("Men")) {
            player = new Men(playerName, nationality, strength);
        } else if (playerType.equals("Women")) {
            player = new Women(playerName, nationality, strength);
        }

        return player;
    }

    private Supplement createSupplement(String type) {
        Supplement supplement = null;

        if (type.equals("Powdered")) {
            supplement = new Powdered();
        } else if (type.equals("Liquid")) {
            supplement = new Liquid();
        }

        return supplement;
    }

    private Field createField(String fieldType, String fieldName) {
        Field field = null;

        if (fieldType.equals("ArtificialTurf")) {
            field = new ArtificialTurf(fieldName);
        } else if (fieldType.equals("NaturalGrass")) {
            field = new NaturalGrass(fieldName);
        }

        return field;
    }

    private Field getField(String fieldName) {
        return fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
    }
}
