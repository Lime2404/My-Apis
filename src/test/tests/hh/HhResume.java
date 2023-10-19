package hh;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;

public class HhResume {
    private final SelenideElement gender = $x("//span[@data-qa='resume-personal-gender']");
    private final SelenideElement age = $x("//span[@data-qa='resume-personal-age']/span");

    public static String GENDER = "Пол";

    public HhResume(String URL) {
        Selenide.open(URL);
    }
//    public Map<String, Object> getAttributes(){
//        return new HashMap<>(){{
//            put(GENDER, getGender());
//        }};
//    }
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
