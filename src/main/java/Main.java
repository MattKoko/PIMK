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

public class Main {
    private static final Log log = new Log(new Object(){}.getClass().getEnclosingClass());
    private static final String dataStoragePath = System.getProperty("user.dir") + "\\src\\main\\java\\DataStorage\\";
    private static final IssueTypesEnum typeOfIssue = IssueTypesEnum.TASK;

    public static void main(String[] args) throws JSONNotFoundException {
        editDescriptionOfIssue();
    }

    //------------------ Functions associated to Jira Issues
    public static void createNewIssue() throws JSONNotFoundException {
        IssueTypesEnum typeOfIssue = IssueTypesEnum.TASK;
        String projectId = getProjectId("TP03");
        String projectName = getProjectName("TP03");
        String issueTypeId = getIssueTypeId("TP03", typeOfIssue);

        //1. Prepare JSON
        JiraRequestHandler.createJiraNewIssueJson(projectId, projectName, issueTypeId, typeOfIssue, dataStoragePath);
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

    public static void deleteIssue() throws JSONNotFoundException {
        String issueId = "PIMK-37";

        //1. Send JSON
        Response response = APIConnection.sendDeleteRequestToDeleteIssue(issueId);
        //2. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }
    //-----------------------------------------------------


    //------------------ Functions associated to Jira Projects
    public static void createNewProject() throws JSONNotFoundException {
        ProjectTypesEnum newProjectType = ProjectTypesEnum.SCRUMTEAM;
        String newProjectName = "API Project - " + RandomDataGenerator.getRandomProjectNumber() + " - " + newProjectType.getProjectTypeName();

        //1. Prepare JSON
        JiraRequestHandler.createJiraCreateNewProjectJson(newProjectName, "TP05", newProjectType, dataStoragePath);
        //2. Send JSON with payload
        Response response = APIConnection.sendPostRequestForProjectCreation();
        JiraNewProjectResponseObject jiraResponse = response.as(JiraNewProjectResponseObject.class);
        log.info(String.format("Project has been created, id: %s, key: %s.", jiraResponse.getProjectId(), jiraResponse.getProjectKey()));
        //2. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void editProjectDetails() throws JSONNotFoundException {
        String projectKey = "TP03";
        String projectId = getProjectId(projectKey);
        String projectName = getProjectName(projectKey);

        //1. Send JSON
        Response response = APIConnection.sendPostRequestForProjectEdit(projectKey, projectId, projectName);
        //2. Save response
        JsonParserInternal.saveResponseAsJson(response, dataStoragePath + "Output\\JiraAPIResponse01.json");
    }

    public static void deleteProject() throws JSONNotFoundException {
        //2. Send JSON with payload
        Response responseWithId = APIConnection.sendGetRequestForProjectDetails("TP04");
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
    //-----------------------------------------------------
}
