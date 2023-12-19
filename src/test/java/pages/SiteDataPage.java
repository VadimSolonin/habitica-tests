package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SiteDataPage {
    SelenideElement secondaryMenuContainer = $(".secondary-menu"),
                    userIdTooltip = $("#tooltip_userId");

    public SiteDataPage clickSecondaryMenuElement(String element) {
        secondaryMenuContainer.$(byText(element)).click();
        return this;
    }
    public SiteDataPage hoverUserIdTooltip() {
        userIdTooltip.hover();
        return this;
    }
    public SiteDataPage verifyUserIdTooltipText(String tooltipText) {
        userIdTooltip.shouldHave(attribute("data-original-title", tooltipText));
        return this;
    }
}
