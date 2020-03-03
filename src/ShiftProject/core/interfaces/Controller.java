package ShiftProject.core.interfaces;

import ShiftProject.models.shifts.interfaces.Shift;

import java.time.LocalDate;

public interface Controller {
    void schedule();

    void addLocalDate(LocalDate localDate);

    void addShift(Shift shift);

    String find(LocalDate localDate);

    String fromToDate(LocalDate fromLocalDate, LocalDate toLocalDate);

}
