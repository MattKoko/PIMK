package JiraJsonObjects.DataStorageObjects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JiraProjectDataRandomModel {
    private List<String> projectName;
    private List<String> projectCodes;
}
