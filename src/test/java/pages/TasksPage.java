package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TasksPage {

    private ElementsCollection navBarItem = $$(".topbar-item");
    private SelenideElement tasksList = $(".habit").$(".sortable-tasks"),
            workspaceContainer = $(".col-12"),
            userButton = $("#menu_collapse").$("[aria-label='User']"),
            userDropdownContainer = $("#menu_collapse").$(".user-dropdown");

    @Step("Open home page")
    public TasksPage openPage(String pageAddress) {
        open(pageAddress);
        return this;
    }

    @Step("Emulate mouse hovering over an element without clicking")
    public TasksPage hoverNavBarItem(String navBarText) {
        navBarItem.findBy(text(navBarText)).hover();
        return this;
    }

    @Step("Click on a drop-down menu item in the navigation menu")
    public TasksPage clickNavDropdownItem(String dropdownItemName) {
        $x("//a[@class='topbar-dropdown-item dropdown-item'][contains(text(),'" + dropdownItemName + "')]").click();
        return this;
    }

    @Step("Add a habit to the list")
    public TasksPage addHabit(String addHabitPlaceholder, String habitName) {
        $x("//textarea[@class='quick-add'][@placeholder='" + addHabitPlaceholder + "']").setValue(habitName).pressEnter();
        return this;
    }

    @Step("Make sure the habit is added to the list")
    public TasksPage checkHabitVisibility(String habitName) {
        tasksList.$(byText(habitName)).shouldBe(visible);
        return this;
    }

    @Step("Make sure the page has loaded")
    public TasksPage verifyHabitsColumnVisibility(String habitName) {
        workspaceContainer.$(withText(habitName)).shouldBe(visible);
        return this;
    }
    @Step("Click on the user menu button")
    public TasksPage clickUserButton() {
        userButton.click();
        return this;
    }
    @Step("Select the Settings link in the dropdown menu")
    public TasksPage clickUserDropdownElementButton(String element) {
        userDropdownContainer.$(byText(element)).shouldBe(visible).click();
        return this;
    }
}
