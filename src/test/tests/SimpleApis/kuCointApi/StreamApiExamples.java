package SimpleApis.kuCointApi;

import io.restassured.http.ContentType;
import jdk.dynalink.linker.LinkerServices;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.get;
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
//    int a = 0;
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
        int a = 0;
    }

    @Test
    public void sortHighToLow(){
        // если ниже не прописать в условии sorted поле, по которому будем сортировать. то будем по всем полям
        List<TickerData> highToLow = getTickers.stream().filter(x->x.getSymbol().endsWith("USDT")).sorted(new Comparator<TickerData>() {
            @Override
            public int compare(TickerData o1, TickerData o2) { // метод compare присутствует у интерфейса
                // TickerData o1, TickerData o2 - объекты. Ниже возвращаем от большего к м. чтобы следовать highToLow
                return o2.getChangeRate().compareTo(o1.getChangeRate());
            }
        }).collect(Collectors.toList());
        // метод limit показывает сколько элементов мы можем достать
        List<TickerData> top10 = highToLow.stream().limit(10).collect(Collectors.toList());
//        int a = 0;
        Assert.assertTrue(top10.get(9).getSymbol().contains("USDT"));
        System.out.println(top10.get(9).getSymbol() + "вот");
//        Assertions.assertEquals(top10.get(9).getSymbol(), "FLAME-USDT");

    }

    @Test // получаем список криптовалют самых низких за сутки
    public void sortLowToHigh(){
        List<TickerData> lowToHigh = getTickers.stream().filter(x->x.getSymbol().endsWith("USDT"))
                .sorted(new TickerComparatorLow()).limit(10).collect(Collectors.toList());
        int a = 0;
    }

    // Собираем класс в map. Преобразуем название валюты в маленькие буквы
    @Test
    public void map(){
        Map<String, Float> usd = new HashMap<>(); // ниже берем один тип данных, что-то с ним делаем и преобразуем в другой тип данных
//        List<String> lowerCases = getTickers.stream().map(x->x.getSymbol().toLowerCase()).collect(Collectors.toList());
        // метод map достал названия криптовалют и превратил их lowercase
        //ниже будем обращаться к нашей хэш карте и вызывать у неё метод put
        // через лямбду мы полученную информацию засовывали в map и дальше с ней можно как-то работать
//        getTickers.forEach(x->usd.put(x.getSymbol(), Float.parseFloat(x.getChangeRate())));
        // второй метод  - это работать с классом. Создадим TickerShort

        List<TickerShort> shortList = new ArrayList<>();
        getTickers.forEach(x->shortList.add(new TickerShort(x.getSymbol(), Float.parseFloat(x.getChangeRate()))));
        int a = 0;
    }
}
