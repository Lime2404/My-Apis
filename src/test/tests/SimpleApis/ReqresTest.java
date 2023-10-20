package SimpleApis;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.authentication;
import static org.junit.Assert.assertTrue;

import org.apache.cassandra.streaming.StreamOut;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresTest {
    private final static String URL = "https://reqres.in/";

    @Test

    public void checkAvatarAndIdTest() {
        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        //в класс UserData мы извлекаем этот список пользователей из массива data
        // перебираем список и выбираем методы по очередности
        // ниже х - это счетчик экземпляров класса
        // тут мы проходим по каждому пользователю и сравниванием аватар
//        users.forEach(x -> assertTrue(x.getAvatar().contains(x.getId().toString())));
        // проверили что id содержится в avatar;

        // нерабочий ассерт
//        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqress.in")));

        // метод collect позволяет всё собрать и поместить в список
        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        // вызов лямбды, для неё получим id и эту id превратив в строку
        List<String> ids = users.stream().map(x->x.getId().toString()).collect(Collectors.toList());
        // сравнение двух списков
        // можно устраивать проверку даже в цикле проверяя наличие одних сущностей в других
        for(int i = 0; i < avatars.size(); i++){
//            System.out.println(avatars.get(i));
//            System.out.println(ids.get(i));
          Assert.assertTrue(avatars.get(i).contains(ids.get(i)));

        }
    }

}
