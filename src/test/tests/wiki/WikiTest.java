package wiki;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import UI.BaseSelenideTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;

public class WikiTest extends BaseSelenideTest {
    private final static String URL = "https://ru.wikipedia.org/wiki/Java";

    @Test
    public void openAllHrefs() {
        Selenide.open(URL); // открываем нашу ссылку
        // далее обозначаем коллекцию элементов. ниже идет метод  $$x - статичный метод селенида для
        // нахождение коллекции элемента через XPATH
        // и в качестве аргумента XPAth
        ElementsCollection hrefs = $$x("//div[@id='toc']//a[@href]");
        // имея коллецию. мы можем взаимодействовать с ней используя класс stream()
        // для этого класс должен имплементировать интерфейс Iterable
        // в hashmap иетарацию не провести так как нет имплементации Iterable
        //ниже мы будем перебирать каждый элемент коллекции и будем выбирать значение аттрибута href
        List<String> links = new ArrayList<>();
        //1
        for (int i = 0; i < hrefs.size(); i++) {
            links.add(hrefs.get(i).getAttribute("href")); // заполняем список строками href страницы
        }
//    int j = 0;
        //2  Идя по методам в глуб видео что мы имеем дело с selenide eleent, значит его можно перебирать
        for (SelenideElement element : hrefs) {
            links.add(element.getAttribute("href"));
        }
//        int i = 0;
        //3  Используя лямбда выражение мы каждый элемент из hrefs x записываем в links
        // зачеркнутое stream означает что мы используем два раза перебор так как stream уже есть в for each
        hrefs.stream().forEach(x -> links.add(x.getAttribute("href")));
//        int i = 0;

        // получая ссылки в каждом элементе, мы можем перебирать их значения
//        1
        // открытие всех ссылок с помощью stream api и ссылочного метода selenide open
        links.forEach(Selenide::open); //аналог links.forEach(x->Selenide.open(x)) но для одного действия
        // это ту мач.  Сейчас начнется быстрый скрол
//        int i = 0;
//        2
        // Сравниваем ссвылку из браузера с тем что нам приходит из коллекции
        for (int i = 0; i < links.size(); i++) {
            String listurl = links.get(i); // получаем ссылку. с которой будем работать
            Selenide.open(listurl); //открываем полученную ссылку
            // теперь получаем ссылку из адресной строки браузера
            String currenturl = WebDriverRunner.getWebDriver().getCurrentUrl();
//            System.out.println(currenturl + " " + listurl);
            Assert.assertEquals(currenturl, listurl);
//            int j = 0;
        }
    }
// 15:24 https://youtu.be/eSYSQikJCTY?list=PLS-CH047rQ3U8iIUIb9gm3cyf9kbOSasP

        // получаем случайное значение из списка с ссылками - рандомное открытие ссылок
        @Test
        public void openAllHrefs2() {
            Selenide.open(URL);
            ElementsCollection hrefs = $$x("//div[@id='toc']//a[@href]");
            List<String> links = new ArrayList<>();
            hrefs.stream().forEach(x -> links.add(x.getAttribute("href")));
            // ниже вызываем класс для вычисления рандомных значений
            Random random = new Random();
            while(links.size() > 0){
                // ниже в аргументе nextInt мы указываем значение до которого мы хотим вызвать случайное число
                int randomNumber = random.nextInt(links.size());
                Selenide.open(links.get(randomNumber));
                // но для того чтобы не натыкаться на повторные ссылки мы делаем следующее
                links.remove(WebDriverRunner.getWebDriver().getCurrentUrl());
            }
            int a = 0;
        }
    @Test
    public void openAllHrefs3() {
        Selenide.open(URL);
        ElementsCollection hrefs = $$x("//div[@id='toc']//a[@href]");
        List<String> links = new ArrayList<>();
        hrefs.stream().forEach(x -> links.add(x.getAttribute("href")));
        // получаем список с длинной ссылок ( список чисел)
        // метод map после stream - это метод. который конвертирует один тип данных в другой
        // на примере ниже мы преобразуем строку в число. Работа stream api c коллекциями в selenide
        // промежуточный метод collect преобразует отфильтровынные значение например в список
        List<Integer> linksLenght = hrefs.stream().map(x->x.getAttribute("href").length()).collect(Collectors.toList());
//    int i = 0;
    }
}
