package JiraJsonObjects.ModelObjects;

import java.util.Collections;
import java.util.List;

public class DescriptionModel {
    private int version = 1;
    private String type = "doc";
    private List<ContentModel> content = Collections.singletonList(new ContentModel());
}
