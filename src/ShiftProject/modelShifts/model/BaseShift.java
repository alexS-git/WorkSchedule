package ShiftProject.modelShifts.model;

import ShiftProject.modelShifts.interfaces.Shift;

public abstract class BaseShift implements Shift {
    private String nameShift;

    protected BaseShift(String name) {
        this.nameShift = name;
    }

    @Override
    public String getShift() {
        return nameShift;
    }

}
