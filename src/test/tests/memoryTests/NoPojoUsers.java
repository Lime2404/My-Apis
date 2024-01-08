package memoryTests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class NoPojoUsers {
    private String url = "https://reqres.in/api";

    @Test
    public void userList(){
        Response response = given()
                .when().get(url + "/users?page=2")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath(); // как будто тут мы создаем объект типа JsonPath
        // хранящий кучу данных по data
        List<String> emails = jsonPath.get("data.email");
        System.out.println(emails);

    }
}