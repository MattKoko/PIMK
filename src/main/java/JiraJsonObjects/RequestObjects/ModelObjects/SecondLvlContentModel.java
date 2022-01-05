package JiraJsonObjects.RequestObjects.ModelObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecondLvlContentModel {
    public String type = "text";
    public String text = "Not very detailed description of the issue";
}
