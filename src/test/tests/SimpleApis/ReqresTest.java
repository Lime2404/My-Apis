package SimpleApis;

import RegistrationAssertion.Register;
import RegistrationAssertion.SuccessReg;
import RegistrationAssertion.UnSucceessReg;
import com.codeborne.selenide.As;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;

import org.apache.cassandra.streaming.StreamOut;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

public class ReqresTest {
    private final static String URL = "https://reqres.in/";

    @Test

    public void checkAvatarAndIdTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseOK200());
        List<UserData> users = given()
                .when()
//                .contentType(ContentType.JSON) // outdated due to established specification
//                .get(URL + "api/users?page=2") // outdated due to established specification
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
//        int j = 0;
        //в класс UserData мы извлекаем этот список пользователей из массива data в ответе от сервера
        // перебираем список и выбираем методы по очередности и сохраняем объекты в list
        // ниже х - это счетчик экземпляров класса ( по сути x равен экземпляру класса)
        // тут мы проходим по каждому пользователю и сравниванием аватар
//        users.forEach(x -> assertTrue(x.getAvatar().contains(x.getId().toString())));
        // проверили что id содержится в avatar;

        // нерабочий ассерт
//        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqress.in")));

        // метод collect позволяет всё собрать и поместить в список.
        // Запись UserData::getAvatar говорит о том что мы получаем поле Avatar класса UserDada
        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
//        List<String> avatars = users.stream().map(x->x.getAvatar()).collect(Collectors.toList());
//        int a = 0;
        // вызов лямбды, для неё получим id и эту id превратив в строку - лямбда выражения
        List<String> ids = users.stream().map(x->x.getId().toString()).collect(Collectors.toList());
        int a = 0;
        // сравнение двух списков
        // можно устраивать проверку даже в цикле проверяя наличие одних сущностей в других
        for(int i = 0; i < avatars.size(); i++){
//            System.out.println(avatars.get(i));
//            System.out.println(ids.get(i));
          Assert.assertTrue(avatars.get(i).contains(ids.get(i)));

        }
    }
    // проверить что возвращаемые токен и id соответствуют требованиям
    @Test
    public void successRegTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseOK200());
       //ниже будут поля с одидаемыми данными согласно https://reqres.in/
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register user = new Register("eve.holt@reqres.in", "pistol");
        // передавая логин и пароль мы ожидаем получить в ответ токен и id
        // создаем экземпляр класса, для него вызываем метод given и далее извлекаем ответ в виде этого класса
        SuccessReg successReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all() // дальше все данные которые получаем в ответ надо предать в класс
                .extract().as(SuccessReg.class);
        int a = 0;
        // проверяем предварительно, что пришел не пустой результат
        Assert.assertNotNull(successReg.getId());
        Assert.assertNotNull(successReg.getToken());
        Assert.assertEquals(id, successReg.getId());
        Assert.assertEquals(token, successReg.getToken());

    }
    // проыерить соответствует ли требованию возвращаемое сообещение при попытке отправить не полные креды
    @Test
    public void unSuccessRegTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecError400());
        //ниже будут поля с одидаемыми данными согласно https://reqres.in/
        String error = "Missing password";
        Register user = new Register("sydney@fife", "");
        UnSucceessReg unSucceessReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(UnSucceessReg.class);
        Assert.assertEquals(error, unSucceessReg.getError());

    }
    // Проверит отсортированы ли элменты в массиве данных на сервисе
    @Test
    public void sortedYearsTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseOK200());
        // Создаем список в который мы будем записывать всё полученные элементы с помощью библотеки restassured
        // данные в виде объектов
        List<ColorsData> colors  = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);
        // ниже будет использоваться streamapi для того чтобы достать года, которые нам нужны из созданного выше списка
        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
//        int a = 0;
        // конструкция ColorsData::getYear достает поле год  из каждого объекта
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(sortedYears, years);
        System.out.println(years);
        System.out.println(sortedYears);
    }

    @Test
    public void removeUser(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseUnique(204));
                given()
                .when()
                .delete("api/users/2")
                .then().log().all();
                // Ассерты пока не нужны так как мы только ждем нужный статус
//                Assert.assertEquals(Specifications.responseUnique(204), 204);
    }

    @Test
    public void timeTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseOK200());
        UserTime user = new UserTime("xmorpheus", "zion resident");
        UserTimeResponse response = given()
                .body(user)
                .when()
                .put("/api/users/2")
                .then().log().all()
                .extract().as(UserTimeResponse.class);
  // Ниже получаем время компьютера и преобразуем ответ с сервера отразая ненужную часть регулярными выражениями
       String regex = "(.{5})$";
       String regex1 = "(.{11})$";
        // в этом выражении мы меняем regex на ничего-  replaceAll(regex,"")
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex1,"");
        System.out.println(currentTime);
        Assert.assertEquals(currentTime, response.getUpdatedAt().toString().replaceAll(regex, ""));

    }
}
