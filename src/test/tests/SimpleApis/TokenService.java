package SimpleApis;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenService {
    public String getJWTToken(String username, String password){
//        String requestBody = String.format("{\"username\": \"%s\", \"password\" : \"%s\"}", username,password);
//        Response response = RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body(requestBody)
//                .post("/login");
//        return response.getHeader("Authorization");

        LoginRequest loginRequest2 = new LoginRequest(username, password);


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
        return tokenResponse1.getToken();
    }
}
