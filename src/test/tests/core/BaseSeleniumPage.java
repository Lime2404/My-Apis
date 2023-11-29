package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

abstract public class BaseSeleniumPage {
    // на всех page классах мы будет наследаться от этого класса
    protected static WebDriver driver1; // если не сделать static, то не будет видно в методах

    // создадим метод для присвоения webdriver из BaseSelenium в этот класс
    public static void setDriver(WebDriver webDriver) { // здесь будем пинговать веб драйвер
        driver1 = webDriver;
    }

}
