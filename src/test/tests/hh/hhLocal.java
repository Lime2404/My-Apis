package hh;

import core.BaseSelenideTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class hhLocal extends BaseSelenideTest {
    private final static String URL = "file:///C:/TEMP/Resume.html";
//    private final static String URL = "https://krisha.kz/a/show/687462531";

    @Test   // ожидаемые результаты
    public void checkAttributesHashMap(){
        HhResume hhResume = new HhResume(URL);
        Map<String, Object> expectedAttributes = new HashMap<>();
        expectedAttributes.put(HhResume.GENDER, "М");
        expectedAttributes.put(HhResume.AGE, 36);
        expectedAttributes.put(HhResume.CITY, "Томск");
        expectedAttributes.put(HhResume.CONFIRMED_PHONE, true);
        expectedAttributes.put(hhResume.READY_TO_RELOCATE, false);

        // для вызова актуальных данных из класса HH Resume нам придется создать мапу и в неё
        // засунуть всё что было в мапе актуальных резуоттатов из getAttributes()
        Map<String, Object> actualAttributes = hhResume.getAttributes();
// сравнение ожидаемых и актуальных результатов
        Assert.assertEquals(expectedAttributes, actualAttributes); }
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

    // Сравнение значений через классы без хэш мапов
        @Test
        public void checkAttributesClass(){
            HhResume hhResume = new HhResume(URL);
            Resume expectedAttributes = new Resume("М", 36, "Томск", true, false);
            Resume actualAttributes = new Resume(hhResume.getGender(), hhResume.getAge(),
                    hhResume.getCity(), hhResume.isPhoneConfirmed(),
                    hhResume.isReadyToRelocate());
            // корректное сравнение двух классов
        Assert.assertTrue(EqualsBuilder.reflectionEquals(expectedAttributes, actualAttributes));
            // альтернативное сравнение переменных через получение их в каждом из классов
            Assert.assertEquals(expectedAttributes.getAge(), actualAttributes.getAge());
            Assert.assertEquals(expectedAttributes.getCity(), actualAttributes.getCity());
            Assert.assertEquals(expectedAttributes.getGender(), actualAttributes.getGender());
            Assert.assertEquals(expectedAttributes.isPhoneConfirmed(), actualAttributes.isPhoneConfirmed());
            Assert.assertEquals(expectedAttributes.isReadyToRelocate(), actualAttributes.isReadyToRelocate());

    }

        @Test
        public void checkAttributesHashMap2(){
            HhResume hhResume2 = new HhResume(URL);
            boolean t = hhResume2.isReadyToRelocate();
    }

}
