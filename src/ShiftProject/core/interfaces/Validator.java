package ShiftProject.core.interfaces;

import ShiftProject.modelShifts.interfaces.Shift;
import ShiftProject.repositores.interfaces.DateRepository;

import java.time.LocalDate;

public interface Validator {
    void validateInputDate();

    String validateRangeOfTheFindDay(LocalDate localDate,  DateRepository<Shift> shiftDateRepository);



}
