package APIConnection;

import CustomExceptions.JSONNotFoundException;
import DataParsers.JsonParserInternal;
import Utils.Log;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class APIConnection {
    private static final Log log = new Log(new Object(){}.getClass().getEnclosingClass());

    private static final String locationOfPayload = System.getProperty("user.dir") + "\\src\\main\\java\\DataStorage\\lastGeneratedJsonRequest.json";
    private static final String basicAuth = "Basic bWlldGtpdHdhcmR5QGdtYWlsLmNvbTo4WFRlcFZGN1JXUnQ4Y2xnbVdWQzI4RkY="; //API token 8XTepVF7RWRt8clgmWVC28FF
    private static final String jiraURL = "https://privatematkoko.atlassian.net/rest/api/3/issue";

    public static String sendPostRequestWithPayload() throws JSONNotFoundException {
        log.info("Preparing API request.");
        String payload = JsonParserInternal.getJsonAsString(locationOfPayload);

        RequestSpecification request = given()
                .header("Authorization", basicAuth)
                .header("accept", "application/json,text/javascript,*/*")
                .header("content-type", "application/json")
                .header("accept-language", "pl-PL,pl;q=0.9")
//                .param("updateHistory", true)
//                .param("applyDefaultValues", false)
                .relaxedHTTPSValidation()
                .body(payload);

        log.info("Sending API request.");
        Response response = request.post(jiraURL);
        String responseString = response.asString();
        Assert.assertNotNull(responseString);
        Integer responseStatusCode = response.getStatusCode();
        log.info("API RESPONSE CODE: " + responseStatusCode);
        Assert.assertTrue(responseStatusCode >= 200 && responseStatusCode < 300);
        return responseString;
    }
}
