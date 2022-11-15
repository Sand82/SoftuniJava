package fairyShop.core;

import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private HelperRepository helperRepository = new HelperRepository();
    private PresentRepository presentRepository = new PresentRepository();
    private Shop shop = new ShopImpl();
    @Override
    public String addHelper(String type, String helperName) {

        Helper helper = createHelper(type, helperName);

        if (helper == null) {
            throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }

        helperRepository.add(helper);

        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {

        Instrument instrument = new InstrumentImpl(power);

        Helper helper = helperRepository.findByName(helperName);

        if (helper == null) {

            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }

        helper.addInstrument(instrument);

        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {

        presentRepository.add(new PresentImpl(presentName, energyRequired));

        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {

        List<Helper> collect = helperRepository.getModels().stream().filter(helper -> helper.getEnergy() > 50)
                .collect(Collectors.toList());

        if (collect.isEmpty()) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }

        int brokenInstruments = 0;

        Present present = presentRepository.findByName(presentName);

        for (Helper helper : collect) {

            shop.craft(present, helper);

            brokenInstruments += (int) helper.getInstruments().stream().filter(Instrument::isBroken).count();

            if (present.isDone()) {
                break;
            }
        }
        if (present.isDone()) {
            return String.format(PRESENT_DONE, presentName, "done") +
                    String.format(COUNT_BROKEN_INSTRUMENTS, brokenInstruments);
        }
        return String.format(PRESENT_DONE, presentName, "not done") +
                String.format(COUNT_BROKEN_INSTRUMENTS, brokenInstruments);
    }


    @Override
    public String report() {

        StringBuilder sb = new StringBuilder();

        int presentsCount = presentRepository.getModels().size();

        sb.append(String.format("%d presents are done!", presentsCount));
        sb.append(String.format(System.lineSeparator()));
        sb.append("Helpers info:");
        sb.append(String.format(System.lineSeparator()));

        for (Helper model : helperRepository.getModels()) {

            int countOfInstruments =(int) model.getInstruments().stream().filter(i -> !i.isBroken()).count();

            sb.append(String.format("Name: %s", model.getName()));
            sb.append(String.format(System.lineSeparator()));
            sb.append(String.format("Energy: %s", model.getEnergy()));
            sb.append(String.format(System.lineSeparator()));
            sb.append(String.format("Instruments: %d not broken left", countOfInstruments));
            sb.append(String.format(System.lineSeparator()));
        }

        return sb.toString().trim();
    }

    private Helper createHelper(String type, String helperName) {
        Helper helper = null;

        if (type.equals("Happy")) {

            helper = new Happy(helperName);

        } else if (type.equals("Sleepy")) {

            helper = new Sleepy(helperName);
        }

        return helper;
    }
}
