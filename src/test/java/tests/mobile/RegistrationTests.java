package tests.mobile;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import tests.TestData;

import static io.qameta.allure.Allure.step;

@Feature("Registration testing")
@Tag("android")
public class RegistrationTests extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();


    @Test
    @DisplayName("Failed registration attempt with invalid email address")
    @Story("Testing failed registration with invalid email address")
    void unsuccessfulRegistrationWithInvalidEmailTest() {
        step("Click the button to skip the welcome window", () -> {
            registrationPage.clickSkipButton();
        });
        step("Click on the `Registration` button", () -> {
            registrationPage.clickRegistrationButton();
        });
        step("Fill in the registration fields", () -> {
            registrationPage.setUsername(testData.randomUsername)
                    .setEmail(testData.wrongEmail)
                    .setPassword(testData.randomPassword)
                    .setConfirmPassword(testData.randomPassword);
        });
        step("Click on the `Registration` button", () -> {
            registrationPage.clickConfirmRegistrationButton();
        });
        step("Verify that a message appears indicating that the email field is invalid.", () -> {
            registrationPage.verifyMessageTextView("Invalid email address.");
        });
    }


    @Test
    @DisplayName("Failed registration attempt without filling in all fields")
    @Story("Testing of unsuccessful Registration Without Filling All Fields")
    void unsuccessfulRegistrationWithoutFillingAllFieldsTest() {
        step("Click the button to skip the welcome window", () -> {
            registrationPage.clickSkipButton();
        });
        step("Click on the `Registration` button", () -> {
            registrationPage.clickRegistrationButton();
        });
        step("Click on the registration confirmation button", () -> {
            registrationPage.clickConfirmRegistrationButton();
        });
        step("Check that a message appears about the need to fill in all fields", () -> {
            registrationPage.verifyMessageTextView("You have to fill out all fields.");
        });
    }

    @Test
    @DisplayName("Failed registration attempt with too short password")
    @Story("Testing of unsuccessful Registration Without Filling All Fields")
    void unsuccessfulRegistrationWithTooShortPasswordTest() {
        step("Click the button to skip the welcome window", () -> {
            registrationPage.clickSkipButton();
        });
        step("Click on the `Registration` button", () -> {
            registrationPage.clickRegistrationButton();
        });
        step("Fill in the registration fields", () -> {
            registrationPage.setUsername(testData.randomUsername)
                    .setEmail(testData.email)
                    .setPassword(testData.shortPassword)
                    .setConfirmPassword(testData.shortPassword);
        });
        step("Click on the `Registration` button", () -> {
            registrationPage.clickConfirmRegistrationButton();
        });
        step("Check that a message appears asking you to enter a longer password", () -> {
            registrationPage.verifyMessageTextView("Your password has to be at least 8 characters long");
        });
    }
}
