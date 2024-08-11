package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.SiteDataPage;
import pages.TasksPage;
import tests.api.extensions.WithLogin;

import static com.codeborne.selenide.Selenide.open;

@Feature("Site data page testing")
@Tag("web")
public class SiteDataPageTests extends TestBase {
    TasksPage tasksPage = new TasksPage();
    SiteDataPage siteDataPage = new SiteDataPage();

    @Test
    @WithLogin
    @DisplayName("Successful user ID tooltip text validation")
    @Story("Checking that tooltip text appears when you hover the mouse over the user ID button")
    public void verifyUserIdTooltipTextTest() {
        open("");
        tasksPage.verifyHabitsColumnVisibility("Habits")
                .clickUserButton()
                .clickUserDropdownElementButton("Settings");
        siteDataPage.clickSecondaryMenuElement("Site Data")
                .hoverUserIdTooltip()
                .verifyUserIdTooltipText("The User ID is a unique number that Habitica automatically " +
                        "generates when a player joins, similar to a Username. However, unlike the Username, a User " +
                        "ID can not be changed.");
    }
}
