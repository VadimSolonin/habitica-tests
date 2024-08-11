package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SiteDataPage {
    private SelenideElement secondaryMenuContainer = $(".secondary-menu"),
                    userIdTooltip = $("#tooltip_userId");

    @Step("Сlick on the Site Data tab in the SecondaryMenu")
    public SiteDataPage clickSecondaryMenuElement(String element) {
        secondaryMenuContainer.$(byText(element)).click();
        return this;
    }
    @Step("Emulate mouse hover over a tooltip")
    public SiteDataPage hoverUserIdTooltip() {
        userIdTooltip.hover();
        return this;
    }
    @Step("Check tooltip text")
    public SiteDataPage verifyUserIdTooltipText(String tooltipText) {
        userIdTooltip.shouldHave(attribute("data-original-title", tooltipText));
        return this;
    }
}
