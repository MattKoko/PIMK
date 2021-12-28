package Enums;

public enum IssueTypesEnum {
    STORY("10001"),
    TASK("10002"),
    BUG("10004"),
    EPIC("10000");

    private String issueTypeCode;

    IssueTypesEnum(String issueTypeCode) {
        this.issueTypeCode = issueTypeCode;
    }

    public String getCode() {
        return this.issueTypeCode;
    }
}
