package JiraJsonObjects.RequestObjects;

import JiraJsonObjects.RequestObjects.ModelObjects.FieldsModel;
import JiraJsonObjects.RequestObjects.ModelObjects.UpdateModel;
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
