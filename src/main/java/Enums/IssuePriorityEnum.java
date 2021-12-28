package Enums;

public enum IssuePriorityEnum {
    HIGHEST("Highest"),
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low"),
    LOWEST("Lowest");

    private String priorityName;

    IssuePriorityEnum(String priorityName) {
        this.priorityName = priorityName;
    }

    public String getName() {
        return this.priorityName;
    }
}
