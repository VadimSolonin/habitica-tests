package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    private SelenideElement singUpButton = $(".sign-up"),
            notificationHolder = $(".notification-holder");

    @Step("Open home page")
    public HomePage openPage(String pageAddress) {
        open(pageAddress);
        return this;
    }

    @Step("Click on the registration button")
    public HomePage clickSingUpButton() {
        singUpButton.click();
        return this;
    }

    @Step("Make sure that a message appears about missing name, password and email")
    public HomePage verifyNotificationText(String errorText) {
        notificationHolder.$(byText(errorText)).shouldBe(visible);
        return this;
    }

    @Step("Click on the start registration button")
    public HomePage clickStartNavLink(String linkText) {
        $x("//a[@class='nav-link'][contains(text(),'" + linkText + "')]").click();
        return this;
    }
}
