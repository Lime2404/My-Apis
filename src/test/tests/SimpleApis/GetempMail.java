package SimpleApis;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

// Get more info from https://www.disify.com/
public class GetempMail {

    String url = "https://www.disify.com/api/email/emil@mail.com";

    @Test
        public void testHello() {
//        RestAssured.enableLoggingOfRequestAndResponseDe(LogDetail.ALL).;RestAssured.
        Response response = RestAssured
                .given().log().all() // here we log all request data in console
                .get(url)
                .andReturn();
        response.prettyPrint();

    }


}
