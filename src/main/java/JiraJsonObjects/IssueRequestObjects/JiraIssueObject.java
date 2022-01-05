package JiraJsonObjects.IssueRequestObjects;

import JiraJsonObjects.IssueRequestObjects.ModelObjects.FieldsModel;
import JiraJsonObjects.IssueRequestObjects.ModelObjects.UpdateModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JiraIssueObject {
    private FieldsModel fields;
    private UpdateModel update;

    public JiraIssueObject() {
        fields = new FieldsModel();
        update = new UpdateModel();
    }
}
