import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Main {
    public static void main(String[] args) {
        RequestSpecification request = given()
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON);

        String response = request.get("URL").asString();


    }
}
