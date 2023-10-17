package SimpleApis;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

// не забудь преобразовать JSON в модельные классы (POJO класс) для отправки тела и извлечения ответа

public class GPTTest {
//    RestAssured.baseURI = "http://85.192.34.140:8080/api/user";
    private static TokenService tokenService;
    @BeforeAll // нужен чтобы проинициализировать статичный класс
    public static void setup(){
        RestAssured.baseURI = "http://85.192.34.140";
        RestAssured.port = 8080;

        tokenService = new TokenService();
    }

    @Test
        public void trainTest() {
       String username = "demo";
       String password = "demo";

       String JWTToken = tokenService.getJWTToken(username, password);

        RestAssured.given() //RestAssured.
                .auth().oauth2(JWTToken)
                .get("/api/user")
                .then().log().all()
                .statusCode(200);
    }

}
