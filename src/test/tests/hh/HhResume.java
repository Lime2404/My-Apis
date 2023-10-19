package hh;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;

public class HhResume {
    private final SelenideElement gender = $x("//span[@data-qa='resume-personal-gender']");

    public static String GENDER = "Пол";

    public HhResume(String URL) {
        Selenide.open(URL);
    }
    public Map<String, Object> getAttributes(){
        return new HashMap<>(){{
            put(GENDER, getGender());
        }};
    }

//    public String getGender(){
//        return gender.getText().equals("Мужчина") ? "М" : "Ж";
//    }
    public String getGender() {
        String genderValue = gender.getText();
        if (genderValue.equals("Мужчина")) {

            genderValue = "M";
        }
        return genderValue;
        }



}
