package JiraJsonObjects;

import DataParsers.JsonDataProvider;
import DataParsers.RandomDataGenerator;
import Enums.IssuePriorityEnum;
import Enums.IssueTypesEnum;
import JiraJsonObjects.DataStorageObjects.JiraIssueDataModel;
import JiraJsonObjects.ModelObjects.DescriptionModel;
import JiraJsonObjects.RequestObjects.JiraIssueObject;
import Utils.Log;

public class JiraRequestHandler {
    private static final Log log = new Log(new Object(){}.getClass().getEnclosingClass());

    public static void createJiraNewIssueJson(IssueTypesEnum typeOfIssue, String filePathToSaveJson) {
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

    public static void createJiraEditSummaryOfIssueJson(String issueId, IssueTypesEnum typeOfIssue, String filePathToSaveJson) {
        //Preparing random data for issue creation
        JiraIssueDataModel jiraIssueData = JsonDataProvider.getJiraObjectFromJson(filePathToSaveJson + "Input\\randomJiraIssueData.json", JiraIssueDataModel.class);
        String randomSummary = String.format("%s %s %s",
                RandomDataGenerator.getRandomElementFromList(jiraIssueData.getIssueSummary()),
                RandomDataGenerator.getRandomElementFromList(jiraIssueData.getIssueSummary()),
                RandomDataGenerator.getRandomElementFromList(jiraIssueData.getIssueSummary()));

        String issueSummary = String.format("%s - API - %s - %s - EDITED SUMMARY", typeOfIssue.name(), RandomDataGenerator.getRandomIssueNumber(), randomSummary);

        log.info("Preparing edit of issue's summary object for issue with id: " + issueId);

        JiraIssueObject issueObject = new JiraIssueObject();
        issueObject.setUpdate(null);
        issueObject.getFields().setSummary(issueSummary);
        issueObject.getFields().setProject(null);
        issueObject.getFields().setIssuetype(null);
        issueObject.getFields().setComponents(null);
        issueObject.getFields().setDescription(null);
        issueObject.getFields().setReporter(null);
        issueObject.getFields().setPriority(null);
        issueObject.getFields().setLabels(null);

        log.info("Edit of issue's summary object has been prepared.");

        JsonDataProvider.saveIssueObjectAsJsonFile(issueObject, filePathToSaveJson + "lastGeneratedJsonRequest.json");
    }

    public static void createJiraEditDescriptionOfIssueJson(String issueId, String filePathToSaveJson) {
        log.info("Preparing edit of issue's description object for issue with id: " + issueId);

        DescriptionModel descriptionModel = new DescriptionModel();
        descriptionModel.getContent().get(0).getContent().get(0).setText("New edited description of issue.");

        log.info("Edit of issue's description object has been prepared.");

        JsonDataProvider.saveIssueObjectAsJsonFile(descriptionModel, filePathToSaveJson + "lastGeneratedJsonRequest.json");
    }

    public static void createJiraEditPriorityOfIssueJson(String issueId, IssuePriorityEnum newPriority, String filePathToSaveJson) {
        String newPriorityIconURL = String.format("https://privatematkoko.atlassian.net/images/icons/priorities/%s.svg", newPriority.getName().toLowerCase());

        log.info("Preparing edit of issue's priority object for issue with id: " + issueId);

        JiraIssueObject issueObject = new JiraIssueObject();
        issueObject.setUpdate(null);
        issueObject.getFields().setSummary(null);
        issueObject.getFields().setProject(null);
        issueObject.getFields().setIssuetype(null);
        issueObject.getFields().setComponents(null);
        issueObject.getFields().setDescription(null);
        issueObject.getFields().setReporter(null);
        issueObject.getFields().setLabels(null);
        issueObject.getFields().getPriority().setName(newPriority.getName());
        issueObject.getFields().getPriority().setId(newPriority.getId());
        issueObject.getFields().getPriority().setIconUrl(newPriorityIconURL);

        log.info("Edit of issue's priority object has been prepared.");

        JsonDataProvider.saveIssueObjectAsJsonFile(issueObject, filePathToSaveJson + "lastGeneratedJsonRequest.json");
    }


    public static JiraIssueObject getJiraIssueFromFile(String filePath) {
        return JsonDataProvider.getJiraObjectFromJson(filePath, JiraIssueObject.class);
    }
}
