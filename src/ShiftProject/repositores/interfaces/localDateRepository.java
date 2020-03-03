package ShiftProject.repositores.interfaces;

import ShiftProject.models.shifts.interfaces.Shift;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class localDateRepository implements Repository<Shift> {
    private Map<LocalDate, Shift> dates;

    public localDateRepository() {
        this.dates = new HashMap<>();
    }

    @Override
    public Collection<Shift> getShift() {
        return Collections.unmodifiableCollection(this.dates.values());
    }

    @Override
    public void add(LocalDate localDate ,Shift shift) {
        this.dates.put(localDate, shift);
    }

    @Override
    public Shift find(LocalDate localDate) {
        Shift shift = null;

        for (Map.Entry<LocalDate, Shift> localDateShiftEntry : dates.entrySet()) {
            if (localDate.equals(localDateShiftEntry.getKey())) {
                 shift = localDateShiftEntry.getValue();
            }
        }

        return shift;
    }
}
