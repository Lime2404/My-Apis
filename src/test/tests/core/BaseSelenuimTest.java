package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
// здесь мы прописывем базовый тестовый класс

abstract public class BaseSelenuimTest {
    protected WebDriver driver;
    // c веб драйвером мы будем взаимодействоатьв во всех классах где будем работать с веб элементами
    // ниже пойдут настройки веб драйвера и нам надо чтобы они были актуальны на всех страницах
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup(); // здесь мы скачиваем драйвер и про
        // писываем его во всех путях. Этот драйвер взаимодействует с браузером
        // чтобы работать с driver надо её инициализировать
        driver = new ChromeDriver(); // выбираем конкретно ChromeDriver
        // чтобы тесты не падали, страница могла вовремя прогрузиться, пропишем
        // ниже ожидания для браузера
        driver.manage().window().maximize(); // окно на весь экран и не было мобильного окна
        // загружаем страницу до опрделенного момента, но не дольше чем 10 секунд
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        // пропишем время ожидания элементов на странице перед взаимодействием с ними
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        BaseSeleniumPage.setDriver(driver); // завершаем инициализацию веб драйвера вызывая его
    }
    // метод ниже будет вызываться каждый раз, когда у нас будут заканчиваться тесты

    @After
    // закрытие браузера
    public void tearDown(){
        driver.close(); // закрываем хром драйвер,лучше закрывать всегда
        driver.quit(); // закрываем окно браузера
    }

}
