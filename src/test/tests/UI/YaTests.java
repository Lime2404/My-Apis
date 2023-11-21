package UI;

import com.codeborne.selenide.Selenide;
import core.BaseSelenideTest;
import org.junit.Assert;
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
        driver.findElement(By.xpath("//button[contains(.,'Найти')]")).click();
//        String text = driver.findElement(By.xpath("//*[@id=\"search-result\"]/li[2]/article/div[2]/div")).getText();
//        System.out.println(text);
//        Assert.assertTrue(text.contains("Москва"));
    }

    @Test
    public void FailedTest() {
        driver.get(URL);
        driver.findElement(By.xpath("//input[@placeholder='найдётся всё']")).sendKeys("Время в Москве");
        driver.findElement(By.xpath("//button[contains(.,'123456']")).click();
    }
}
