package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public enum EnumSlot {
    SLOT_1(LocalTime.of(7, 0, 0)),
    SLOT_2(LocalTime.of(9, 0, 0)),
    SLOT_3(LocalTime.of(11, 0, 0)),
    SLOT_4(LocalTime.of(13, 0, 0)),
    SLOT_5(LocalTime.of(15, 0, 0));

    private final LocalTime startTime;
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    private EnumSlot(LocalTime startTime) {
        this.startTime = startTime;
    }

    private String getStartTime() {
        return startTime.format(timeFormatter);
    }

    public static String getTimeSlotInt(int value) {
        EnumSlot slot = null;
        switch (value) {
            case 1 -> {
                slot = EnumSlot.SLOT_1;
                return slot.getStartTime();
            }
            case 2 -> {
                slot = EnumSlot.SLOT_2;
                return slot.getStartTime();
            }
            case 3 -> {
                slot = EnumSlot.SLOT_3;
                return slot.getStartTime();
            }
            case 4 -> {
                slot = EnumSlot.SLOT_4;
                return slot.getStartTime();
            }
            case 5 -> {
                slot = EnumSlot.SLOT_5;
                return slot.getStartTime();
            }
            default -> throw new IllegalArgumentException("Giá trị không hợp lệ: " + value);
        }
    }
    
}
