package JiraJsonObjects.ResponseObjects.ModelObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JiraResponseFieldModel {
    private IssueTypeResponseModel issuetype;
    private ProjectResponseModel project;
    private String created;
    private PriorityResponseModel priority;
    private String description;
    private String summary;
    private CreatorResponseModel creator;
    private ReporterResponseModel reporter;
}