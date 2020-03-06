package ShiftProject.core;
import ShiftProject.core.interfaces.Validator;
import ShiftProject.modelShifts.interfaces.Shift;
import ShiftProject.repositores.interfaces.DateRepository;


import java.time.LocalDate;

import static ShiftProject.Constants.ConstantsImpl.*;

public class ValidatorImpl implements Validator{

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
}
