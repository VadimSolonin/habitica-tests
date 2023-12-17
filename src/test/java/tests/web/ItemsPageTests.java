package tests.web;

import org.junit.jupiter.api.Test;
import pages.ItemsPage;
import tests.api.extensions.WithLogin;

import static io.qameta.allure.Allure.step;

public class ItemsPageTests extends TestBase {

    ItemsPage itemsPage = new ItemsPage();

    @Test
    @WithLogin
    public void verifyMapSpecialItemTest() {
        step("Открыть начальную страницу", () -> {
            itemsPage.openPage("inventory/items");
        });
        step("Кликнуть на фильтр `Особые`", () -> {
            itemsPage.clickSpecialFilterCheckbox();
        });
        step("Проверить, что количество предметов на странице равно одному", () -> {
            itemsPage.checkItemTitleCount(1);
        });
        step("Проверить, что на странице отображается особый предмет", () -> {
            itemsPage.checkSpecialItemVisibility("Особые");
        });
    }

    @Test
    @WithLogin
    public void searchItemInInventoryTest() {
        step("Открыть начальную страницу", () -> {
            itemsPage.openPage("inventory/items");
        });
        step("Открыть начальную страницу", () -> {
            itemsPage.setItemSearchName("Предмет");
        });
        step("Проверить, что на странице отображается особый предмет", () -> {
            itemsPage.checkSpecialItemVisibility("Особые");
        });
    }
}
