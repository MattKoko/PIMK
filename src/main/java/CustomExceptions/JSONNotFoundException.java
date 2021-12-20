package CustomExceptions;

public class JSONNotFoundException extends Exception {
    public JSONNotFoundException(String filePath) {
        super(String.format("JSON NOT FOUND IN EXPECTED LOCATION: %s", filePath));
    }
}
