package ShiftProject.core.interfaces;

import ShiftProject.modelShifts.interfaces.Shift;

import java.time.LocalDate;

public interface Controller {
    void schedule();

    void addLocalDate(LocalDate localDate);

    void addShift(Shift shift);

    String find(LocalDate localDate);

    String fromToDate(LocalDate fromLocalDate, LocalDate toLocalDate);

    void doSchedule(LocalDate localDate, String shift, LocalDate inputDate);

    void iterShifts(LocalDate localDate, LocalDate inputDate, int count);
}
