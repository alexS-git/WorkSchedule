package ShiftProject.core;

import ShiftProject.core.interfaces.Controller;
import ShiftProject.core.interfaces.Engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import static ShiftProject.Constants.ConstantsImpl.*;

public class EngineImpl implements Engine {
    private Controller controller;
    private BufferedReader reader;
    private LocalDate currentDate;
    private LocalDate inputDate;
    private String shift;
    private ValidatorImpl validator;

    public EngineImpl(Controller controller) {

        this.controller = controller;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.currentDate = LocalDate.now();
        this.validator = new ValidatorImpl();
    }

    @Override
    public void run() {

        try {
            processInput();
        } catch (IOException e) {
            e.printStackTrace();
        }

        generedSchedule();

        while (true) {
            String result;

            try {
                result = processOutput();

                if (result.equals("exit")) {
                    System.out.println(PROGRAM_FINISHED_CORRECT);
                    return;
                }
            } catch (NullPointerException | IllegalArgumentException | IOException e) {
                result = e.getMessage();
            }

            System.out.println(result);
        }
    }

    private void processInput() throws IOException {


        System.out.println(CURRENT_SHIFT_TODAY);
        while (true) {
            shift = this.reader.readLine();

            shift = validator.validateShift(shift);

            if (!shift.equals("0")) {
                break;
            }
            System.out.println(INVALID_SHIFT);
            System.out.println(REPEAT_SHIFT);
        }

        System.out.println(SCHEDULE_RANGE);
        while (true) {
            try {
                inputDate = LocalDate.parse(this.reader.readLine());
                break;
            } catch (Exception e) {
                System.out.println(INVALID_DATE);
                System.out.println(REPEAT_DATE);
            }
        }
    }

    private void generedSchedule() {

        controller.doSchedule(currentDate, shift, inputDate);

        controller.schedule();
    }

    private String processOutput() throws IOException {

        String result;

        System.out.println(CHOOSE_COMMAND);
        String[] comands = this.reader.readLine().split("\\s+");

        String currentCommand = comands[0];



        switch (currentCommand) {
            case "find":
                result = comands[1] + " -> " + controller.find(LocalDate.parse(comands[1]));
                break;

            case "fromToDate":
                result = controller.fromToDate(LocalDate.parse(comands[1]),
                        LocalDate.parse(comands[2]));
                break;

            case "exit":
                result = EXIT;
                break;

            case "getShift":
                //TODO: wait for implementation!!!
                result = "asd";
                break;
            default:
                result = INVALID_COMMAND + System.lineSeparator() + ALL_COMMANDS;
                break;
        }

        return result;
    }
}
