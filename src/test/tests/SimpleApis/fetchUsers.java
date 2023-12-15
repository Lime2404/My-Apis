package SimpleApis;
import static io.restassured.RestAssured.filters;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo; // очень важно подтянуть
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
// https://reqres.in/api-docs/#/

public class fetchUsers {
    @Test
    public void getUser() {
      Response response1 = //RestAssured
                given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get("1")
//                .when().get("total_pages")
                .andReturn();
        response1.prettyPrint();
    }
    @Test
    public void getUsers(){
    given().log().all()
            .baseUri("https://reqres.in/api")
            .basePath("/users")
            .contentType(ContentType.JSON)
            .when().get("1")
            .then().statusCode(200);
//            .body("data[1].email", equalTo("george.bluth@reqres.in"));
//            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("C:\\Users\\EmilIsabekov\\OneDrive - Solveva\\Desktop\\First-APIs\\src\\test\\Resources\\user-schema.json"));
//            .body("data.find{it.email=='george.bluth@reqres.in'}.first_name", equalTo("George"));
//        System.out.println("все проперки прошли, йоу");;

        }
    @Test
    public void getUsersEmail(){
        given().log().all()
//                .baseUri("https://www.disify.com/api/email/emil@mail.com")
//                .basePath("/users")
//                .contentType(ContentType.JSON)
                .when().get("https://www.disify.com/api/email/emil@mail.com")
                .then().body("format", equalTo(false));
//                .prettyPrint();
//                .thenReturn().body().equals();
//
//                .getBody().prettyPrint();
    }
//        given().log().all()
@Test
        public void getUsersId() {
        given().log().all()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get("1")
//                .then().body("page", )
//                .then().statusCode(200);
//                .then().body("data", equalTo());
//                .prettyPrint();
//                .thenReturn().body().equals();
                  .then().body("data", equalTo(null));
//                    int a = 0;
//
//                .getBody().prettyPrint();
    }

}
