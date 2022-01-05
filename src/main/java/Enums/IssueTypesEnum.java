package Enums;

public enum IssueTypesEnum {

    //Values of issueTypeCode need to be set before use!
    STORY("Story", "Story"),
    TASK("Zadanie", "Task"),
    SUBTASK("Podzadanie", "Subtask"),
    BUG("Błąd w programie", "Bug"),
    EPIC("Epik", "Epic");

    private String polishName;
    private String englishName;

    IssueTypesEnum(String polishName, String englishName) {
        this.polishName = polishName;
        this.englishName = englishName;
    }

    public String getPolishName() {
        return this.polishName;
    }
    public String getEnglishName() {
        return this.englishName;
    }

}
