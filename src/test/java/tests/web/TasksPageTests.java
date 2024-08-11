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
import tests.api.api.UserTasksApi;
import tests.api.extensions.WithLogin;
import utils.Helpers;

@Feature("Tasks page testing")
@Tag("web")
public class TasksPageTests extends TestBase {
    TestData testData = new TestData();
    TasksPage tasksPage = new TasksPage();
    UserTasksApi userTasksApi = new UserTasksApi();

    @CsvFileSource(resources = "/inventoryTabTitles.csv")
    @ParameterizedTest
    @WithLogin
    @DisplayName("Check inventory tab links and tab titles")
    @Story("Testing of opening navigation drop-down menu item and verifying page title")
    public void verifyInventoryTabTitlesTest(String navElement, String title) {
        tasksPage.openPage("")
                .verifyHabitsColumnVisibility("Habits")
                .hoverNavBarItem("Inventory")
                .clickNavDropdownItem(navElement);
        Helpers.verifyPageTitle(title);
    }

    @Test
    @WithLogin
    @DisplayName("Quick add habit")
    @Story("Testing adding a habit to the list")
    public void quickAddHabitTest() {
        tasksPage.openPage("")
                .addHabit("Add a Habit", "Read " + testData.randomAuthor + "'s book")
                .checkHabitVisibility("Read " + testData.randomAuthor + "'s book");
        String taskId = userTasksApi.getUserTasks().getData().get(0).getId();
        userTasksApi.deleteUserTask(taskId);
    }
}
