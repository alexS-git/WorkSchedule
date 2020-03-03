package ShiftProject.models.shifts;

import ShiftProject.models.shifts.interfaces.Shift;

public abstract class BaseShift implements Shift {
    private java.lang.String string;

    protected BaseShift(String name) {
        this.string = name;
    }

    @Override
    public java.lang.String getShift() {
        return string;
    }

}
