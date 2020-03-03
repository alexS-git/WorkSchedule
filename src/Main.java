import ShiftProject.core.ControllerImpl;
import ShiftProject.core.EngineImpl;
import ShiftProject.core.interfaces.Controller;
import ShiftProject.core.interfaces.Engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(java.lang.String[] args) throws IOException {
        BufferedReader reader =  new BufferedReader(
                new InputStreamReader(
                        System.in
                )
        );

        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();

        System.out.println();


    }
}
