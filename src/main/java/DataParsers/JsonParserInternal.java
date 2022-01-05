package DataParsers;

import CustomExceptions.JSONNotFoundException;
import Utils.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.response.Response;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParserInternal {
    private static final Log log = new Log(new Object(){}.getClass().getEnclosingClass());

    public static String getJsonAsString(String filePath) throws JSONNotFoundException {
        try {
            log.info("Parsing file to String.");
            byte[] endoded = Files.readAllBytes(Paths.get(filePath));
            return new String(endoded, StandardCharsets.UTF_8).replaceAll("\r\n", "");
        } catch (IOException e) {
            throw new JSONNotFoundException(filePath);
        }
    }

    public static void saveResponseAsJson(Response response, String filePath) throws JSONNotFoundException {
        log.info("Saving JSON String in file");

        new File(filePath).mkdir();

        try(FileWriter writer = new FileWriter(new File(filePath), false)) {
            Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().setPrettyPrinting().create();
            JsonElement jsonElement = new JsonParser().parse(response.asString());

            writer.write(String.valueOf(gson.toJson(jsonElement)));
            log.info("File saved successfully in location: " + filePath);
        } catch (IOException e) {
            throw new JSONNotFoundException(filePath);
        }
    }
}
