package tests.web;

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

@Tag("web")
public class TasksPageTests extends TestBase {
    TestData testData = new TestData();
    TasksPage tasksPage = new TasksPage();

    @CsvFileSource(resources = "/inventoryTabTitles.csv")
    @ParameterizedTest
    @WithLogin
    public void verifyInventoryTabTitlesTest(String navElement, String title) {
        step("Открыть начальную страницу", () -> {
            tasksPage.openPage("");
        });
        step("Эмулируем наведение мышкой на элемент без клика", () -> {
            tasksPage.hoverNavBarItem("Inventory");
        });
        step("Кликнуть на элемент выпадающего меню в навигационном меню", () -> {
            tasksPage.clickNavDropdownItem(navElement);
        });
        step("Проверить заголовок страницы", () -> {
            Helpers.verifyPageTitle(title);
        });

    }

    @Test
    @WithLogin
    public void quickAddHabitTest() {
        step("Открыть начальную страницу", () -> {
            tasksPage.openPage("");
        });
        step("Добавить привычку в список", () -> {
            tasksPage.addHabit("Add a Habit", "Read " + testData.randomAuthor + "'s book");
        });
        step("Убедиться, что привычка добавлена в список", () -> {
            tasksPage.checkHabitVisibility("Read " + testData.randomAuthor + "'s book");
        });
        step("Удалить добавленную привычку средствами api", () -> {
            UserTasksApi userTasksApi = new UserTasksApi();
            String taskId = userTasksApi.getUserTasks().getData().get(0).getId();
            userTasksApi.deleteUserTask(taskId);
        });
    }

}
