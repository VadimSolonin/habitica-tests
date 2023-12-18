package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ItemsPage;
import tests.api.extensions.WithLogin;

import static io.qameta.allure.Allure.step;

@Feature("Items page testing")
@Tag("web")
public class ItemsPageTests extends TestBase {

    ItemsPage itemsPage = new ItemsPage();

    @Test
    @WithLogin
    @DisplayName("Successful rendering of a special item when it is selected in the filter")
    @Story("Testing that when you select a special item in the filter, it is displayed on the page")
    public void verifyMapSpecialItemTest() {
        step("Open home page", () -> {
            itemsPage.openPage("inventory/items");
        });
        step("Click on the value 'Special' in the filter", () -> {
            itemsPage.clickSpecialFilterCheckbox();
        });
        step("Check that the number of items on the page is one", () -> {
            itemsPage.checkItemTitleCount(1);
        });
        step("Make sure the page displays a special element", () -> {
            itemsPage.checkSpecialItemVisibility("Special");
        });
    }

    @Test
    @WithLogin
    @DisplayName("Successfully rendering a special element when searching for it in the search bar")
    @Story("Testing that when you enter text from the description of a special element into the search bar, it is displayed on the page")
    public void searchItemInInventoryTest() {
        step("Open home page", () -> {
            itemsPage.openPage("inventory/items");
        });
        step("Enter the value `Item` in the search field", () -> {
            itemsPage.setItemSearchName("Item");
        });
        step("Make sure the page displays a special element", () -> {
            itemsPage.checkSpecialItemVisibility("Special");
        });
    }
}
