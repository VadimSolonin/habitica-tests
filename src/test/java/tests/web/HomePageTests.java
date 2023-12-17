package tests.web;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import utils.Helpers;

import static io.qameta.allure.Allure.step;

@Tag("web")
public class HomePageTests extends TestBase {
    HomePage homePage = new HomePage();

    @Test
    void checkHomePageTitleTest() {
        step("Открыть начальную страницу", () -> {
            homePage.openPage("");
        });
        step("Проверить title страницы", () -> {
            Helpers.verifyPageTitle("Habitica - Gamify Your Life");
        });
    }

    @Test
    void verifyNotificationOnRegistrationWithoutFillFieldsTest() {
        step("Открыть начальную страницу", () -> {
            homePage.openPage("");
        });
        step("Кликнуть на кнопку регистрации", () -> {
            homePage.clickSingUpButton();
        });
        step("Убедиться, что появилось сообщение об отсутствии имени, пароля и почты", () -> {
            homePage.verifyNotificationText("Missing username. Missing email. Missing password.");
        });
    }

    @Test
    void checkRedirectOnStartButtonClickTest() {
        step("Открыть начальную страницу", () -> {
            homePage.openPage("");
        });
        step("Нажать на кнопку начала регистрации", () -> {
            homePage.clickStartNavLink("Get started");
        });
        step("Проверить title страницы", () -> {
            Helpers.verifyPageTitle("Registration | Habitica");
        });

    }
}
