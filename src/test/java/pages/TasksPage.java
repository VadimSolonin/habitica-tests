package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TasksPage {

    ElementsCollection navBarItem = $$(".topbar-item");
    SelenideElement tasksList = $(".habit").$(".sortable-tasks");
    SelenideElement workspaceContainer = $(".col-12");


    public TasksPage openPage(String pageAddress) {
        open(pageAddress);
        return this;
    }

    public TasksPage hoverNavBarItem(String navBarText) {
        navBarItem.findBy(text(navBarText)).hover();
        return this;
    }

    public TasksPage clickNavDropdownItem(String dropdownItemName) {
        $x("//a[@class='topbar-dropdown-item dropdown-item'][contains(text(),'" + dropdownItemName + "')]").click();
        return this;
    }

    public TasksPage addHabit(String addHabitPlaceholder, String habitName) {
        $x("//textarea[@class='quick-add'][@placeholder='" + addHabitPlaceholder + "']").setValue(habitName).pressEnter();
        return this;
    }

    public TasksPage checkHabitVisibility(String habitName) {
        tasksList.$(byText(habitName)).shouldBe(visible);
        return this;
    }

    public TasksPage verifyHabitsColumnVisibility(String habitName) {
        workspaceContainer.$(withText(habitName)).shouldBe(visible);
        return this;
    }
}
