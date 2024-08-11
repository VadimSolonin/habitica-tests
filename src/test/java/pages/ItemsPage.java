package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class ItemsPage {
    private SelenideElement specialFilterCheckbox = $("#special").sibling(0),
            itemsTab = $(".standard-page"),
            itemNameInputSearch = $(".form-control");
    private ElementsCollection ItemTitleCount = $$(".standard-page div h2");

    @Step("Open home page")
    public ItemsPage openPage(String pageAddress) {
        open(pageAddress);
        return this;
    }

    @Step("Click on the value 'Special' in the filter")
    public ItemsPage clickSpecialFilterCheckbox() {
        specialFilterCheckbox.click();
        return this;
    }

    @Step("Check that the number of items on the page is one")
    public ItemsPage checkItemTitleCount(int titleCount) {
        ItemTitleCount.shouldHave(size(titleCount));
        return this;
    }

    @Step("Make sure the page displays a special element")
    public ItemsPage checkSpecialItemVisibility(String titleText) {
        itemsTab.$(withText(titleText)).shouldBe(visible);
        return this;
    }

    @Step("Enter the value `Item` in the search field")
    public ItemsPage setItemSearchName(String itemName) {
        itemNameInputSearch.setValue(itemName);
        return this;
    }
}
