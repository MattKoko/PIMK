package JiraJsonObjects.ModelObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssuetypeModel {
    private String id;

    public IssuetypeModel() {
        id = "00002";
    }
}
