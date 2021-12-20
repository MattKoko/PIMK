package Enums;

public enum IssueTypesCodes{
    TASK("10002"),
    STORY("10001"),
    BUG("10004"),
    EPIC("10000");

    private String issueTypeCode;

    IssueTypesCodes(String issueTypeCode) {
        this.issueTypeCode = issueTypeCode;
    }

    public String getCode() {
        return this.issueTypeCode;
    }
}
