package Enums;

public enum IssuePriorityEnum {
    HIGHEST("Highest", "1"),
    HIGH("High", "2"),
    MEDIUM("Medium", "3"),
    LOW("Low", "4"),
    LOWEST("Lowest", "5");

    private String priorityName;
    private String priorityId;

    IssuePriorityEnum(String priorityName, String priorityId) {
        this.priorityName = priorityName;
        this.priorityId = priorityId;
    }

    public String getName() {
        return this.priorityName;
    }
    public String getId() {
        return this.priorityId;
    }
}
