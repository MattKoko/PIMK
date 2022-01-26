package JiraJsonObjects.ProjectInfoResponseObjects;

import JiraJsonObjects.ProjectInfoResponseObjects.ModelObjects.ProjectInfoIssueTypeModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JiraProjectInfoResponseObject {
    private String id;
    private String key;
    private String name;
    private String description;
    private List<ProjectInfoIssueTypeModel> issueTypes;
}
