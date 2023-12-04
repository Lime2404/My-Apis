package SimpleApis.kuCointApi;

import io.restassured.http.ContentType;
import jdk.dynalink.linker.LinkerServices;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    // у stream всегда должен быть конечный метод и в этом случае -применение сортировки и сохранение
    // ниже конечный метод collect засунет всю отфильтрованную информацию в список usdTickers
    @Test
    public void checkCrypto(){
        List<TickerData> usdTickers = getTickers.stream().filter(x->x.getSymbol().endsWith("USDT")).collect(Collectors.toList());
    // здесь мы используем лямбда выражение.с переменной списка, к которой мы будем обращаться и вызывать метод
//      Boolean test =  usdTickers.stream().allMatch(x->x.getSymbol().endsWith("USDT"));
      //  убеждаемся что каждый элемент имеет символ USDT
//        System.out.println(test);

        Assert.assertTrue(usdTickers.stream().allMatch(x->x.getSymbol().endsWith("USDT")));
//        int a = 0;
    }

    @Test
    public void sortHighToLow(){
        // если ниже не прописать в условии sorted поле, по которому будем сортировать. то будем по всем полям
        List<TickerData> highToLow = getTickers.stream().filter(x->x.getSymbol().endsWith("USDT")).sorted(new Comparator<TickerData>() {
            @Override
            public int compare(TickerData o1, TickerData o2) { // метод compare присутствует у интерфейса
                // TickerData o1, TickerData o2 - объекты. Ниже возвращаем от большего к м. чтобы следовать highToLow
                return o2.getChangeRate().compareTo(o2.getChangeRate());
            }
        }).collect(Collectors.toList());
        // метод limit показывает сколько элементов мы можем достать
        List<TickerData> top10 = highToLow.stream().limit(10).collect(Collectors.toList());
//        int a = 0;
        Assert.assertTrue(top10.get(9).getSymbol().contains("FLAME"));
        System.out.println(top10.get(9).getSymbol() + "вот");
        Assertions.assertEquals(top10.get(9).getSymbol(), "FLAME-USDT");

//        int a = 0;
    }
}
