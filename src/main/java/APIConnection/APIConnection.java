package APIConnection;

import CustomExceptions.JSONNotFoundException;
import DataParsers.JsonParserInternal;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class APIConnection {

    private static final String locationOfPayload = "C:\\Users\\kokos\\IdeaProjects\\JiraAutomation\\src\\main\\java\\DataStorage\\sampleTaskRequest01.json";
    private static final String basicAuth = "Basic bWlldGtpdHdhcmR5QGdtYWlsLmNvbTo4WFRlcFZGN1JXUnQ4Y2xnbVdWQzI4RkY="; //API token 8XTepVF7RWRt8clgmWVC28FF
    private static final String jiraURL = "https://privatematkoko.atlassian.net/rest/api/3/issue";

    public static String sendPostRequestWithPayload() throws JSONNotFoundException {
        String payload = JsonParserInternal.getJsonAsString(locationOfPayload);

        RequestSpecification request = given()
                .header("Authorization", basicAuth)
                .header("accept", "application/json,text/javascript,*/*")
                .header("content-type", "application/json")
                .header("accept-language", "pl-PL,pl;q=0.9")
                .param("updateHistory", true)
                .param("applyDefaultValues", false)
                .relaxedHTTPSValidation()
                .body(payload);

        String response = request.post(jiraURL).asString();
        Assert.assertNotNull(response);
        return response;
    }
}
