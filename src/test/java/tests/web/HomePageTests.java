package tests.web;

import org.junit.jupiter.api.Test;
import pages.HomePage;
import utils.Helpers;

public class HomePageTests extends TestBase {
    HomePage homePage = new HomePage();

    @Test
    void checkUrlTitleTest(){
        homePage.openPage("");
        Helpers.verifyPageTitle("Habitica - Gamify Your Life");
    }
}
