package ShiftProject.core;

import ShiftProject.core.interfaces.Controller;
import ShiftProject.models.shifts.interfaces.Shift;
import ShiftProject.repositores.interfaces.Repository;
import ShiftProject.repositores.interfaces.localDateRepository;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.Deque;

public class ControllerImpl implements Controller {
    private Deque<Shift> shifts;
    private Deque<LocalDate> dates;
    private Repository<Shift> shiftRepository;

    public ControllerImpl() {
        this.shifts = new ArrayDeque<>();
        this.dates = new ArrayDeque<>();
        this.shiftRepository = new localDateRepository();

    }

    @Override
    public void schedule() {
        while (dates.size() != 0) {

            Shift shift = shifts.poll();
            LocalDate localDate = dates.poll();

            shiftRepository.add(localDate, shift);
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
        return this.shiftRepository.find(localDate).getShift();
    }

    @Override
    public String fromToDate(LocalDate fromLocalDate, LocalDate toLocalDate) {
        StringBuilder sb = new StringBuilder();

        toLocalDate = toLocalDate.plus(1, ChronoUnit.DAYS);
        while (fromLocalDate.isBefore(toLocalDate)) {
            sb.append(fromLocalDate);
            sb.append(" - ");
            sb.append(find(fromLocalDate));
            sb.append(System.lineSeparator());
            fromLocalDate = fromLocalDate.plus(1, ChronoUnit.DAYS);
        }

        return sb.toString().trim();
    }

}
