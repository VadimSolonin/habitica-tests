package tests.mobile;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static io.qameta.allure.Allure.step;


public class RegistrationTests extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();


    @Test
    void unsuccessfulRegistrationWithInvalidEmailTest() {
        step("Нажать на кнопку пропуска приветственного окна", () -> {
            registrationPage.clickSkipButton();
        });
        step("Нажать на кнопку `Регистрация`", () -> {
            registrationPage.clickRegistrationButton();
        });
        step("Заполнить регистрационные поля", () -> {
            registrationPage.setUsername(testData.username)
                    .setEmail(testData.wrongEmail)
                    .setPassword(testData.password)
                    .setConfirmPassword(testData.password);
        });
        step("Нажать на кнопку `Регистрация`", () -> {
            registrationPage.clickConfirmRegistrationButton();
        });
        step("Проверить, что появилось сообщение о невалидном поле email", () -> {
            registrationPage.verifyMessageTextView("Invalid email address.");
        });
    }

    @Tag("android")
    @Test
    void unsuccessfulRegistrationWithoutFillingAllFieldsTest() {
        step("Нажать на кнопку пропуска приветственного окна", () -> {
            registrationPage.clickSkipButton();
        });
        step("Нажать на кнопку `Регистрация`", () -> {
            registrationPage.clickRegistrationButton();
        });
        step("Нажать на кнопку подтверждения регистрации` ", () -> {
            registrationPage.clickConfirmRegistrationButton();
        });
        step("Проверить, что появилось сообщение о необходимости заполнить все поля", () -> {
            registrationPage.verifyMessageTextView("You have to fill out all fields.");
        });
    }

    @Test
    void unsuccessfulRegistrationWithTooShortPasswordTest() {
        step("Нажать на кнопку пропуска приветственного окна", () -> {
            registrationPage.clickSkipButton();
        });
        step("Нажать на кнопку `Регистрация`", () -> {
            registrationPage.clickRegistrationButton();
        });
        step("Заполнить регистрационные поля", () -> {
            registrationPage.setUsername(testData.username)
                    .setEmail(testData.email)
                    .setPassword(testData.shortPassword)
                    .setConfirmPassword(testData.shortPassword);
        });
        step("Нажать на кнопку `Регистрация`", () -> {
            registrationPage.clickConfirmRegistrationButton();
        });
        step("Проверить, что появилось сообщение о необходимости указать более длинный пароль", () -> {
            registrationPage.verifyMessageTextView("Your password has to be at least 8 characters long");
        });
    }
}
