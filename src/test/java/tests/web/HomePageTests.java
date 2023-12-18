package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
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
        step("Open home page", () -> {
            homePage.openPage("");
        });
        step("Check page title", () -> {
            Helpers.verifyPageTitle("Habitica - Gamify Your Life");
        });
    }

    @Test
    @DisplayName("Unsuccessful registration without filling in all fields")
    @Story("Testing registration without filling in all fields")
    void verifyNotificationOnRegistrationWithoutFillFieldsTest() {
        step("Open home page", () -> {
            homePage.openPage("");
        });
        step("Click on the registration button", () -> {
            homePage.clickSingUpButton();
        });
        step("Make sure that a message appears about missing name, password and email", () -> {
            homePage.verifyNotificationText("Missing username. Missing email. Missing password.");
        });
    }

    @Test
    @DisplayName("Check redirect on start button click ")
    @Story("Testing redirection to the registration page when the Start button is clicked")
    void checkRedirectToRegistrationPageOnStartButtonClickTest() {
        step("Open home page", () -> {
            homePage.openPage("");
        });
        step("Click on the start registration button", () -> {
            homePage.clickStartNavLink("Get Started");
        });
        step("Make sure we have reached the registration page by checking the page title", () -> {
            Helpers.verifyPageTitle("Register | Habitica");
        });

    }
}
