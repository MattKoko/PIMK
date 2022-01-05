package JiraJsonObjects.ResponseObjects;

import JiraJsonObjects.ResponseObjects.ModelObjects.JiraResponseIssueModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class JiraSearchResponseObject {
    private String expand;
    private String startAt;
    private String maxResults;
    private String total;
    private List<JiraResponseIssueModel> issues;

    public String getFoundIssuesKeys() {
        List<String> liftOfFoundIssues = new ArrayList<>();

        try {
            for(JiraResponseIssueModel jiraResponseIssue : issues) {
                liftOfFoundIssues.add(jiraResponseIssue.getKey());
            }
        } catch (Exception e) {
            liftOfFoundIssues.add("No connection to Jira!");
        }

        if(liftOfFoundIssues.size() == 0) {
            liftOfFoundIssues.add("None issues found!");
        }

        return String.valueOf(liftOfFoundIssues);
    }
}
