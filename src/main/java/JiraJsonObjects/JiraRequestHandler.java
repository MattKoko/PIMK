package JiraJsonObjects;

import DataParsers.JsonDataProvider;
import DataParsers.RandomDataGenerator;
import Enums.IssuePriorityEnum;
import Enums.IssueTypesEnum;
import Enums.ProjectTypesEnum;
import JiraJsonObjects.DataStorageObjects.JiraIssueDataRandomModel;
import JiraJsonObjects.DataStorageObjects.JiraProjectDataRandomModel;
import JiraJsonObjects.IssueRequestObjects.ModelObjects.DescriptionModel;
import JiraJsonObjects.IssueRequestObjects.JiraIssueObject;
import JiraJsonObjects.NewProjectRequestObjects.JiraNewProjectRequestObject;
import Utils.Log;

public class JiraRequestHandler {
    private static final Log log = new Log(new Object(){}.getClass().getEnclosingClass());

    public static void createJiraNewIssueJson(String projectId, String projectName, String issueName, String issueTypeId, IssueTypesEnum typeOfIssue, String filePathToSaveJson) {
        //Preparing random data for issue creation
        JiraIssueDataRandomModel jiraIssueData = JsonDataProvider.getJiraObjectFromJson(filePathToSaveJson + "Input\\randomJiraIssueData.json", JiraIssueDataRandomModel.class);
        String randomSummary = (issueName == null || issueName.equals(""))
                ? String.format("%s %s %s", RandomDataGenerator.getRandomElementFromList(jiraIssueData.getIssueSummary()),
                    RandomDataGenerator.getRandomElementFromList(jiraIssueData.getIssueSummary()),
                    RandomDataGenerator.getRandomElementFromList(jiraIssueData.getIssueSummary()))
                : issueName;

        String issueSummary = String.format("%s - API - %s - %s", typeOfIssue.name(), RandomDataGenerator.getRandomIssueNumber(), randomSummary);
        log.info("Preparing issue object with name: " + issueSummary);

        JiraIssueObject issueObject = new JiraIssueObject();
        issueObject.getFields().setSummary(issueSummary);
        issueObject.getFields().getProject().setId(projectId);
        issueObject.getFields().getIssuetype().setId(issueTypeId);

        if(!projectName.contains("FIRM")) {
            issueObject.getFields().setPriority(null);
        }
        if(typeOfIssue.equals(IssueTypesEnum.EPIC)) {
            issueObject.getFields().setCustomfield_10011(issueSummary);
        }
        log.info("Issue object has been prepared.");

        JsonDataProvider.saveIssueObjectAsJsonFile(issueObject, filePathToSaveJson + "lastGeneratedJsonRequest.json");
    }

    public static void createJiraEditSummaryOfIssueJson(String issueId, IssueTypesEnum typeOfIssue, String filePathToSaveJson) {
        //Preparing random data for issue creation
        JiraIssueDataRandomModel jiraIssueData = JsonDataProvider.getJiraObjectFromJson(filePathToSaveJson + "Input\\randomJiraIssueData.json", JiraIssueDataRandomModel.class);
        String randomSummary = String.format("%s %s %s",
                RandomDataGenerator.getRandomElementFromList(jiraIssueData.getIssueSummary()),
                RandomDataGenerator.getRandomElementFromList(jiraIssueData.getIssueSummary()),
                RandomDataGenerator.getRandomElementFromList(jiraIssueData.getIssueSummary()));

        String issueSummary = String.format("%s - API - %s - %s - (edited summary)", typeOfIssue.name(), RandomDataGenerator.getRandomIssueNumber(), randomSummary);

        log.info("Preparing edit of issue's summary object for issue with id: " + issueId);

        JiraIssueObject issueObject = new JiraIssueObject();
        issueObject.setUpdate(null);
        issueObject.getFields().setSummary(issueSummary);
        issueObject.getFields().setProject(null);
        issueObject.getFields().setIssuetype(null);
//        issueObject.getFields().setComponents(null);
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
//        issueObject.getFields().setComponents(null);
        issueObject.getFields().setDescription(null);
        issueObject.getFields().setReporter(null);
        issueObject.getFields().setLabels(null);
        issueObject.getFields().getPriority().setName(newPriority.getName());
        issueObject.getFields().getPriority().setId(newPriority.getId());
        issueObject.getFields().getPriority().setIconUrl(newPriorityIconURL);

        log.info("Edit of issue's priority object has been prepared.");

        JsonDataProvider.saveIssueObjectAsJsonFile(issueObject, filePathToSaveJson + "lastGeneratedJsonRequest.json");
    }

    public static void createJiraCreateNewProjectJson(String newProjectName, ProjectTypesEnum projectType, String filePathToSaveJson) {
//        log.info("Preparing edit of issue's priority object for issue with id: " + issueId);

        //Preparing random data for issue creation
        JiraProjectDataRandomModel jiraProjectData = JsonDataProvider.getJiraObjectFromJson(filePathToSaveJson + "Input\\randomJiraProjectData.json", JiraProjectDataRandomModel.class);
        String randomName = (newProjectName == null || newProjectName.equals(""))
                ? RandomDataGenerator.getRandomElementFromList(jiraProjectData.getProjectName())
                : newProjectName;

        String projectNumber = RandomDataGenerator.getRandomProjectNumber();
        String projectName = String.format("API Project - %s - %s - %s", randomName, projectNumber, projectType.getProjectTypeName());
        String projectKey = "API" + projectNumber;

        JiraNewProjectRequestObject jiraNewProjectRequestObject = new JiraNewProjectRequestObject();
        jiraNewProjectRequestObject.setName(projectName);
        jiraNewProjectRequestObject.setKey(projectKey);
        jiraNewProjectRequestObject.setTemplateKey(projectType.getProjectTypeString());

//        log.info("Edit of issue's priority object has been prepared.");

        JsonDataProvider.saveIssueObjectAsJsonFile(jiraNewProjectRequestObject, filePathToSaveJson + "lastGeneratedJsonRequest.json");
    }


    public static JiraIssueObject getJiraIssueFromFile(String filePath) {
        return JsonDataProvider.getJiraObjectFromJson(filePath, JiraIssueObject.class);
    }
}
