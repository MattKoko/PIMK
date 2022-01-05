package DataParsers;

import Utils.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class JsonDataProvider {
    private static final Log log = new Log(new Object(){}.getClass().getEnclosingClass());

    public static <T> T getJiraObjectFromJson(String filePath, Class<T> classOfT) {
        Gson gson = new Gson();
        Reader reader;
        T issueObject = (T) new Object();

        try {
            reader = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
            issueObject = gson.fromJson(reader, classOfT);
        } catch (IOException e) {
            System.out.println("Exception IOException");
        }
        return issueObject;
    }

    public static void saveIssueObjectAsJsonFile(Object issueObject, String filePathOutput) {
        try {
            FileWriter writer = new FileWriter(filePathOutput, false);
            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(issueObject));
            writer.close();
            log.info("New JSON file has been saved in location: " + filePathOutput);
        } catch (IOException e) {
            System.out.println("Exception IOException");
        }
    }
}
