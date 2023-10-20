package SimpleApis;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;

public class Specifications {
   public static RequestSpecification requestSpec(String url){
       // определяем по какой сылке обращаться и какой тип данных ожидать
       return new RequestSpecBuilder()
               .setBaseUri(url) // базовая ссылка
               .setContentType(ContentType.JSON)
               .build();
   }
   public static ResponseSpecification responseOK200(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
   }

   public static void installSpecification(RequestSpecification request, ResponseSpecification response){
       RestAssured.requestSpecification = request;
       RestAssured.responseSpecification = response;
   }
}
