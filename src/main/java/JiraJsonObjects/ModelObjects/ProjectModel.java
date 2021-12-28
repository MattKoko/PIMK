package JiraJsonObjects.ModelObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectModel {
    private String id;

    public ProjectModel() {
        id = "00001";
    }
}
