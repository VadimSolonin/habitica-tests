package tests.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import utils.Helpers;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

public class HomePageTests extends TestBase {
    HomePage homePage = new HomePage();

    @Test
    void checkHomePageTitleTest(){
        step("Открыть начальную страницу", () -> {
            homePage.openPage("");
        });
        step("Проверить title страницы", () -> {
            Helpers.verifyPageTitle("Habitica - Gamify Your Life");
        });
    }
    @Test
    void verifyNotificationOnRegistrationWithoutFillFieldsTest(){
        step("Открыть начальную страницу", () -> {
            homePage.openPage("");
        });
        step("Кликнуть на кнопку регистрации", () -> {
            homePage.clickSingUpButton();
        });
        step("Убедиться, что появилось сообщение об отсутствии имени, пароля и почты", () -> {
            homePage.verifyNotificationText("Отсутствует имя пользователя. Отсутствует адрес электронной почты. Отсутствует пароль.");
        });
    }
    @Test
    void checkRedirectOnStartButtonClickTest(){
        step("Открыть начальную страницу", () -> {
            homePage.openPage("");
        });
        step("Открыть начальную страницу", () -> {
            homePage.clickStartNavLink("Начать");
        });
        step("Проверить title страницы", () -> {
            Helpers.verifyPageTitle("Регистрация | Habitica");
        });

    }
}
