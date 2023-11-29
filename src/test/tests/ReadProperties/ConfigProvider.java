package ReadProperties;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConfig();
    // создаем метод который будет обращаться к application.conf и читать его содержиаое для
    // инициализации Config config
    // по факту ниже идет реализация интерфейса
    static Config readConfig(){
//  1.      return ConfigFactory.load("application.conf");
//  2.  Если есть такой профиль как "testProfile" то мы загружаем проверти и получаем профиль
     return ConfigFactory.systemProperties().hasPath("testProfile")
        ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
        : ConfigFactory.load("application.conf"); // теперь можно обращаться к файоу и читать
    }
//    Создаем статичные сроки-перменные, чтобы открывались нужные нам значения из другого класса
    String URL = readConfig().getString("url1"); // прочитали значние url из файла
    Integer AGE = readConfig().getInt("age");
    String ADMIN_LOGIN = readConfig().getString("usersParams.admin.login"); // cсылка не работает, но
    // библиотека всё равно позволяе копать вглубь иерархии
    String DEMO_PASSWORD = readConfig().getString("usersParams.demo.password");
    Boolean IS_ADMIN = readConfig().getBoolean("usersParams.admin.isAdmin");

}
