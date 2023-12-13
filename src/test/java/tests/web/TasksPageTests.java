package tests.web;

import org.junit.jupiter.api.Test;
import tests.api.extensions.WithLogin;
import utils.Helpers;

import static com.codeborne.selenide.Selenide.open;

public class TasksPageTests extends TestBase {


    @Test
    @WithLogin
    public void verifyTasksPageTitle() {
        open("inventory/items");
        Helpers.verifyPageTitle("Предметы | Инвентарь | Habitica");
    }

}
