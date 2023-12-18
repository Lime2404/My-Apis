package UI.helpDesk;

import UI.BaseSelenuimTest;
import org.junit.Test;


public class HelpDeskTest extends BaseSelenuimTest {

@Test // если не выбрать нужную библиотеку, то
    public void checkTicket(){
        String hotelInput = "Hilton Astana";
        MainPage mainPage = new MainPage();
        mainPage.createTicket(hotelInput);

        //42.14



    }
}
