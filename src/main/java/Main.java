import APIConnection.APIConnection;
import CustomExceptions.JSONNotFoundException;
import DataParsers.JsonParserInternal;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class Main {
    public static void main(String[] args) throws JSONNotFoundException {
        //1. Prepare JSON

        //2. Send JSON
        String response = APIConnection.sendPostRequestWithPayload();
        //3. Save response
        JsonParserInternal.saveStringAsJson(response, "C:\\Users\\kokos\\IdeaProjects\\JiraAutomation\\src\\main\\java\\DataStorage\\JiraAPIResponse01.json");
    }
}
