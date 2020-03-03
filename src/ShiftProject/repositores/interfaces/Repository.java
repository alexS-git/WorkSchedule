package ShiftProject.repositores.interfaces;

import java.time.LocalDate;
import java.util.Collection;

public interface Repository<T> {
    Collection<T> getShift();

    void add(LocalDate localDate ,T model);

    T find(LocalDate localDate);

}
