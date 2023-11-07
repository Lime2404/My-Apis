package SimpleApis;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class TemporaryTests {
    private final static String URi = "https://reqres.in/";

    @Test
    public void AvatarIdCheck() {
        Specifications.installSpecification(Specifications.requestSpec(URi), Specifications.responseUnique(200));

        List<TemporaryPogo> people = given() // важно создавать список того же типа что и данные
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", TemporaryPogo.class);
//        int i  = 0;
        List<String> avatars = people.stream().map(TemporaryPogo::getAvatar).collect(Collectors.toList());
        List<Integer> ids = people.stream().map(x -> x.getId()).collect(Collectors.toList());
//        System.out.println(avatars);
//        System.out.println(ids);
        for(int i = 0; i < people.size(); i++){
            Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));
        }
    }

}