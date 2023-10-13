package hh;

import core.BaseSelenideTest;
import org.junit.jupiter.api.Test;

public class hhTestPage extends BaseSelenideTest {
    private final static String URL = "https://hh.ru/resume/d01a1fa8ff0433a6aa0039ed1f4a756954536f";
@Test
    public void checkAttributesHashMap(){
        HhResumePage hhResumePage = new HhResumePage(URL);

}

}
