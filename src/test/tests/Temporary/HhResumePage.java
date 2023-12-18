package Temporary;

import com.codeborne.selenide.Selenide;

public class HhResumePage {
    public HhResumePage(String URL) {
        Selenide.open(URL);
    }
}
