package core;

import ReadProperties.ConfigProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

// здесь мы прописывем базовый тестовый класс

abstract public class BaseSelenuimTest {
    protected WebDriver driver1;
    // c веб драйвером мы будем взаимодействоатьв во всех классах где будем работать с веб элементами
    // ниже пойдут настройки веб драйвера и нам надо чтобы они были актуальны на всех страницах
    @Before // читать документацию с JUnit
    public void setUp(){
        WebDriverManager.chromedriver().setup(); // здесь мы скачиваем драйвер и про
        // писываем его во всех путях. Этот драйвер взаимодействует с браузером
        // чтобы работать с driver надо её инициализировать
        driver1 = new ChromeDriver(); // выбираем конкретно ChromeDriver
        // чтобы тесты не падали, страница могла вовремя прогрузиться, пропишем
        // ниже ожидания для браузера
        driver1.manage().window().maximize(); // окно на весь экран и не было мобильного окна
        // загружаем страницу до опрделенного момента, но не дольше чем 10 секунд
        driver1.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); // означает что скоро это код устареет
        // пропишем время ожидания элементов на странице перед взаимодействием с ними
        driver1.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        BaseSeleniumPage.setDriver(driver1); // завершаем инициализацию веб драйвера вызывая его
//        driver1.get("https://kz.pegast.asia/ru/agency/pegasys-external/search-tour");
        driver1.get(ConfigProvider.URL);
    }
    // метод ниже будет вызываться каждый раз, когда у нас будут заканчиваться тесты

    @After
    // закрытие браузера
    public void tearDown(){
        driver1.close(); // закрываем хром драйвер,лучше закрывать всегда
        driver1.quit(); // закрываем окно браузера
    }

}
