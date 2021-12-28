import CustomExceptions.JSONNotFoundException;
import DataParsers.JsonDataProvider;
import DataParsers.JsonParserInternal;
import APIConnection.APIConnection;
import DataParsers.RandomDataGenerator;
import Enums.IssueTypesEnum;
import JiraJsonObjects.DataStorageObjects.JiraIssueDataModel;
import JiraJsonObjects.JiraRequestHandler;
import JiraJsonObjects.RequestObjects.JiraIssueObject;
import Utils.Log;

import java.util.Random;

public class Main {
    private static final Log log = new Log(new Object(){}.getClass().getEnclosingClass());

    private static final String dataStoragePath = System.getProperty("user.dir") + "\\src\\main\\java\\DataStorage\\";

    private static final IssueTypesEnum typeOfIssue = IssueTypesEnum.TASK;

    public static void main(String[] args) throws JSONNotFoundException {
        //1. Prepare JSON
        JiraRequestHandler.createJiraIssueJson(typeOfIssue, dataStoragePath);
        //2. Send JSON
        String response = APIConnection.sendPostRequestWithPayload();
        //3. Save response
        JsonParserInternal.saveStringAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }
}
