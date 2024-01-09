package memoryTests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
// идем вниз по https://reqres.in/
public class NoPojoUsers {
    private String url = "https://reqres.in/api";
    private Integer expectedTotal = 12;

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
// альтернатиный и более знакомый вариант
        List<String> test = response.jsonPath().getList("data.email");
        System.out.println(test);

    }
    @Test
    public void checkTotal(){
        Response response = given()
                .when().get(url + "/users?page=2")
                .then().extract().response();
        Integer total = response.jsonPath().get("total");  // забираем из ответа поле
        Integer totalPages = response.jsonPath().get("total_pages");
        List<Integer> ids = response.jsonPath().get("data.id"); // забираем из ответа массив полей
        System.out.println(total);
        System.out.println(totalPages);
        System.out.println(ids + " " + ids.get(0));

        Assert.assertEquals(expectedTotal, total);
    }

    @Test
    public void notFound(){
        given().when().get(url+"/users/23")
                .then().statusCode(404);
        // cамая простая проверка статус кода
    }

    @Test
    public void createApi(){
        Creds creds = new Creds("morpheus", "leader");
        Response response = given()
                .body(creds)
                .when()
                .post(url+"/users")
                .then().extract().response();
//        JsonPath jsonPath = response.jsonPath();
//        String id = jsonPath.get("id");
        String id = response.jsonPath().get("id");
        System.out.println(id);
    }
}
