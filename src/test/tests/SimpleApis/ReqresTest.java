package SimpleApis;

import io.restassured.http.ContentType;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ReqresTest {
    private final static String URL = "https://reqres.in/";

    @Test

    public void checkAvatarAndIdTest(){
        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL+"api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        // перебираем список и выбираем методы по очередности
        users.forEach(x-> assertTrue(x.getAvatar().contains(x.getId().toString())));
    }

}
