package tests.web;

import decorator.LoggingExtension;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.HomePage;
import utils.Helpers;

import static io.qameta.allure.Allure.step;

@Feature("Home page testing")
@Tag("web")
public class HomePageTests extends TestBase {
    HomePage homePage = new HomePage();

    @Test
    @DisplayName("Successful verification of home page title")
    @Story("Testing home page header")
    void checkHomePageTitleTest() {
        homePage.openPage("");
        Helpers.verifyPageTitle("Habitica - Gamify Your Life");
    }

    @Test
    @DisplayName("Unsuccessful registration without filling in all fields")
    @Story("Testing registration without filling in all fields")
    void verifyNotificationOnRegistrationWithoutFillFieldsTest() {
        homePage.openPage("")
                .clickSingUpButton()
                .verifyNotificationText("Missing username. Missing email. Missing password.");
    }

    @Test
    @ExtendWith(LoggingExtension.class)
    @DisplayName("Check redirect on start button click")
    @Story("Testing redirection to the registration page when the Start button is clicked")
    void checkRedirectToRegistrationPageOnStartButtonClickTest() {
        homePage.openPage("")
                .clickStartNavLink("Get Started");
        Helpers.verifyPageTitle("Register | Habitica");
    }
}
