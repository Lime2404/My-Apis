package hh;

import core.BaseSelenideTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class hhLocal extends BaseSelenideTest {
    private final static String URL = "file:///C:/TEMP/Resume";
//    private final static String URL = "https://krisha.kz/a/show/687462531";

    @Test
    public void checkAttributesHashMap(){

        HhResumePage hhResumePage = new HhResumePage(URL);
//emil.issabekov@gmail.com

//        Response response = given() //RestAssured.
//                .contentType(ContentType.JSON)
//                .body(requestBody1.toString())
//                .when()
//                .post(URL)
//                .then().log().all()
//                .extract()
//                .response();
//        response.prettyPrint();
    }

}
