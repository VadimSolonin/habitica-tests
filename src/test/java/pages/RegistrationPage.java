package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.id;

public class RegistrationPage {

    private SelenideElement skipButton = $(id("com.habitrpg.android.habitica:id/skipButton")),
            registrationButton = $(id("com.habitrpg.android.habitica:id/new_game_button")),
            usernameField = $(id("com.habitrpg.android.habitica:id/username")),
            emailField = $(id("com.habitrpg.android.habitica:id/email")),
            passwordField = $(id("com.habitrpg.android.habitica:id/password")),
            confirmPasswordField = $(id("com.habitrpg.android.habitica:id/confirm_password")),
            confirmRegistrationButton = $(id("com.habitrpg.android.habitica:id/login_btn")),
            messageTextView = $(id("com.habitrpg.android.habitica:id/messageTextView"));


    @Step("Click the button to skip the welcome window")
    public RegistrationPage clickSkipButton() {
        skipButton.click();
        return this;
    }

    @Step("Click on the `Registration` button")
    public RegistrationPage clickRegistrationButton() {
        registrationButton.click();
        return this;
    }

    @Step("Set Username")
    public RegistrationPage setUsername(String username) {
        usernameField.sendKeys(username);
        return this;
    }
    @Step("Set email")
    public RegistrationPage setEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    @Step("Set password")
    public RegistrationPage setPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Confirm password")
    public RegistrationPage setConfirmPassword(String password) {
        confirmPasswordField.sendKeys(password);
        return this;
    }

    @Step("Click on the `Registration` button")
    public RegistrationPage clickConfirmRegistrationButton() {
        confirmRegistrationButton.click();
        return this;
    }

    @Step("Verify that a message appears indicating that the email field is invalid.")
    public RegistrationPage verifyMessageTextView(String messageText) {
        messageTextView.shouldHave(text(messageText));
        return this;
    }
}
