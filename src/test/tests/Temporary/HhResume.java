package Temporary;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;

public class HhResume {
    private final SelenideElement gender = $x("//span[@data-qa='resume-personal-gender']");
    private final SelenideElement age = $x("//span[@data-qa='resume-personal-age']/span");
    private final SelenideElement city = $x("//span[@data-qa='resume-personal-address']");
    private final SelenideElement liveData = $x("//span[@data-qa='resume-personal-address']/ancestor::p");
    private final SelenideElement tick = $x("//div[@data-qa='resume-contacts-phone']/a[1]");

    // создаем статичные переменные чтобы потом к ним можно было обратиться без создания экземпляра класса
    public static String GENDER = "Пол";
    public static String AGE = "Возраст";
    public static String CITY = "Город";
    public static String CONFIRMED_PHONE = "Подтвержденный телефон";
    public static String READY_TO_RELOCATE = "Готовность к переезду";

    public HhResume(String URL) {
        Selenide.open(URL);
    }
    // актуальные результаты
    public Map<String, Object> getAttributes(){ // тип Object указывается чтобы не обработать разные типы данных в значениях
        return new HashMap<>(){{ // более короткая запись чем создание мапы и её return
            put(GENDER, getGender()); // название карты уже не нужно. так как все входные данные связаны с ней
            put(AGE, getAge());
            put(CITY, getCity());
            put(CONFIRMED_PHONE, isPhoneConfirmed());
            put(READY_TO_RELOCATE, isReadyToRelocate());
        }};
    }

    public boolean isPhoneConfirmed(){
        return tick.isDisplayed();
    }

    public boolean isReadyToRelocate(){
//        String[] array = liveData.getText().split(", ");
//        String relocate = array[2];
//        return false;
        return !liveData.getText().split(", ")[1].equals("не готов к переезду");
    }

    public String getCity(){
        return city.getText();
    }


    //можно регулярным выражением \D+ или его эквивалентом [^0-9] выбрать все символы которые не являются числом
// а далее в коде Заменить все найденные символы на пустоту
    public int getAge(){
    return Integer.parseInt(age.getText().replaceAll("\\D+", ""));
    }


    public String getGender(){
        return gender.getText().equals("Мужчина") ? "М" : "Ж"; //тернарный оператор - если условие
        // условие в equals верно, товернуть М в противном случае Ж
    }
//    public String getGender() {
//        String genderValue = gender.getText();
//        if (genderValue.equals("Мужчина")) {
//
//            genderValue = "M";
//        }
//        return genderValue;
//        }




}
