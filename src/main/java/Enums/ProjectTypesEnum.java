package Enums;

public enum ProjectTypesEnum {
    SCRUMTEAM("SCRUM-TEAM", "com.pyxis.greenhopper.jira:gh-simplified-agility-scrum"), // - Project Scrum zarzadzany przez zespol
    SCRUMFIRM("SCRUM-FIRM", "com.pyxis.greenhopper.jira:gh-simplified-scrum-classic"), // - Project Scrum zarzadzany przez firme
    KANBANTEAM("KANBAN-TEAM", "com.pyxis.greenhopper.jira:gh-simplified-agility-kanban"), // - Project Kanban zarzadzany przez zespol
    KANBANFIRM("KANBAN-FIRM", "com.pyxis.greenhopper.jira:gh-simplified-kanban-classic"), // - Project Kanban zarzadzany przez firme
    BUGTRACKING("BUG-TRACKING", "com.pyxis.greenhopper.jira:gh-simplified-basic"); // - Project 'Bug Tracking'

    private String projectTypeName;
    private String projectTypeString;

    ProjectTypesEnum(String projectTypeName, String projectTypeString) {
        this.projectTypeName = projectTypeName;
        this.projectTypeString = projectTypeString;
    }

    public String getProjectTypeName() {
        return this.projectTypeName;
    }

    public String getProjectTypeString() {
        return this.projectTypeString;
    }
}
