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
    private String expectedEmail = "lindsay.ferguson@reqres.in1";
    List<String> emails;

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
// все объекты можно положит в один список
        List<PojoUsersList> usersList = given()
                .contentType(ContentType.JSON)
                .when().get(url +"/users?page=2")
                .then().log().all()
//            .extract().body().jsonPath().getList("data", PojoUsersList.class);
                .extract().jsonPath().getList("data", PojoUsersList.class);
      String actualEmail = usersList.get(1).getEmail();
//            for(int i = 0; i < usersList.size(); i++){
//                System.out.println(usersList.get(i).getEmail());

//    Assert.assertTrue(expectedEmail.equals(actualEmail));
    Assert.assertEquals(expectedEmail, actualEmail);

        System.out.println(emails);
    }
}
