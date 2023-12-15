package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.id;

public class RegistrationPage {

    SelenideElement skipButton = $(id("com.habitrpg.android.habitica:id/skipButton")),
            registrationButton = $(id("com.habitrpg.android.habitica:id/new_game_button")),
            usernameField = $(id("com.habitrpg.android.habitica:id/username")),
            emailField = $(id("com.habitrpg.android.habitica:id/email")),
            passwordField = $(id("com.habitrpg.android.habitica:id/password")),
            confirmPasswordField = $(id("com.habitrpg.android.habitica:id/confirm_password")),
            confirmRegistrationButton = $(id("com.habitrpg.android.habitica:id/login_btn")),
            messageTextView = $(id("com.habitrpg.android.habitica:id/messageTextView"));


    public RegistrationPage clickSkipButton() {
        skipButton.click();
        return this;
    }

    public RegistrationPage clickRegistrationButton() {
        registrationButton.click();
        return this;
    }

    public RegistrationPage setUsername(String username) {
        usernameField.sendKeys(username);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public RegistrationPage setPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public RegistrationPage setConfirmPassword(String password) {
        confirmPasswordField.sendKeys(password);
        return this;
    }

    public RegistrationPage clickConfirmRegistrationButton() {
        confirmRegistrationButton.click();
        return this;
    }

    public RegistrationPage verifyMessageTextView(String messageText) {
        messageTextView.shouldHave(text(messageText));
        return this;
    }
}
