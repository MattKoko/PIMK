import CustomExceptions.JSONNotFoundException;
import DataParsers.JsonParserInternal;
import APIConnection.APIConnection;
import Enums.IssuePriorityEnum;
import Enums.IssueTypesEnum;
import JiraJsonObjects.JiraRequestHandler;
import JiraJsonObjects.ResponseObjects.JiraSearchResponseObject;
import Utils.Log;
import io.restassured.response.Response;

public class Main {
    private static final Log log = new Log(new Object(){}.getClass().getEnclosingClass());

    private static final String dataStoragePath = System.getProperty("user.dir") + "\\src\\main\\java\\DataStorage\\";

    private static final IssueTypesEnum typeOfIssue = IssueTypesEnum.TASK;

    public static void main(String[] args) throws JSONNotFoundException {
        searchForIssue();
    }

    public static void createNewIssue() throws JSONNotFoundException {
        //1. Prepare JSON
        JiraRequestHandler.createJiraNewIssueJson(typeOfIssue, dataStoragePath);
        //2. Send JSON with payload
        Response response = APIConnection.sendPostRequestForIssueCreation();
        //3. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void editSummaryOfIssue() throws JSONNotFoundException {
        String issueId = "PIMK-28";

        //1. Prepare JSON
        JiraRequestHandler.createJiraEditSummaryOfIssueJson(issueId, typeOfIssue, dataStoragePath);
        //2. Send JSON with payload
        Response response = APIConnection.sendPutRequestForSummaryEdit(issueId);
        //3. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void editDescriptionOfIssue() throws JSONNotFoundException {
        String issueId = "PIMK-28";

        //1. Prepare JSON
        JiraRequestHandler.createJiraEditDescriptionOfIssueJson(issueId, dataStoragePath);
        //2. Send JSON with payload
        Response response = APIConnection.sendPutRequestForDescriptionEdit(issueId);
        //3. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void editPriorityOfIssue() throws JSONNotFoundException {
        String issueId = "PIMK-28";
        IssuePriorityEnum newPriority = IssuePriorityEnum.HIGHEST;

        //1. Prepare JSON
        JiraRequestHandler.createJiraEditPriorityOfIssueJson(issueId, newPriority, dataStoragePath);
        //2. Send JSON with payload
        Response response = APIConnection.sendPutRequestForPriorityEdit(issueId);
        //3. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void editProjectDetails() throws JSONNotFoundException {
        //1. Send JSON
        Response response = APIConnection.sendPostRequestForProjectEdit();
        //2. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void deleteIssue() throws JSONNotFoundException {
        String issueId = "PIMK-37";

        //1. Send JSON
        Response response = APIConnection.sendDeleteRequestToDeleteIssue(issueId);
        //2. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void searchForIssue() throws JSONNotFoundException {
        String jql = "project = \"PIMK\" AND summary ~ \"TASK\" order BY created DESC";

        //1. Send JSON
        Response response = APIConnection.sendGetRequestForDescriptionEdit(jql);
        JiraSearchResponseObject jiraResponse = response.as(JiraSearchResponseObject.class);
        String foundIssues = jiraResponse.getFoundIssuesKeys();
        log.info("Issues found in project: " + foundIssues);

        //2. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }
}
