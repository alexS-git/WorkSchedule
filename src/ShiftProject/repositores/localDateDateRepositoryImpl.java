package ShiftProject.repositores;

import ShiftProject.modelShifts.interfaces.Shift;
import ShiftProject.repositores.interfaces.DateRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class localDateDateRepositoryImpl implements DateRepository<Shift> {
    private Map<LocalDate, Shift> dates;

    public localDateDateRepositoryImpl() {
        this.dates = new HashMap<>();
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
