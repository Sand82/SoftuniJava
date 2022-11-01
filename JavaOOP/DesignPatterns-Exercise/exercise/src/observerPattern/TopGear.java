package observerPattern;

import java.util.ArrayList;
import java.util.List;

public class TopGear implements Subject{

    List<Observer> observers;

    public TopGear() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
            observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(String newMagazineName) {
        for (var observer: observers) {
            observer.update(newMagazineName);
        }
    }
}
