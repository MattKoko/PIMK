package JiraJsonObjects.IssueRequestObjects.ModelObjects;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class ContentModel {
    private String type = "paragraph";
    private List<SecondLvlContentModel> content = Collections.singletonList(new SecondLvlContentModel());
}
