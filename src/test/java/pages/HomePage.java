package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    private SelenideElement singUpButton = $(".sign-up"),
            notificationHolder = $(".notification-holder");

    public HomePage openPage(String pageAddress) {
        open(pageAddress);
        return this;
    }

    public HomePage clickSingUpButton() {
        singUpButton.click();
        return this;
    }

    public HomePage verifyNotificationText(String errorText) {
        notificationHolder.$(byText(errorText)).shouldBe(visible);
        return this;
    }

    public HomePage clickStartNavLink(String linkText) {
        $x("//a[@class='nav-link'][contains(text(),'" + linkText + "')]").click();
        return this;
    }
}
