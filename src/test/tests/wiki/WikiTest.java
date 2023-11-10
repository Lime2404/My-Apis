package wiki;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import core.BaseSelenideTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;

public class WikiTest extends BaseSelenideTest {
    private final static String URL = "https://ru.wikipedia.org/wiki/Java";

    @Test
    public void openAllHrefs(){
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
        for(int i = 0; i < hrefs.size(); i++){
            links.add(hrefs.get(i).getAttribute("href")); // заполняем список строками href страницы
        }
//    int i = 0;
        //2  Идя по методам в глуб видео что мы имеем дело с selenide eleent, значит его можно перебирать
        for(SelenideElement element : hrefs){
            links.add(element.getAttribute("href"));
        }
//        int i = 0;
        //3  Используя лямбда выражение мы каждый элемент из hrefs x записываем в links
        // зачеркнутое stream означает что мы используем два раза перебор так как stream уже есть в for each
        hrefs.stream().forEach(x->links.add(x.getAttribute("href")));
    }
}
