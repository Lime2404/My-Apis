package Reqress;

import SimpleApis.Specifications;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.xml.parsers.SAXParser;
import java.awt.geom.RectangularShape;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReqresNoPogoTest {
    private final static String URi = "https://reqres.in/";

    // получаем пользователей
    // убеждаемся, что аватарки содержат id
    @Test
    public void checkAvatarsPogoTest() {
        Specifications.installSpecification(Specifications.requestSpec(URi), Specifications.responseOK200());
        // ниже будем использовать интерфейс Response
// GET
        Response response = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                //ниже будут идти матчеры, надо прописать equalTo и импортровать org.hamcrest.Matchers.equalTo
                .body("page", equalTo(2))
                .body("data.id", notNullValue()) // это хорошая проверка что поля не пустые
                .body("data.email", notNullValue())
                .body("data.first_name", notNullValue())
                .body("data.last_name", notNullValue())
                .body("data.avatar", notNullValue())
                .extract().response();
        // далее мы можем работать с этим интерфейсом response, содержащим ответ от сервера как хотим
        JsonPath jsonPath = response.jsonPath();
//        int i = 0;
        //получаем список со всеми имейлами, указываем путь по которому нам надо пойти и забрать всё что там есть
        // засовывя все данные теперь в этот списки типа email
        List<String> emails = jsonPath.get("data.email");
        List<Integer> ids = jsonPath.get("data.id");
        List<String> avatars = jsonPath.get("data.avatar");
//        int i = 0;
        for (int i = 0; i < avatars.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));
//            Assert.assertEquals(avatars.get(i), ids.get(i)); // данная проверка не подойдет, там строка
        }
        // каждый элемент должен оканчивтаься на "@regres.in"
        Assert.assertTrue(emails.stream().allMatch(x -> x.endsWith("@reqres.in")));
        // лучше вызывать allMatch так как тогда если какой-то элесент не пройдет проверку то провалится вс пверорка
// POST

    }

    @Test
    public void successUserRegTestNoPojo() {
        Specifications.installSpecification(Specifications.requestSpec(URi), Specifications.responseOK200());
        // в pojo классах мы передавали объект класса с нужными полями, теперь можем передавать мапу
        Map<String, String> user = new HashMap<>();
        user.put("email", "eve.holt@reqres.in");
        user.put("password", "pistol");
        given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
        // прверка проходит на уровне ответа в rest assured
    }

    ///всё то же самое можно сделать через response
    @Test
    public void successUserRegTestNoPojo2() {
        Specifications.installSpecification(Specifications.requestSpec(URi), Specifications.responseOK200());
        // в pojo классах мы передавали объект класса с нужными полями, теперь можем передавать мапу
        Map<String, String> user = new HashMap<>();
        user.put("email", "eve.holt@reqres.in");
        user.put("password", "pistol");
        Response response = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
//                .body("id", equalTo(4))
//                .body("token", equalTo("QpwL5tke4Pnpja7X4"))
//        отличием будет что теперь всё что мы получили от сервера надо извлечь в response
                .extract().response(); // опять же далем что захотим с этим
        JsonPath jsonPath = response.jsonPath();
        // начинаем доставть переменные из jsona
        int id = jsonPath.get("id");  // поле должно быть таким же как в ответе
        String token = jsonPath.get("token");
        // в assrtaх всегда сначачал пишется ожидаемый резуоттат
        Assert.assertEquals(4, id);
        Assert.assertEquals("QpwL5tke4Pnpja7X4", token); //
    }
    @Test
    public void unseccessRegTestNoPojo(){
        Specifications.installSpecification(Specifications.requestSpec(URi), Specifications.responseSpecError400());
        Map<String, String> user = new HashMap<>();
        user.put("email", "sydney@fife");
        user.put("password", "");
        Response response = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("error");
        Assert.assertEquals("Missing password", message);
//        System.out.println(message);
    }
}
