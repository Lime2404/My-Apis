package helpDesk;

import ReadProperties.ConfigProvider;
import core.BaseSeleniumPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//https://youtu.be/brKmBegyObU?list=PLS-CH047rQ3U8iIUIb9gm3cyf9kbOSasP
// https://kz.pegast.asia/ru/agency/pegasys-external/search-tour

public class MainPage extends BaseSeleniumPage {
    private final By cityList = By.xpath("//*[@id=\"departure-location\"]/a");
    // ниже мы сразу обращаемся к элементам, которых может не быть на станице пока мы не кликнули
    private WebElement cityListElementWeak = driver1.findElement(cityList);
    // есть подходящий вариант обратиться к элементу только тогда, когда он будет доступен
    @FindBy(xpath = "//*[@id=\"departure-location\"]/a")
    // теперь элемент cityListElement будет иницивлизирован толко когда мы будем к нему обращаться
    private WebElement cityListElement;
////a[@rel = "Алматы_553"]

    @FindBy(xpath = "//*[@id=\"departure-location\"]/a//a[@rel = \"Алматы_553\"]")
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

    public MainPage() {
        driver1.get(ConfigProvider.URL);
        PageFactory.initElements(driver1, this); //this - мы инициализируем этот класс
    }
//"Hilton Astana"
    public MainPage createTicket(String hotelInput){
        cityListElement.click();
        cityValue.click();
        input.sendKeys(hotelInput);
        dateField.click();
        dateValue.click();
        search.click();
        return this; // здесь мы возвращаем текущую страницу так как мы продолжаем с ней работать
    }

    // создавая экземпляр класса у нас произойдет инициализация всей страницы
}
