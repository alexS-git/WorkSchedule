package ShiftProject.repositores.interfaces;

import java.time.LocalDate;

public interface DateRepository<T> {

    void add(LocalDate localDate ,T model);

    T find(LocalDate localDate);

}
