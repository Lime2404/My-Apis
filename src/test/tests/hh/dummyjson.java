package hh;

import core.BaseSelenideTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class dummyjson extends BaseSelenideTest {
    private final static String URL = "https://dummyjson.com/auth/login";
@Test
    public void checkAttributesHashMap(){
    JSONObject requestBody1 = new JSONObject();
    requestBody1.put("username", "kminchelle");    // без аннотации test put не сработает
    requestBody1.put("password", "0lelplR");
        HhResumePage hhResumePage = new HhResumePage(URL);
//emil.issabekov@gmail.com

    Response response = given() //RestAssured.
            .contentType(ContentType.JSON)
            .body(requestBody1.toString())
            .when()
            .post(URL)
            .then().log().all()
            .extract()
            .response();
            response.prettyPrint();
}

}
