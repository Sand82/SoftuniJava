package softuni.exam.models.entity;

public enum DayOfWeek {
    FRIDAY(1),
    SATURDAY(2),
    SUNDAY(3);

    private final int value;

    DayOfWeek(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
