package designpattern;

import java.util.LinkedHashMap;

import static java.sql.DriverManager.println;

/**
 * @author ice
 * @date 19-1-7 下午5:06
 */
public class StateTest {
    public static void main(String[] args) {
        State state = new ConcreteStateB();
        Context context = new Context();
        context.setState(state);
        context.request("test");
    }
}
class Context {
    private State state;

    public void setState(State state) {
        this.state = state;
    }

    public void request(String sampleParameter) {
        state.handle(sampleParameter);
    }
}
interface State {

    public void handle(String sampleParameter);
}
class ConcreteStateA implements State {

    @Override
    public void handle(String sampleParameter) {
        System.out.println("ConcreteStateA handle : " + sampleParameter);
    }
}
class ConcreteStateB implements State {

    @Override
    public void handle(String sampleParameter) {
        System.out.println("ConcreteStateB handle:" + sampleParameter);
    }
}
