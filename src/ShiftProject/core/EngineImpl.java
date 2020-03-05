package ShiftProject.core;

import ShiftProject.core.interfaces.Controller;
import ShiftProject.core.interfaces.Engine;
import ShiftProject.models.shifts.*;
import ShiftProject.models.shifts.interfaces.Shift;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EngineImpl implements Engine {
    private Controller controller;
    private BufferedReader reader;
    private static final Shift[] SHIFTS = {new SecondShift()
            , new FirstShift()
            , new BigShift()
            , new DayForRest()
    };


    public EngineImpl(Controller controller) {
        this.controller = controller;
        this.reader = new BufferedReader(new InputStreamReader(System.in));

    }


    @Override
    public void run() {

        while (true) {
            String result = null;
            try {
                result = processInput();

                if (result.equals("exit")) {
                    System.out.println("The program finished succsefly!");
                    break;
                }
            } catch (NullPointerException | IllegalArgumentException | IOException e) {
                result = e.getMessage();
            }

            return;
        }
    }

    private String processInput() throws IOException {
        String shift = this.reader.readLine();
        LocalDate inputDate = LocalDate.parse(this.reader.readLine());
        LocalDate localDate = LocalDate.now();

        String result = null;

        doSchedule(localDate, shift, inputDate);

        controller.schedule();

        while (true) {

            String[] comand = this.reader.readLine().split("\\s+");

            if (comand[0].equals("find")) {
                System.out.println(controller.find(LocalDate.parse(comand[1])));

            } else if (comand[0].equalsIgnoreCase("exit")) {
                result = "exit";
                break;

            } else if (comand[0].equalsIgnoreCase("fromToDate")) {
                System.out.println(controller.fromToDate(LocalDate.parse(comand[1])
                        , LocalDate.parse(comand[2])));
            }
        }
        return result;
    }

    private void doSchedule(LocalDate localDate, String shift, LocalDate inputDate) {
        Shift shiftType;

        switch (shift) {
            case "FirstShift":
                shiftType = new FirstShift();
                controller.addShift(shiftType);
                controller.addLocalDate(localDate);

                iterShifts(localDate, inputDate, 2);

                break;

            case "SecondShift":
                shiftType = new SecondShift();
                controller.addShift(shiftType);
                controller.addLocalDate(localDate);

                iterShifts(localDate, inputDate, 1);

                break;

            case "BigShift":
                shiftType = new BigShift();
                controller.addShift(shiftType);
                controller.addLocalDate(localDate);

                iterShifts(localDate, inputDate, 3);
                break;

            case "RestDay":
                shiftType = new DayForRest();
                controller.addShift(shiftType);
                controller.addLocalDate(localDate);

                iterShifts(localDate, inputDate, 0);
                break;

        }
    }

    private void iterShifts(LocalDate localDate, LocalDate inputDate, int count) {

        while (localDate.isBefore(inputDate)) {
            localDate = localDate.plus(1, ChronoUnit.DAYS);
            controller.addLocalDate(localDate);

            controller.addShift(SHIFTS[count++]);

            if (count == SHIFTS.length) {
                count = 0;
            }
        }
    }
}
