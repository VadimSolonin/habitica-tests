package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ItemsPage;
import tests.api.extensions.WithLogin;

@Feature("Items page testing")
@Tag("web")
public class ItemsPageTests extends TestBase {

    ItemsPage itemsPage = new ItemsPage();

    @Test
    @WithLogin
    @DisplayName("Successful rendering of a special item when it is selected in the filter")
    @Story("Testing that when you select a special item in the filter, it is displayed on the page")
    public void verifyMapSpecialItemTest() {
        itemsPage.openPage("inventory/items")
                .clickSpecialFilterCheckbox()
                .checkItemTitleCount(1)
                .checkSpecialItemVisibility("Special");
    }

    @Test
    @WithLogin
    @DisplayName("Successfully rendering a special element when searching for it in the search bar")
    @Story("Testing that when you enter text from the description of a special element into the search bar, it is displayed on the page")
    public void searchItemInInventoryTest() {
        itemsPage.openPage("inventory/items")
                .setItemSearchName("Item")
                .checkSpecialItemVisibility("Special");
    }
}
