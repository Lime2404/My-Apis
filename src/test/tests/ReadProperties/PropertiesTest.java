package ReadProperties;

import UI.BaseSelenideTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PropertiesTest extends BaseSelenideTest {
    @Test
    public void readProperties() throws IOException {
        // здесь мы начинаем подгружать настройки из application.properties
        // если не работает ссылка на файл, то надо выбрать правильный ресурс для папки crt+shift+alt+s
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        String urlFromProperty = System.getProperty("url");
        System.out.println(urlFromProperty);
    }

    @Test
    public void readFromConf(){
        // здесь мы планируем использовать библиотеку typesafe(зависимость в maven) и создать для неё конфиг public class ConfigProvider {
        String urlFromConfig = ConfigProvider.URL;
        System.out.println(urlFromConfig);
        Boolean isAdminFromConfig = ConfigProvider.IS_ADMIN;
        System.out.println(isAdminFromConfig);
        // еще один пример как обращаться к конфигам только через метод readConfig()
        if(ConfigProvider.readConfig().getBoolean("usersParams.admin.isAdmin")) {
            System.out.println("сошлось");
        }
        else
            System.out.println("не сошлось");
    }
}
