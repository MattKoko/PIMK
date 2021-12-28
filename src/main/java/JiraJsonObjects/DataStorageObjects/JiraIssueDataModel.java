package JiraJsonObjects.DataStorageObjects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JiraIssueDataModel {
    private List<String> issueSummary;
    private List<String> issueDescription;
}
