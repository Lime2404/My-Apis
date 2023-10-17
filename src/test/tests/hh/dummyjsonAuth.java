package hh;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.mortbay.util.ajax.JSON;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class dummyjsonAuth{

    private final static String URL3 = "https://dummyjson.com/auth/login";

//    JSONObject requestBody2 = new JSONObject();
//    requestBody2.put("username", "kminchelle");    // без аннотации test put не сработает
//    requestBody2.put("password", "0lelplR");
@Test
public void Avtorization() {
    Map<String, String> auth = new HashMap<>();
    auth.put("username", "kminchelle");
    auth.put("password", "0lelplR");

    Response response1 = given() //RestAssured.
            .contentType(ContentType.JSON)
            .body(auth)
            .when()
            .post(URL3)
            .then().log().all()
            .extract()
            .response();
}

}
