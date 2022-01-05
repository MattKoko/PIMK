package JiraJsonObjects.ResponseObjects.ModelObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JiraResponseIssueModel {
    private String id;
    private String self;
    private String key;
    private JiraResponseFieldModel fields;
}
