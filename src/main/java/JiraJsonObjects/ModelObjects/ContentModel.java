package JiraJsonObjects.ModelObjects;

import java.util.Collections;
import java.util.List;

public class ContentModel {
    private String type = "paragraph";
    private List<SecondLvlContentModel> content = Collections.singletonList(new SecondLvlContentModel());
}
