package JiraJsonObjects;

import DataParsers.JsonDataProvider;
import DataParsers.RandomDataGenerator;
import Enums.IssueTypesEnum;
import JiraJsonObjects.DataStorageObjects.JiraIssueDataModel;
import JiraJsonObjects.RequestObjects.JiraIssueObject;
import Utils.Log;

public class JiraRequestHandler {
    private static final Log log = new Log(new Object(){}.getClass().getEnclosingClass());

    public static void createJiraIssueJson(IssueTypesEnum typeOfIssue, String filePathToSaveJson) {
        //Preparing random data for issue creation
        JiraIssueDataModel jiraIssueData = JsonDataProvider.getJiraObjectFromJson(filePathToSaveJson + "Input\\randomJiraIssueData.json", JiraIssueDataModel.class);
        String randomSummary = String.format("%s %s %s",
                RandomDataGenerator.getRandomElementFromList(jiraIssueData.getIssueSummary()),
                RandomDataGenerator.getRandomElementFromList(jiraIssueData.getIssueSummary()),
                RandomDataGenerator.getRandomElementFromList(jiraIssueData.getIssueSummary()));

        String issueSummary = String.format("%s - API - %s - %s", typeOfIssue.name(), RandomDataGenerator.getRandomIssueNumber(), randomSummary);
        log.info("Preparing issue object with name: " + issueSummary);

        JiraIssueObject issueObject = new JiraIssueObject();
        issueObject.getFields().setSummary(issueSummary);
        issueObject.getFields().getProject().setId("10000");
        issueObject.getFields().getIssuetype().setId(typeOfIssue.getCode());
        if(typeOfIssue.equals(IssueTypesEnum.EPIC)) {
            issueObject.getFields().setCustomfield_10011(issueSummary);
        }
        log.info("Issue object has been prepared.");

        JsonDataProvider.saveIssueObjectAsJsonFile(issueObject, filePathToSaveJson + "lastGeneratedJsonRequest.json");
    }

    public static JiraIssueObject getJiraIssueFromFile(String filePath) {
        return JsonDataProvider.getJiraObjectFromJson(filePath, JiraIssueObject.class);
    }
}
