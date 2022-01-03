package JiraJsonObjects.ModelObjects;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class DescriptionModel {
    private int version = 1;
    private String type = "doc";
    private List<ContentModel> content = Collections.singletonList(new ContentModel());
}
