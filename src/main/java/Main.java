import CustomExceptions.JSONNotFoundException;
import DataParsers.JsonParserInternal;
import APIConnection.APIConnection;
import DataParsers.RandomDataGenerator;
import Enums.IssuePriorityEnum;
import Enums.IssueTypesEnum;
import Enums.ProjectTypesEnum;
import JiraJsonObjects.JiraRequestHandler;
import JiraJsonObjects.NewProjectResponseObjects.JiraNewProjectResponseObject;
import JiraJsonObjects.ProjectInfoResponseObjects.JiraProjectInfoResponseObject;
import JiraJsonObjects.ProjectInfoResponseObjects.ModelObjects.ProjectInfoIssueTypeModel;
import JiraJsonObjects.SearchResponseObjects.JiraSearchResponseObject;
import Utils.Log;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class Main {
    private static final Log log = new Log(new Object(){}.getClass().getEnclosingClass());
    private static final String dataStoragePath = System.getProperty("user.dir") + "\\src\\main\\java\\DataStorage\\";
    private static final IssueTypesEnum typeOfIssue = IssueTypesEnum.TASK;

    private static String projectKeyForTest = "PIMK";

    public static void main(String[] args) {
        switch(args[0].toLowerCase()) {
            case "ci": // create issue
                projectKeyForTest = args[1];
                createNewIssue(args[2]);
                break;
            case "esi": // edit summary of issue
                projectKeyForTest = args[1];
                editSummaryOfIssue(args[2]);
                break;
            case "edi": // edit description of issue
                projectKeyForTest = args[1];
                editDescriptionOfIssue(args[2]);
                break;
            case "epi": // edit priority of issue
                projectKeyForTest = args[1];
                editPriorityOfIssue(args[2], getPriorityByName(args[3]));
                break;
            case "si": // search for issue
                projectKeyForTest = args[1];
                searchForIssue(args[2]);
                break;
            case "di": // delete issue
                projectKeyForTest = args[1];
                deleteIssue(args[2]);
                break;
            case "cp": // create project
                createNewProject(args[1], getProjectTypeByName(args[2]));
                break;
            case "ep": // edit project
                projectKeyForTest = args[1];
                editProjectDetails();
                break;
            case "dp": // delete project
                projectKeyForTest = args[1];
                editProjectDetails();
                break;
            case "t01": // test 01
                projectKeyForTest = args[1];
                test01IssueCreation();
                break;
            case "t02": // test 02
                projectKeyForTest = args[1];
                test02IssueEdit();
                break;
            case "t03": // test 03
                projectKeyForTest = args[1];
                test03ProjectCreation();
                break;
            case "t04": // test 04
                projectKeyForTest = args[1];
                test04ProjectEdit();
                break;
            case "t05": // test 05
                projectKeyForTest = args[1];
                test05Performance();
                break;
        }
    }

    //------------------ Functions associated to Jira Issues

    /**
     *
     * @param issueName, can has values of: null, "" or other string value of expected name for new project
     */
    public static void createNewIssue(String issueName) {
        //1. Prepare input data
        IssueTypesEnum typeOfIssue = IssueTypesEnum.TASK;
        String projectId = getProjectId(projectKeyForTest);
        String projectName = getProjectName(projectKeyForTest);
        String issueTypeId = getIssueTypeId(projectKeyForTest, typeOfIssue);
        //2. Prepare input JSON based on input data
        JiraRequestHandler.createJiraNewIssueJson(projectId, projectName, issueName, issueTypeId, typeOfIssue, dataStoragePath);
        //3. Send JSON with payload
        Response response = APIConnection.sendPostRequestForIssueCreation();
        //4. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void editSummaryOfIssue(String issueId) {
        //1. Prepare input JSON based on input data
        JiraRequestHandler.createJiraEditSummaryOfIssueJson(issueId, typeOfIssue, dataStoragePath);
        //2. Send JSON with payload
        Response response = APIConnection.sendPutRequestForSummaryEdit(issueId);
        //3. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void editDescriptionOfIssue(String issueId) {
        //1. Prepare JSON
        JiraRequestHandler.createJiraEditDescriptionOfIssueJson(issueId, dataStoragePath);
        //2. Send JSON with payload
        Response response = APIConnection.sendPutRequestForDescriptionEdit(issueId);
        //3. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void editPriorityOfIssue(String issueId, IssuePriorityEnum newPriority) {
        //1. Prepare JSON
        JiraRequestHandler.createJiraEditPriorityOfIssueJson(issueId, newPriority, dataStoragePath);
        //2. Send JSON with payload
        Response response = APIConnection.sendPutRequestForPriorityEdit(issueId);
        //3. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static String searchForIssue(String jql) {
        //1. Send JSON
        Response response = APIConnection.sendGetRequestForDescriptionEdit(jql);
        JiraSearchResponseObject jiraResponse = response.as(JiraSearchResponseObject.class);
        String foundIssues = jiraResponse.getFoundIssuesKeys();
        log.info("Issues found in project: " + foundIssues);

        //2. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");

        //3. Return found issues
        return foundIssues;
    }

    public static void deleteIssue(String issueId) {
        //1. Send JSON
        Response response = APIConnection.sendDeleteRequestToDeleteIssue(issueId);
        //2. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static IssuePriorityEnum getPriorityByName(String priorityName) {
        switch (priorityName.toLowerCase()) {
            case "lowest":
                return IssuePriorityEnum.LOWEST;
            case "low":
                return IssuePriorityEnum.LOW;
            case "medium":
                return IssuePriorityEnum.MEDIUM;
            case "high":
                return IssuePriorityEnum.HIGH;
            case "highest":
            default:
                return IssuePriorityEnum.HIGHEST;
        }
    }
    //-----------------------------------------------------


    //------------------ Functions associated to Jira Projects

    /**
     *
     * @param newProjectName, can has values of: null, "" or other string value of expected name for new project
     * @param newProjectType
     */
    public static void createNewProject(String newProjectName, ProjectTypesEnum newProjectType) {
        //1. Prepare input JSON based on input data
        JiraRequestHandler.createJiraCreateNewProjectJson(newProjectName, newProjectType, dataStoragePath);
        //2. Send JSON with payload
        Response response = APIConnection.sendPostRequestForProjectCreation();
        JiraNewProjectResponseObject jiraResponse = response.as(JiraNewProjectResponseObject.class);
        log.info(String.format("Project has been created, id: %s, key: %s.", jiraResponse.getProjectId(), jiraResponse.getProjectKey()));
        projectKeyForTest = jiraResponse.getProjectKey();
        //3. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void editProjectDetails() {
        //1. Prepare input data
        String projectId = getProjectId(projectKeyForTest);
        String projectName = getProjectName(projectKeyForTest);
        //2. Send JSON
        Response response = APIConnection.sendPostRequestForProjectEdit(projectKeyForTest, projectId, projectName);
        //3. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void deleteProject() {
        //1. Send JSON with payload
        Response responseWithId = APIConnection.sendGetRequestForProjectDetails(projectKeyForTest);
        JiraProjectInfoResponseObject projectInfoResponseObject = responseWithId.as(JiraProjectInfoResponseObject.class);
        String projectId = projectInfoResponseObject.getId();

        Response responseForDelete = APIConnection.sendDeleteRequestToDeleteProject(projectId);
        //2. Save response
        JsonParserInternal.saveResponseAsJson(responseForDelete, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static String getProjectId(String projectKey) {
        Response responseWithId = APIConnection.sendGetRequestForProjectDetails(projectKey);
        JiraProjectInfoResponseObject projectInfoResponseObject = responseWithId.as(JiraProjectInfoResponseObject.class);
        return projectInfoResponseObject.getId();
    }

    public static String getProjectName(String projectKey) {
        Response responseWithId = APIConnection.sendGetRequestForProjectDetails(projectKey);
        JiraProjectInfoResponseObject projectInfoResponseObject = responseWithId.as(JiraProjectInfoResponseObject.class);
        return projectInfoResponseObject.getName();
    }

    public static String getProjectDescription(String projectKey) {
        Response responseWithId = APIConnection.sendGetRequestForProjectDetails(projectKey);
        JiraProjectInfoResponseObject projectInfoResponseObject = responseWithId.as(JiraProjectInfoResponseObject.class);
        return projectInfoResponseObject.getDescription();
    }

    public static String getIssueTypeId(String projectKey, IssueTypesEnum issueTypeEnum) {
        Response responseWithId = APIConnection.sendGetRequestForProjectDetails(projectKey);
        JiraProjectInfoResponseObject projectInfoResponseObject = responseWithId.as(JiraProjectInfoResponseObject.class);

        for(ProjectInfoIssueTypeModel infoIssueType : projectInfoResponseObject.getIssueTypes()) {
            if(infoIssueType.getName().equals(issueTypeEnum.getPolishName())) {
                return infoIssueType.getId();
            }
        }
        return "Not found ID!";
    }

    public static ProjectTypesEnum getProjectTypeByName(String projectType) {
        switch (projectType.toUpperCase()) {
            case "BUG-TRACKING":
                return ProjectTypesEnum.BUGTRACKING;
            case "KANBAN-FIRM":
                return ProjectTypesEnum.KANBANFIRM;
            case "KANBAN-TEAM":
                return ProjectTypesEnum.KANBANTEAM;
            case "SCRUM-TEAM":
                return ProjectTypesEnum.SCRUMTEAM;
            case "SCRUM-FIRM":
            default:
                return ProjectTypesEnum.SCRUMFIRM;
        }
    }
    //-----------------------------------------------------

    //------------------ Tests
    @Test
    public static void test01IssueCreation() {
        String randomIssueName = RandomDataGenerator.getRandomIssueNumber();
        String jql = String.format("project = \"%s\" AND summary ~ \"%s\" order BY created DESC", projectKeyForTest, randomIssueName);
        createNewIssue("Test issue " + randomIssueName);
        String foundIssues = searchForIssue(jql);
        Assert.assertNotEquals(foundIssues, "[None issues found!]");
    }

    @SneakyThrows
    @Test
    public static void test02IssueEdit() {
        String randomIssueName = RandomDataGenerator.getRandomIssueNumber();
        String jqlCreation = String.format("project = \"%s\" AND summary ~ \"%s\" order BY created DESC", projectKeyForTest, randomIssueName);
        createNewIssue("Test issue " + randomIssueName);
        String newIssueId = searchForIssue(jqlCreation).replace("[", "").replace("]", "");

        editSummaryOfIssue(newIssueId);
        Thread.sleep(3000);
        editDescriptionOfIssue(newIssueId);
        Thread.sleep(3000);
        editPriorityOfIssue(newIssueId, IssuePriorityEnum.HIGHEST);
        Thread.sleep(3000);

        String jqlValidation = String.format("project = \"%s\" AND key = \"%s\" AND summary ~ \"(edited summary)\" AND description ~ \"New edited description of issue.\" AND priority = HIGHEST order BY created DESC",
                projectKeyForTest, newIssueId);
        String foundIssues = searchForIssue(jqlValidation);
        Assert.assertNotEquals(foundIssues, "[None issues found!]");
    }

    @SneakyThrows
    @Test
    public static void test03ProjectCreation() {
        createNewProject("", ProjectTypesEnum.KANBANTEAM);
        Thread.sleep(5000);
        String randomIssueName = RandomDataGenerator.getRandomIssueNumber();
        String jql = String.format("project = \"%s\" AND summary ~ \"%s\" order BY created DESC", projectKeyForTest, randomIssueName);
        createNewIssue("Test issue " + randomIssueName);
        String foundIssues = searchForIssue(jql);
        Assert.assertNotEquals(foundIssues, "[None issues found!]");
    }

    @SneakyThrows
    @Test
    public static void test04ProjectEdit() {
        createNewProject("", ProjectTypesEnum.KANBANTEAM);
        Thread.sleep(5000);
        editProjectDetails();
        Assert.assertTrue(getProjectName(projectKeyForTest).contains("Edited"));;
        Assert.assertTrue(getProjectDescription(projectKeyForTest).contains("Edited"));;
    }

    @Test
    public static void test05Performance() {
        long startTime = new Date().getTime();
        createNewIssue("Test issue " + RandomDataGenerator.getRandomIssueNumber());
        long endTime = new Date().getTime();
        Assert.assertTrue(endTime - startTime < 15000);
    }
    //-----------------------------------------------------
}
