package UI.helpDesk;

import UI.BaseSeleniumPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//https://youtu.be/brKmBegyObU?list=PLS-CH047rQ3U8iIUIb9gm3cyf9kbOSasP
// https://kz.pegast.asia/ru/agency/pegasys-external/search-tour

public class MainPage extends BaseSeleniumPage {
//    private final By cityList = By.xpath("//*[@id=\"departure-location\"]//select[@id=\"DepartureLocations\"]//optgroup[@label=\"Казахстан\"]");
    private final By cityList = By.xpath("/html/body/ul[3]");
    // ниже мы сразу обращаемся к элементам, которых может не быть на станице пока мы не кликнули
    private WebElement cityListElementWeak = driver1.findElement(cityList);
    // есть подходящий вариант обратиться к элементу только тогда, когда он будет доступен
//    @FindBy(xpath = "//*[@id=\"departure-location\"]/a")
//    @FindBy(xpath = "//*[@id=\"departure-location\"]//select[@id=\"DepartureLocations\"]//optgroup[@label=\"Казахстан\"]")
    @FindBy(xpath = "/html/body/ul[3]")

    // теперь элемент cityListElement будет иницивлизирован толко когда мы будем к нему обращаться
    private WebElement cityListElement;
////a[@rel = "Алматы_553"]

//    @FindBy(xpath = "//*[@id=\"departure-location\"]//select[@id=\"DepartureLocations\"]//optgroup[@label=\"Казахстан\"]//option[@value=\"Алматы_553\"]")
    @FindBy(xpath = "/html/body/ul[3]/li[4]/a")

    ///html/body/ul[3]/li[4]/a
    private WebElement cityValue;

    @FindBy(id = "hotel-filter-by-nazvanie")
    private WebElement input;
    ////input[@id="MinDepartureDate"]

    @FindBy(id = "MinDepartureDate")
    private WebElement dateField;

    @FindBy(xpath = "//table[@class=\"ui-datepicker-calendar\"]//a[text()=\"29\"]")
    private WebElement dateValue;
    // теперь нам нужен page factoring отвечающий за инициализацию всех элементов и в
    // дальнейшем возможностью взаимодействовать с этими элементами

    @FindBy(xpath = "//button[@id = \"search-button\"]")
    private WebElement search;

    public MainPage() { // при создании объекта класса Main Page ожидается открытие страницы, инициализация всех элементов на этой страницу
//        driver1.get(ConfigProvider.URL);
//        driver1.get("https://kz.pegast.asia/ru/agency/pegasys-external/search-tour");
        PageFactory.initElements(driver1, this); //this - мы инициализируем этот класс
    }
//"Hilton Astana"
    public MainPage createTicket(String hotelInput){
        cityListElement.click();
        cityValue.click();
        input.sendKeys(hotelInput);
//        input.sendKeys(hotelInput, ENTER);
        dateField.click();
        dateValue.click();
        search.click();
        return this; // здесь мы возвращаем текущую страницу так как мы продолжаем с ней работать
        // если мы ниже создадим еще один метод. то мы так же можем вызвать его при звпуске теста через точку
        // в этой строке mainPage.createTicket(hotelInput);
        // а если метод еще и возвращает новый класс, то в тесте можно даже использовать методы этого класса
        //https://youtu.be/brKmBegyObU?list=PLS-CH047rQ3U8iIUIb9gm3cyf9kbOSasP&t=3013
    }

    // создавая экземпляр класса у нас произойдет инициализация всей страницы
}
