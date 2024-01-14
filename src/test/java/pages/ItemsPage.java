package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class ItemsPage {
    private SelenideElement specialFilterCheckbox = $("#special").sibling(0),
            itemsTab = $(".standard-page"),
            itemNameInputSearch = $(".form-control");
    private ElementsCollection ItemTitleCount = $$(".standard-page div h2");

    public ItemsPage openPage(String pageAddress) {
        open(pageAddress);
        return this;
    }

    public ItemsPage clickSpecialFilterCheckbox() {
        specialFilterCheckbox.click();
        return this;
    }

    public ItemsPage checkItemTitleCount(int titleCount) {
        ItemTitleCount.shouldHave(size(titleCount));
        return this;
    }

    public ItemsPage checkSpecialItemVisibility(String titleText) {
        itemsTab.$(withText(titleText)).shouldBe(visible);
        return this;
    }

    public ItemsPage setItemSearchName(String itemName) {
        itemNameInputSearch.setValue(itemName);
        return this;
    }


}
