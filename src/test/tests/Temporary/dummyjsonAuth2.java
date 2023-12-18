package Temporary;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class dummyjsonAuth2 {

    private final static String URL3 = "https://dummyjson.com/auth/login";

//    JSONObject requestBody2 = new JSONObject();
//    requestBody2.put("username", "kminchelle");    // без аннотации test put не сработает
//    requestBody2.put("password", "0lelplR");
@Test
public void Avtorization() {
//    Map<String, String> auth = new HashMap<>();
//    auth.put("username", "kminchelle");
//    auth.put("password", "0lelplR");
    Creds auth = new Creds("kminchelle", "0lelplR");

    Response response1 = given() //RestAssured.
            .contentType(ContentType.JSON)
            .body(auth)
            .when()
            .post(URL3)
            .then().log().all()
            .extract()
            .response();
}
public static class Creds{
    private String username;
    private String password;

    public Creds(String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

}

}
