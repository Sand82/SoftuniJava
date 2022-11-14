package onlineShop.core;

import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.*;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {

    private Map<Integer, Computer> computers = new LinkedHashMap<>();
    private Map<Integer, Peripheral> peripherals = new LinkedHashMap<>();
    private Map<Integer, Component> components = new LinkedHashMap<>();

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {

        Computer computer = computers.get(id);

        if (computer != null) {

            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }

        if (!computerType.equals("Laptop") && !computerType.equals("DesktopComputer")) {

            throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }

        computer = createComputer(computerType, id, manufacturer, model, price);

        computers.put(id, computer);

        return String.format(ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {

        Computer computer = getComputer(computerId);

        Peripheral peripheral = peripherals.get(id);

        if (peripheral != null) {

            throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
        }

        if (!peripheralType.equals("Headset") && !peripheralType.equals("Keyboard") && !peripheralType.equals("Monitor") && !peripheralType.equals("Mouse")) {

            throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
        }

        peripheral = createPeripheral(peripheralType, id, manufacturer, model, price, overallPerformance, connectionType);

        computer.addPeripheral(peripheral);

        peripherals.put(id, peripheral);

        return String.format(ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {

        Computer computer = getComputer(computerId);

        Peripheral peripheral = computer.getPeripherals().stream().filter(p -> p.getClass().getSimpleName().equals(peripheralType)).findFirst().orElse(null);

        computer.removePeripheral(peripheralType);

        peripherals.remove(peripheral.getId(), peripheral);

        return String.format(REMOVED_PERIPHERAL, peripheralType, peripheral.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {

        Computer computer = getComputer(computerId);

        Component component = components.get(id);

        if (component != null) {

            throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
        }

        if (!componentType.equals("Motherboard") && !componentType.equals("PowerSupply") && !componentType.equals("RandomAccessMemory") &&
                !componentType.equals("SolidStateDrive") && !componentType.equals("VideoCard") && !componentType.equals("CentralProcessingUnit")) {

            throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
        }

        component = createComponent(componentType, id, manufacturer, model, price, overallPerformance, generation);

        computer.addComponent(component);
        components.put(id, component);

        return String.format(ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {

        Computer computer = getComputer(computerId);

        Component component = components.values().stream().filter(p -> p.getClass().getSimpleName().equals(componentType)).findFirst().orElse(null);

        computer.removeComponent(componentType);

        computers.remove(component.getId(), component);

        return String.format(String.format(REMOVED_COMPONENT, componentType, component.getId()));
    }

    @Override
    public String buyComputer(int id) {

        Computer computer = getComputer(id);

        computers.remove(id ,computer);

        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {

        List<Computer> filteredByPrice = new ArrayList<>();

        for (Computer comp : computers.values()) {

            if (comp.getTottallPrice() <= budget) {

                filteredByPrice.add(comp);
            }
        }

        filteredByPrice.sort((x1, x2) -> (int) (x2.getOverallPerformance() - x1.getOverallPerformance()));

       Computer bestComp = filteredByPrice.stream().findFirst().orElse(null);

        if (bestComp == null) {

            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER, budget));
        }

        computers.remove(bestComp);

        return bestComp.toString();
    }

    @Override
    public String getComputerData(int id) {

        Computer computer = getComputer(id);

        return computer.toString();
    }

    private Component createComponent(String componentType, int id, String manufacturer, String model, double price, double overallPerformance, int generation) {

        Component component = null;

        if (componentType.equals("Motherboard")) {

            component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals("PowerSupply")) {

            component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals("RandomAccessMemory")) {

            component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals("SolidStateDrive")) {

            component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals("VideoCard")) {

            component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals("CentralProcessingUnit")) {

            component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
        }

        return component;
    }

    private Peripheral createPeripheral(String peripheralType, int id, String manufacturer, String model, double price, double overallPerformance, String connectionType) {

        Peripheral peripheral = null;

        if (peripheralType.equals("Headset")) {

            peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
        } else if (peripheralType.equals("Keyboard")) {

            peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
        } else if (peripheralType.equals("Monitor")) {

            peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
        } else if (peripheralType.equals("Mouse")) {

            peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
        }

        return peripheral;
    }

    private Computer createComputer(String computerType, int id, String manufacturer, String model, double price) {

        Computer computer = null;

        if (computerType.equals("Laptop")) {

            computer = new Laptop(id, manufacturer, model, price);
        } else if (computerType.equals("DesktopComputer")) {

            computer = new DesktopComputer(id, manufacturer, model, price);
        }

        return computer;
    }

    private Computer getComputer(int computerId) {

        Computer computer = computers.get(computerId);

        if (computer == null) {

            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        return computer;
    }
}
