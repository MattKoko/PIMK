import CustomExceptions.JSONNotFoundException;
import DataParsers.JsonParserInternal;
import APIConnection.APIConnection;
import Enums.IssuePriorityEnum;
import Enums.IssueTypesEnum;
import JiraJsonObjects.JiraRequestHandler;
import Utils.Log;

public class Main {
    private static final Log log = new Log(new Object(){}.getClass().getEnclosingClass());

    private static final String dataStoragePath = System.getProperty("user.dir") + "\\src\\main\\java\\DataStorage\\";

    private static final IssueTypesEnum typeOfIssue = IssueTypesEnum.TASK;

    public static void main(String[] args) throws JSONNotFoundException {
        deleteIssue();
    }

    public static void createNewIssue() throws JSONNotFoundException {
        //1. Prepare JSON
        JiraRequestHandler.createJiraNewIssueJson(typeOfIssue, dataStoragePath);
        //2. Send JSON
        String response = APIConnection.sendPostRequestForIssueCreationWithPayload();
        //3. Save response
        JsonParserInternal.saveStringAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void editSummaryOfIssue() throws JSONNotFoundException {
        String issueId = "PIMK-28";

        //1. Prepare JSON
        JiraRequestHandler.createJiraEditSummaryOfIssueJson(issueId, typeOfIssue, dataStoragePath);
        //2. Send JSON
        String response = APIConnection.sendPutRequestForSummaryEditWithPayload(issueId);
        //3. Save response
        JsonParserInternal.saveStringAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void editDescriptionOfIssue() throws JSONNotFoundException {
        String issueId = "PIMK-28";

        //1. Prepare JSON
        JiraRequestHandler.createJiraEditDescriptionOfIssueJson(issueId, dataStoragePath);
        //2. Send JSON
        String response = APIConnection.sendPutRequestForDescriptionEditWithPayload(issueId);
        //3. Save response
        JsonParserInternal.saveStringAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void editPriorityOfIssue() throws JSONNotFoundException {
        String issueId = "PIMK-28";
        IssuePriorityEnum newPriority = IssuePriorityEnum.HIGHEST;

        //1. Prepare JSON
        JiraRequestHandler.createJiraEditPriorityOfIssueJson(issueId, newPriority, dataStoragePath);
        //2. Send JSON
        String response = APIConnection.sendPutRequestForPriorityEditWithPayload(issueId);
        //3. Save response
        JsonParserInternal.saveStringAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void editProjectDetails() {
        //2. Send JSON
        String response = APIConnection.sendPostRequestForProjectEditWithoutPayload();
        //3. Save response
//        JsonParserInternal.saveStringAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void deleteIssue() throws JSONNotFoundException {
        String issueId = "PIMK-37";

        //2. Send JSON
        String response = APIConnection.sendDeleteRequestToDeleteIssueWithPayload(issueId);
        //3. Save response
        JsonParserInternal.saveStringAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

}
