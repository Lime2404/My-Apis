package hh;

import core.BaseSelenideTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class hhLocal extends BaseSelenideTest {
    private final static String URL = "file:///C:/TEMP/Resume.html";
//    private final static String URL = "https://krisha.kz/a/show/687462531";

    @Test
    public void checkAttributesHashMap(){
        HhResume hhResume = new HhResume(URL);
        Map<String, Object> expectedAttributes = new HashMap<>();
        expectedAttributes.put(HhResume.GENDER, "М");

        Map<String, Object> actualAttributes = hhResume.getAttributes();

        Assert.assertEquals(expectedAttributes, actualAttributes);
//        HhResumePage hhResumePage = new HhResumePage(URL);
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
