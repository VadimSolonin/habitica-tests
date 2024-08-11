package tests.mobile;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import tests.TestData;

import static io.qameta.allure.Allure.step;

@Feature("Registration testing")
public class RegistrationTests extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();

    @Test
    @DisplayName("Failed registration attempt with invalid email address")
    @Story("Testing failed registration with invalid email address")
    void unsuccessfulRegistrationWithInvalidEmailTest() {
        registrationPage.clickSkipButton()
                .clickRegistrationButton()
                .setUsername(testData.randomUsername)
                .setEmail(testData.wrongEmail)
                .setPassword(testData.randomPassword)
                .setConfirmPassword(testData.randomPassword)
                .clickConfirmRegistrationButton()
                .verifyMessageTextView("Invalid email address.");
    }

    @Test
    @DisplayName("Failed registration attempt without filling in all fields")
    @Story("Testing of unsuccessful Registration Without Filling All Fields")
    void unsuccessfulRegistrationWithoutFillingAllFieldsTest() {
        registrationPage.clickSkipButton()
                .clickRegistrationButton()
                .clickConfirmRegistrationButton()
                .verifyMessageTextView("You have to fill out all fields.");
    }

    @Test
    @DisplayName("Failed registration attempt with too short password")
    @Story("Testing of unsuccessful Registration Without Filling All Fields")
    void unsuccessfulRegistrationWithTooShortPasswordTest() {
        registrationPage.clickSkipButton()
                .clickRegistrationButton()
                .setUsername(testData.randomUsername)
                .setEmail(testData.email)
                .setPassword(testData.shortPassword)
                .setConfirmPassword(testData.shortPassword)
                .clickConfirmRegistrationButton()
                .verifyMessageTextView("Your password has to be at least 8 characters long");
    }
}

