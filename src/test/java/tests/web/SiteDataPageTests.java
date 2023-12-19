package tests.web;

import org.junit.jupiter.api.Test;
import pages.SiteDataPage;
import pages.TasksPage;
import tests.api.extensions.WithLogin;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class SiteDataPageTests extends TestBase {
    TasksPage tasksPage = new TasksPage();
    SiteDataPage siteDataPage = new SiteDataPage();

    @Test
    @WithLogin
    public void verifyUserIdTooltipTextTest() {
        step("Open home page", () -> {
            open("");
        });
        step("Make sure the page has loaded", () -> {
            tasksPage.verifyHabitsColumnVisibility("Habits");
        });
        step("Click on the user menu button", () -> {
            tasksPage.clickUserButton();
        });
        step("Select the Settings link in the dropdown menu", () -> {
            tasksPage.clickUserDropdownElementButton("Settings");
        });
        step("Сlick on the Site Data tab in the SecondaryMenu", () -> {
            siteDataPage.clickSecondaryMenuElement("Site Data");
        });
        step("Emulate mouse hover over a tooltip and check its text", () -> {
            siteDataPage.hoverUserIdTooltip();
            siteDataPage.verifyUserIdTooltipText("The User ID is a unique number that Habitica automatically " +
                    "generates when a player joins, similar to a Username. However, unlike the Username, a User " +
                    "ID can not be changed.");
        });
    }
}
