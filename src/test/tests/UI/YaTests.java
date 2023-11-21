package UI;

import com.codeborne.selenide.Selenide;
import core.BaseSelenideTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class YaTests extends BaseSelenideTest {

    private final static String URL = "https://ya.ru";
    @Test
    public void SuccessTest() throws InterruptedException {
//        Selenide.open(URL);
        driver.get(URL);
        driver.findElement(By.xpath("//input[@placeholder='найдётся всё']")).sendKeys("Время в Москве");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//"))
    }
}
