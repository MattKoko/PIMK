package JiraJsonObjects.ModelObjects;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FieldsModel {
    private ProjectModel project = new ProjectModel();
    private IssuetypeModel issuetype = new IssuetypeModel();
    private String summary = "Auto-Auto 007 task";
    private List<String> components = new ArrayList<>(); //probably Strings
    private DescriptionModel description = new DescriptionModel();
    private ReporterModel reporter = new ReporterModel();
//    private List<String> fixVersion = new ArrayList<>();
    private PriorityModel priority = new PriorityModel();
    private List<String> labels = new ArrayList<>();
    private String customfield_10020 = null;
    private String customfield_10011 = null; // Epic name

    public FieldsModel() {
    }
}
