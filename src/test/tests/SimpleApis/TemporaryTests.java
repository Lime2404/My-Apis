package SimpleApis;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;

public class TemporaryTests {
    private final static String URi = "https://reqres.in/";

    @Test
    public void AvatarIdCheck() {
        Specifications.installSpecification(Specifications.requestSpec(URi), Specifications.responseUnique(200));

        List<TemporaryPogoPeople> people = given() // важно создавать список того же типа что и данные
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", TemporaryPogoPeople.class);
//        int i  = 0;
        List<String> avatars = people.stream().map(TemporaryPogoPeople::getAvatar).collect(Collectors.toList());
        List<Integer> ids = people.stream().map(x -> x.getId()).collect(Collectors.toList());
//        System.out.println(avatars);
//        System.out.println(ids);
        for (int i = 0; i < people.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));
        }
    }

    @Test



    public void checkRegSuccessStat() {
        String token = "QpwL5tke4Pnpja7X4";
        int id = 4;
        Specifications.installSpecification(Specifications.requestSpec(URi), Specifications.responseOK200());
        TemporaryPogoRegistration temporaryPogoRegistration = new TemporaryPogoRegistration("eve.holt@reqres.in", "pistol");
       TemporaryPogoSRegistration temporaryPogoSRegistration =  given()
                .when()
                .body(temporaryPogoRegistration)
                .post("api/register")
                .then().log().all()
                .extract().as(TemporaryPogoSRegistration.class);

       Assert.assertTrue(temporaryPogoSRegistration.getToken().equals(token));
       Assert.assertTrue(temporaryPogoSRegistration.getId().equals(id));
//       Assert.assertEquals(java.util.Optional.of(id), temporaryPogoSRegistration.getId());
//        System.out.println(temporaryPogoSRegistration.getId() + " " + temporaryPogoSRegistration.getToken());
    }
}
