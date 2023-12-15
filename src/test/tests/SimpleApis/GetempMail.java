package SimpleApis;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

// Get more info from https://www.disify.com/
public class GetempMail {

    String url = "https://www.disify.com/api/email/emil@mail.com";

    @Test
        public void testHello() {
//        RestAssured.enableLoggingOfRequestAndResponseDe(LogDetail.ALL).;RestAssured.
        Response response = given().log().all() // here we log all request data in console
                .get(url)
                .andReturn();
        response.prettyPrint();
//        int a = 0;

    }

    @Test
    public void testEmail(){
        String s = given()
             .get(url)
                .thenReturn().body().prettyPrint();
//        System.out.println(s);

    }


}
