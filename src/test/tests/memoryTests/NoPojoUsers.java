package memoryTests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;

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
        Access access = new Access("morpheus", "leader");
        Response response = given()
                .body(access)
                .when()
                .post(url+"/users")
                .then().extract().response();
        JsonPath jsonPath = response.jsonPath();
        String id = jsonPath.get("id");
//        String id = response.jsonPath().get("id");
        System.out.println(id);
    }

    @Test
    public void updateApi(){
        Access access = new Access("morpheus", "zion resident");
        Response response = given()
                .body(access)
                .when().contentType(ContentType.JSON)
                .patch(url+"/users/2")
                .then().extract().response();

//        JsonPath jsonPath = response.jsonPath();
//        String job = jsonPath.get("job");
        String job = response.jsonPath().get("job");
//        int a = 0;
        System.out.println(job);
    }

    @Test
    public void deleteUser(){
        given()
                .when().delete(url+"/users/2")
                .then().statusCode(204);
        System.out.println("узер убит");
    }

    @Test
    public void registerUser(){
        Creds creds = new Creds("eve.holt@reqres.in", "pistol");
        String token = "QpwL5tke4Pnpja7X4";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(creds)
                .when().post(url+"/register")
                .then().log().all()
                .extract().response();
        String expectedToken = response.jsonPath().get("token");
        System.out.println(expectedToken);
        Assert.assertEquals(token, expectedToken);
    }
// ниже пример когда не нужно создавать объект для передачи данных в rest assured
    @Test
        public void missingCreds() {
        String expected_message = "Missing password";
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", "peter@klaven");
        credentials.put("password", "");
        Response response = given()
                .body(credentials)
                .when().post(url+"/login")
                .then().log().all()
                .extract().response();
//        int a =0;
        String error = response.jsonPath().get("error");
        System.out.println(error);
        Assert.assertEquals(expected_message, error);
    }
}
