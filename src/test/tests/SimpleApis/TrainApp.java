package SimpleApis;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;

// не забудь преобразовать JSON в модельные классы (POJO класс) для отправки тела и извлечения ответа

public class TrainApp {

    @Test
        public void trainTest() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", "admin");    // без аннотации test put не сработает
        requestBody.put("password", "admin");


        // Отправляем POST-запрос с JSON-телом
        Response response = given() //RestAssured.
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post("http://85.192.34.140:8080/api/login")
                .then()
                .extract()
                .response();

        String responseBodyAsString = response.body().asString();
        JSONParser parser = new JSONParser();
        JSONObject responseBody = null;
        try {
            responseBody = (JSONObject) parser.parse(responseBodyAsString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String token = (String) responseBody.get("token");
        System.out.println("Token: " + token);

//    private static final String URI1 = "http://85.192.34.140:8080/api/user";
//    @Test
//    public void get(){
//        given().get(URI1)
//                .then()
//                .log().all();

        Response response3 = given() //RestAssured.
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("http://85.192.34.140:8080/api/user")
                .then().log().all()
                .extract()
                .response();
        System.out.println("Response: " + response3.body().asString());
    }

}
