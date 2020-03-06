package ShiftProject.core;

import ShiftProject.core.interfaces.Controller;
import ShiftProject.core.interfaces.Validator;
import ShiftProject.modelShifts.BigShift;
import ShiftProject.modelShifts.DayForRest;
import ShiftProject.modelShifts.FirstShift;
import ShiftProject.modelShifts.SecondShift;
import ShiftProject.modelShifts.interfaces.Shift;
import ShiftProject.repositores.interfaces.DateRepository;
import ShiftProject.repositores.localDateDateRepositoryImpl;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.Deque;

import static ShiftProject.Constants.ConstantsImpl.*;

public class ControllerImpl implements Controller {
    private Deque<Shift> shifts;
    private Deque<LocalDate> dates;
    private DateRepository<Shift> shiftDateRepository;
    private Validator validator;
    private static final Shift[] SHIFTS = {new SecondShift()
            , new FirstShift()
            , new BigShift()
            , new DayForRest()
    };

    public ControllerImpl() {
        this.shifts = new ArrayDeque<>();
        this.dates = new ArrayDeque<>();
        this.shiftDateRepository = new localDateDateRepositoryImpl();
        this.validator = new ValidatorImpl();

    }

    @Override
    public void schedule() {
        while (dates.size() != 0) {

            Shift shift = shifts.poll();
            LocalDate localDate = dates.poll();

            shiftDateRepository.add(localDate, shift);
        }
    }

    @Override
    public void addLocalDate(LocalDate localDate) {
        this.dates.offer(localDate);
    }

    @Override
    public void addShift(Shift shift) {
        this.shifts.offer(shift);
    }

    @Override
    public String find(LocalDate localDate) {

//        try {
//            res = this.shiftDateRepository.find(localDate).getShift();
//        } catch (NullPointerException e) {
//            res = DATE_IS_OUT_OF_RANGE;
//        }

        return validator.validateRangeOfTheFindDay(localDate, this.shiftDateRepository);
    }

    @Override
    public String fromToDate(LocalDate fromLocalDate, LocalDate toLocalDate) {
        StringBuilder sb = new StringBuilder();

        toLocalDate = toLocalDate.plus(1, ChronoUnit.DAYS);
        while (fromLocalDate.isBefore(toLocalDate)) {
            sb.append(fromLocalDate);
            sb.append(" -> ");
            sb.append(find(fromLocalDate));
            sb.append(System.lineSeparator());
            fromLocalDate = fromLocalDate.plus(1, ChronoUnit.DAYS);
        }

        return sb.toString().trim();
    }


    public void doSchedule(LocalDate localDate, String shift, LocalDate inputDate) {
        Shift shiftType;

        switch (shift) {
            case "FirstShift":
                shiftType = new FirstShift();
                addShift(shiftType);
                addLocalDate(localDate);

                iterShifts(localDate, inputDate, 2);

                break;

            case "SecondShift":
                shiftType = new SecondShift();
                addShift(shiftType);
                addLocalDate(localDate);

                iterShifts(localDate, inputDate, 1);

                break;

            case "BigShift":
                shiftType = new BigShift();
                addShift(shiftType);
                addLocalDate(localDate);

                iterShifts(localDate, inputDate, 3);
                break;

            case "RestDay":
                shiftType = new DayForRest();
                addShift(shiftType);
                addLocalDate(localDate);

                iterShifts(localDate, inputDate, 0);
                break;

        }
    }

    public void iterShifts(LocalDate localDate, LocalDate inputDate, int count) {

        while (localDate.isBefore(inputDate)) {
            localDate = localDate.plus(1, ChronoUnit.DAYS);
            addLocalDate(localDate);

            addShift(SHIFTS[count++]);

            if (count == SHIFTS.length) {
                count = 0;
            }
        }
    }
}