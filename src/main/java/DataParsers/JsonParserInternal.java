package DataParsers;

import CustomExceptions.JSONNotFoundException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParserInternal {
    public static String getJsonAsString(String filePath) throws JSONNotFoundException {
        try {
            byte[] endoded = Files.readAllBytes(Paths.get(filePath));
            return new String(endoded, StandardCharsets.UTF_8).replaceAll("\r\n", "");
        } catch (IOException e) {
            throw new JSONNotFoundException(filePath);
        }
    }

    public static void saveStringAsJson(String jsonContentString, String filePath) throws JSONNotFoundException {
        new File(filePath).mkdir();

        try(FileWriter writer = new FileWriter(new File(filePath), false)) {
            Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().setPrettyPrinting().create();
            JsonElement jsonElement = new JsonParser().parse(jsonContentString);

            writer.write(String.valueOf(gson.toJson(jsonElement)));
        } catch (IOException e) {
            throw new JSONNotFoundException(filePath);
        }
    }
}
