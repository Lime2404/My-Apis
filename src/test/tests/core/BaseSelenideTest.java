package core;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Базовый класс для инициализации селенида
 */
public class BaseSelenideTest {

    public static EventFiringWebDriver driver;
    public static ChromeOptions chromeOptions;
    /**
     * Инициализация selenide с настройками
     */
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.webdriverLogsEnabled = true;
//        Configuration.driverManagerEnabled = true;
        chromeOptions = new ChromeOptions();
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        driver = new EventFiringWebDriver(new ChromeDriver(chromeOptions));
    }

    /**
     * Выполнение метода перед каждым запуском тестов
     */
    @BeforeEach
    public void init(){
        setUp();
    }

    /**
     * Выполнение метода после каждого закрытия тестов
     */
    @AfterEach
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
