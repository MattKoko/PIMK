package APIConnection;

import CustomExceptions.JSONNotFoundException;
import DataParsers.JsonParserInternal;
import Utils.Log;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.protocol.RequestUserAgent;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class APIConnection {
    private static final Log log = new Log(new Object(){}.getClass().getEnclosingClass());

    private static final String locationOfPayload = System.getProperty("user.dir") + "\\src\\main\\java\\DataStorage\\lastGeneratedJsonRequest.json";
    private static final String basicAuth = "Basic bWlldGtpdHdhcmR5QGdtYWlsLmNvbTo4WFRlcFZGN1JXUnQ4Y2xnbVdWQzI4RkY="; //API token 8XTepVF7RWRt8clgmWVC28FF
    private static final String atlToken = "ba0d1224-c01f-4613-bc04-7d9c6e6c192f_0f10aff6e04f9be894a56d17cad80d56d26e6e42_lin"; //Description how to handle ATL Tokens: https://developer.atlassian.com/server/jira/platform/form-token-handling/
    private static final String jiraURLNewIssue = "https://privatematkoko.atlassian.net/rest/api/3/issue";
    private static final String jiraURLEditSummary = "https://privatematkoko.atlassian.net/rest/api/3/issue/PIMK-XXXX/";
    private static final String jiraURLEditDescription = "https://privatematkoko.atlassian.net/rest/internal/3/issue/PIMK-XXXX/description";
    private static final String jiraURLEditPriority = "https://privatematkoko.atlassian.net/rest/api/2/issue/PIMK-XXXX";
    private static final String jiraURLDeleteIssue = "https://privatematkoko.atlassian.net/rest/api/3/issue/PIMK-XXXX";
    private static final String jiraURLEditProject = "https://privatematkoko.atlassian.net/secure/project/EditProject.jspa";

    public static String sendPostRequestForIssueCreationWithPayload() throws JSONNotFoundException {
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
        Response response = request.post(jiraURLNewIssue);
        String responseString = response.asString();
        Assert.assertNotNull(responseString);
        Integer responseStatusCode = response.getStatusCode();
        log.info("API RESPONSE CODE: " + responseStatusCode);
        Assert.assertTrue(responseStatusCode >= 200 && responseStatusCode < 300);
        return responseString;
    }

    public static String sendPutRequestForSummaryEditWithPayload(String issueId) throws JSONNotFoundException {
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
        Response response = request.put(jiraURLEditSummary.replace("PIMK-XXXX", issueId));
        String responseString = response.asString();
        Assert.assertNotNull(responseString);
        Integer responseStatusCode = response.getStatusCode();
        log.info("API RESPONSE CODE: " + responseStatusCode);
        Assert.assertTrue(responseStatusCode >= 200 && responseStatusCode < 300);
        return responseString;
    }

    public static String sendPutRequestForDescriptionEditWithPayload(String issueId) throws JSONNotFoundException {
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
        Response response = request.put(jiraURLEditDescription.replace("PIMK-XXXX", issueId));
        String responseString = response.asString();
        Assert.assertNotNull(responseString);
        Integer responseStatusCode = response.getStatusCode();
        log.info("API RESPONSE CODE: " + responseStatusCode);
        Assert.assertTrue(responseStatusCode >= 200 && responseStatusCode < 300);
        return responseString;
    }

    public static String sendPutRequestForPriorityEditWithPayload(String issueId) throws JSONNotFoundException {
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
        Response response = request.put(jiraURLEditPriority.replace("PIMK-XXXX", issueId));
        String responseString = response.asString();
        Assert.assertNotNull(responseString);
        Integer responseStatusCode = response.getStatusCode();
        log.info("API RESPONSE CODE: " + responseStatusCode);
        Assert.assertTrue(responseStatusCode >= 200 && responseStatusCode < 300);
        return responseString;
    }

    public static String sendDeleteRequestToDeleteIssueWithPayload(String issueId) throws JSONNotFoundException {
        log.info("Preparing API request.");
        String payload = JsonParserInternal.getJsonAsString(locationOfPayload);

        RequestSpecification request = given()
                .header("Authorization", basicAuth)
                .header("accept", "application/json,text/javascript,*/*")
                .header("content-type", "application/json")
                .header("accept-language", "pl-PL,pl;q=0.9")
                .queryParam("deleteSubtasks", true)
                .relaxedHTTPSValidation()
                .body(payload);

        log.info("Sending API request.");
        Response response = request.delete(jiraURLDeleteIssue.replace("PIMK-XXXX", issueId));
        String responseString = response.asString();
        Assert.assertNotNull(responseString);
        Integer responseStatusCode = response.getStatusCode();
        log.info("API RESPONSE CODE: " + responseStatusCode);
        Assert.assertTrue(responseStatusCode >= 200 && responseStatusCode < 300);
        return responseString;
    }

    public static String sendPostRequestForProjectEditWithoutPayload() {
        log.info("Preparing API request.");
        String randomString = "ATL 01";

        RequestSpecification request = given()
                .config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
                .header("Authorization", basicAuth)
                .header("content-type", ContentType.URLENC)
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
                .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .header("accept-language", "pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("cookie", "atlassian.xsrf.token=" + atlToken)
                .formParam("name", "Praca Inz Mat Koko - " + randomString)
                .formParam("originalKey", "PIMK")
                .formParam("keyEdited", "false")
                .formParam("url", "")
                .formParam("avatarId", "10424")
                .formParam("description",  randomString)
                .formParam("pid", "10000")
                .formParam("atl_token", atlToken)
                .relaxedHTTPSValidation();

        log.info("Sending API request.");
        Response response = request.post(jiraURLEditProject);
        String responseString = response.asString();
        Assert.assertNotNull(responseString);
        Integer responseStatusCode = response.getStatusCode();
        log.info("API RESPONSE CODE: " + responseStatusCode);
        Assert.assertTrue(responseStatusCode >= 200 && responseStatusCode < 400); //Extended accepted range of responses for POST
        return responseString;
    }
}
