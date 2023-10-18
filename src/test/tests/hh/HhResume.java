package hh;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class HhResume {
    private final SelenideElement gender = $x("//span[@data-qa='resume-personal-gender']");
    public HhResume(String URL) {
        Selenide.open(URL);
    }
}
