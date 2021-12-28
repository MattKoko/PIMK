package JiraJsonObjects.RequestObjects;

import JiraJsonObjects.ModelObjects.FieldsModel;
import JiraJsonObjects.ModelObjects.UpdateModel;
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
