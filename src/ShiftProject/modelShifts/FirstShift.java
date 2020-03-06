package ShiftProject.modelShifts;

import ShiftProject.modelShifts.model.BaseShift;

public class FirstShift extends BaseShift {
    private static final String SHIFT_NAME = "FirstShift [10:00 - 18:00]";

    public FirstShift() {
        super(SHIFT_NAME);
    }
}
