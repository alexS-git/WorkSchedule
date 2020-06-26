package ShiftProject.core;

import ShiftProject.core.interfaces.Validator;
import ShiftProject.modelShifts.interfaces.Shift;
import ShiftProject.repositores.interfaces.DateRepository;


import java.time.LocalDate;

import static ShiftProject.Constants.ConstantsImpl.*;

public class ValidatorImpl implements Validator {

    public ValidatorImpl() {

    }


    @Override
    public void validateInputDate() {

    }

    @Override
    public String validateRangeOfTheFindDay(LocalDate localDate, DateRepository<Shift> shiftDateRepository) {

        String res;

        try {
            res = shiftDateRepository.find(localDate).getShift();
        } catch (NullPointerException e) {
            res = DATE_IS_OUT_OF_RANGE;
        }
        return res;
    }

    @Override
    public String validateShift(String shift) {

        if (shift.equalsIgnoreCase("1")
                || shift.equalsIgnoreCase("first")
                || shift.equalsIgnoreCase("firstShift")) {

            shift = "FirstShift";
        } else if (shift.equalsIgnoreCase("2")
                || shift.equalsIgnoreCase("second")
                || shift.equalsIgnoreCase("secondshift")) {

            shift = "SecondShift";
        } else if (shift.equalsIgnoreCase("3")
                || shift.equalsIgnoreCase("big")
                || shift.equalsIgnoreCase("bigshift")) {

            shift = "BigShift";
        } else if (shift.equalsIgnoreCase("rest")
                || shift.equalsIgnoreCase("off")
                || shift.equalsIgnoreCase("dayoff")
                || shift.equalsIgnoreCase("restday")) {

            shift = "RestDay";
        } else {

            shift = "0";
        }

        return shift;
    }
}
