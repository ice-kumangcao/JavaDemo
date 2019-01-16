package designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ice
 * @date 19-1-7 下午4:30
 */
public class ObserverTest {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        Observer observer = new ConcreteObserver();
        Observer observer1 = new ConcreteObserver();
        subject.attach(observer);
        subject.attach(observer1);

        subject.change("new State");
    }
}

abstract class Subject {

    private List<Observer> list = new ArrayList<>();

    public void attach(Observer observer) {
        list.add(observer);
        System.out.println("Attach an observer");
    }

    public void delete(Observer observer) {
        list.remove(observer);
    }

    public void nodifyObservers(String newState) {
        for (Observer observer : list) {
            observer.update(newState);
        }
    }
}
class ConcreteSubject extends Subject {
    private String state;

    public String getState() {
        return state;
    }

    public void change(String newState) {
        state = newState;
        System.out.println("主题状态为:" + state);
        this.nodifyObservers(state);
    }
}

interface Observer {
    void update(String state);
}
class ConcreteObserver implements Observer {

    private String observerState;

    @Override
    public void update(String state) {
        observerState = state;
        System.out.println("状态为: " + observerState);
    }
}