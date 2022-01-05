package JiraJsonObjects.NewProjectRequestObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JiraNewProjectRequestObject {
    private String name;
    private String key;
    private String templateKey;
    private String accessLevel = "FREE";
}
