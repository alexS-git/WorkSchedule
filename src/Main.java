import ShiftProject.core.ControllerImpl;
import ShiftProject.core.EngineImpl;
import ShiftProject.core.interfaces.Controller;
import ShiftProject.core.interfaces.Engine;

public class Main {

    public static void main(java.lang.String[] args) {

        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();

    }
}
