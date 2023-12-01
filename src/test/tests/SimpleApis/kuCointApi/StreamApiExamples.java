package SimpleApis.kuCointApi;

import io.restassured.http.ContentType;
import jdk.dynalink.linker.LinkerServices;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class StreamApiExamples {
    public List<TickerData> getTickers = given()
//        given()
            .contentType(ContentType.JSON)
            .when()
            .get("https://api.kucoin.com/api/v1/market/allTickers")
            .then().log().body()
            // метод extract извлекает ответ сервера куда-то
            // в данном случае мы извлекаем все крипты в класс и создаем список объектов этого класса?
            .extract().jsonPath().getList("data.ticker", TickerData.class);
    // stream - это упрощенная функция for each где есть анонимная функция, которая позволяет нам выполнить
    // какое-то действие или же серию действий.

    @Test
    public void checkCrypto(){
        List<TickerData> usdTickers = getTickers.stream().filter()
    // здесь мы используем лямбда выражение.с переменной списка, к которой мы будем обращаться и вызывать метод

    }
}
