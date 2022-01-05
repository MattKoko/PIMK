package JiraJsonObjects.RequestObjects.ModelObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueTypeModel {
    private String id;

    public IssueTypeModel() {
        id = "00002";
    }
}
