package versions;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

// Get more info from https://rapidapi.com/Privatix/api/temp-mail/
public class GetempMail {

    String url = "https://www.disify.com/";

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
