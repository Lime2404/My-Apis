package UI;

import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class YaTests extends BaseSelenideTest {

    private final static String URL = "https://ya.ru";
    @Test
    @Issue(value = "1")
    @Link(name = "Погода", url = URL)
    public void SuccessTest() throws InterruptedException {
//        Selenide.open(URL);
        driver.get(URL);
        driver.findElement(By.xpath("//input[@placeholder='найдётся всё']")).sendKeys("Время в Москве");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(.,'Найти')]")).click();

//        Ниже представлена старая реализация, которую я вынес в новый класс BaseSelenideTest
//        String text = driver.findElement(By.xpath("//*[@id=\"search-result\"]/li[2]/article/div[2]/div")).getText();
//        System.out.println(text);
//        Assert.assertTrue(text.contains("Москва"));
//        driver.close();
//        driver.quit();
    }
//    @After
//    public void tearDown(){
//        driver.close(); // закрываем хром драйвер
//        driver.quit(); // закрываем окно браузера
//    }

    @Test
    public void FailedTest() {
        driver.get(URL);
        driver.findElement(By.xpath("//input[@placeholder='найдётся всё']")).sendKeys("Время в Москве");
        driver.findElement(By.xpath("//button[contains(.,'123456']")).click();
    }
}
