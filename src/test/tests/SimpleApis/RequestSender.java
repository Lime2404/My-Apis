package SimpleApis;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RequestSender {
    @Test
    public void trainTest() {
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("username", "admin");    // без аннотации test put не сработает
//        requestBody.put("password", "admin");
        LoginRequest loginRequest = new LoginRequest("admin", "admin");


        // объяснение на 13 минуте  https://youtu.be/rrMQWZhfAVA
        Response response = given() //RestAssured.
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .when()
                .post("http://85.192.34.140:8080/api/login")
                .then().log().all()
                .extract()
                .response();
        TokenResponse tokenResponse = response.as(TokenResponse.class);
        String token = tokenResponse.getToken();
        System.out.println("Token: " + token);
    }
//        String responseBodyAsString = response.body().asString();
//        JSONParser parser = new JSONParser();
//        JSONObject responseBody = null;
//        try {
//            responseBody = (JSONObject) parser.parse(responseBodyAsString);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String token = (String) responseBody.get("token");
//        System.out.println("Token: " + token);
//    }
        @Test
                public void jwtTest(){
            LoginRequest loginRequest2 = new LoginRequest("admin", "admin");


            // объяснение на 13 минуте  https://youtu.be/rrMQWZhfAVA
            Response response2 = given() //RestAssured.
                    .contentType(ContentType.JSON)
                    .body(loginRequest2)
                    .when()
                    .post("http://85.192.34.140:8080/api/login")
                    .then().log().all()
                    .extract()
                    .response();
            TokenResponse tokenResponse1 = response2.as(TokenResponse.class);
            String token2 = tokenResponse1.getToken();

//            String jwtToken = "your_jwt_token";

            Response response3 = given() //RestAssured.
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + token2)
                    .when()
                    .get("http://85.192.34.140:8080/api/user")
                    .then().log().all()
                    .extract()
                    .response();
            System.out.println("Response: " + response3.body().asString());
        }

    @Test
            public void get() {
        given().get("http://85.192.34.140:8080/api/user")
                .then()
                .log().all();
    }
}