package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.TasksPage;
import tests.TestData;
import tests.api.apiResponses.UserTasksApi;
import tests.api.extensions.WithLogin;
import utils.Helpers;

import static io.qameta.allure.Allure.step;

@Feature("Tasks page testing")
@Tag("web")
public class TasksPageTests extends TestBase {
    TestData testData = new TestData();
    TasksPage tasksPage = new TasksPage();

    @CsvFileSource(resources = "/inventoryTabTitles.csv")
    @ParameterizedTest
    @WithLogin
    @DisplayName("Check inventory tab links and tab titles")
    @Story("Testing of opening navigation drop-down menu item and verifying page title")
    public void verifyInventoryTabTitlesTest(String navElement, String title) {
        step("Open home page", () -> {
            tasksPage.openPage("");
        });
        step("Make sure the page has loaded", () -> {
            tasksPage.verifyHabitsColumnVisibility("Habits");
        });
        step("Emulate mouse hovering over an element without clicking", () -> {
            tasksPage.hoverNavBarItem("Inventory");
        });
        step("Click on a drop-down menu item in the navigation menu", () -> {
            tasksPage.clickNavDropdownItem(navElement);
        });
        step("Check page title", () -> {
            Helpers.verifyPageTitle(title);
        });
    }

    @Test
    @WithLogin
    @DisplayName("Quick add habit")
    @Story("Testing adding a habit to the list")
    public void quickAddHabitTest() {
        step("Open home page", () -> {
            tasksPage.openPage("");
        });
        step("Add a habit to the list", () -> {
            tasksPage.addHabit("Add a Habit", "Read " + testData.randomAuthor + "'s book");
        });
        step("Make sure the habit is added to the list", () -> {
            tasksPage.checkHabitVisibility("Read " + testData.randomAuthor + "'s book");
        });
        step("Remove an added habit using the API", () -> {
            UserTasksApi userTasksApi = new UserTasksApi();
            String taskId = userTasksApi.getUserTasks().getData().get(0).getId();
            userTasksApi.deleteUserTask(taskId);
        });
    }
}
