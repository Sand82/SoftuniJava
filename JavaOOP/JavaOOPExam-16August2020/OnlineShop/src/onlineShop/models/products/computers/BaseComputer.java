package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;

public abstract class BaseComputer extends BaseProduct implements Computer {

    private List<Component> components;
    private List<Peripheral> peripherals;

    public BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);

        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public List<Component> getComponents() {

        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {

        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {

        String componentType = component.getClass().getSimpleName();

        Component checkForComponent = getComponent(componentType);

        if (checkForComponent != null) {

            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT,
                    componentType, this.getClass().getSimpleName(), this.getId()));
        }
        components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {

        Component component = getComponent(componentType);

        if (component == null) {

            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT,
                    componentType, this.getClass().getSimpleName(), this.getId()));
        }
        components.remove(component);

        return component;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {

        String peripheralString = peripheral.getClass().getSimpleName();

        Peripheral peripheralToCheck = getPeripheral(peripheralString);

        if (peripheralToCheck != null) {

            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL,
                    peripheralString, this.getClass().getSimpleName(), this.getId()));
        }
        peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {

        Peripheral peripheral = getPeripheral(peripheralType);

        if (peripheral == null) {

            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL,
                    peripheralType, this.getClass().getSimpleName(), this.getId()));
        }
        peripherals.remove(peripheral);

        return peripheral;
    }

    @Override
    public String toString() {

        int componentSCount = components.size();

        double overallPerformance = getOverallPerformance() ;

        double price = getTottallPrice() + this.getPrice();

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Overall Performance: %.2f. Price: %.2f - %s: %s %s (Id: %d)",
                overallPerformance, price, this.getClass().getSimpleName(), this.getManufacturer(), this.getModel(), this.getId()));
        sb.append(System.lineSeparator());
        sb.append(String.format(" Components (%d):", componentSCount));
        sb.append(System.lineSeparator());
        for (Component component : components) {

            sb.append(String.format("  %s", component.toString()));
            sb.append(System.lineSeparator());
        }

        double averagePeripheralsPerformance = peripherals.size() != 0 ? peripherals.stream().mapToDouble(Peripheral::getOverallPerformance).average().getAsDouble() : 0.00;

        sb.append(String.format(" Peripherals  (%d); Average Overall Performance (%.2f):", peripherals.size(), averagePeripheralsPerformance));
        sb.append(System.lineSeparator());

        for (Peripheral peripheral : peripherals) {

            sb.append(String.format("  %s", peripheral.toString()));
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    public double getOverallPerformance() {

        double overallPerformanceComponents = components.stream().mapToDouble(Component::getOverallPerformance).sum();

        int count = components.size();
        double overallPerformance = overallPerformanceComponents / count;

        return overallPerformance;
    }

    public double getTottallPrice() {

        double priceComponents = components.stream().mapToDouble(Component::getTottallPrice).sum();
        double pricePeripherals= peripherals.stream().mapToDouble(Peripheral::getTottallPrice).sum();

        double price = priceComponents + pricePeripherals;

        return price;
    }

    private Component getComponent(String componentType) {

        return components.stream()
                .filter(c -> c.getClass().getSimpleName().equals(componentType))
                .findFirst().orElse(null);
    }

    private Peripheral getPeripheral(String peripheral) {

        return peripherals.stream().filter(c -> c.getClass().getSimpleName().equals(peripheral))
                .findFirst().orElse(null);
    }
}
