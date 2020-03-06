package ShiftProject.core.interfaces;

import java.time.LocalDate;

public interface Validator {
    void validateInputDate();

    String validateRangeOfTheFindDay(LocalDate localDate);



}
