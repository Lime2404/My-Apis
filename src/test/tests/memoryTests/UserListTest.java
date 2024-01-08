package memoryTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserListTest {

    private String url = "https://reqres.in/api";

    @Test
    public void getStatusCode(){
                 given()
                .baseUri(url)
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get("2")
                .then().statusCode(200);
//                .andReturn();
//                response.prettyPrint();
//        List<String> collected = given()
    }

    @Test
            public void getUserList() {
        PojoUsersList usersList = given()
                .contentType(ContentType.JSON)
                .when().get(url)
                .then().log().all()
//            .extract().body().jsonPath().getList("data", PojoUsersList.class);
                .extract().as(PojoUsersList.class);
        System.out.println();

    }


}
