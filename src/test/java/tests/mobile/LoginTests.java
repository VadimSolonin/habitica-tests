package tests.mobile;

import driver.LocalDeviceDriver;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.id;

public class LoginTests extends TestBase {

    @Tag("habiticaAndroid")
    @Test
    void verifyMainPageDescriptionTest() {
        step("Проверить, что на вкладке `Explore` есть ", () -> {
            $(id("com.habitrpg.android.habitica:id/descriptionTextView")).shouldHave(text("It’s time to have fun while you get things done. Join over 2,000,000 others improving their life one task at a time."));
        });

    }

    @Tag("habiticaAndroid")
    @Test
    void loginWithoutFillingAllFieldsTest() {
        $(id("com.habitrpg.android.habitica:id/skipButton")).click();
        step("Проверить, что на вкладке `Explore` есть ", () -> {
            $(id("com.habitrpg.android.habitica:id/new_game_button")).click();
            $(id("com.habitrpg.android.habitica:id/login_btn")).click();
            $(id("com.habitrpg.android.habitica:id/messageTextView")).shouldHave(text("You have to fill out all fields."));
        });
    }


    @Tag("habiticaAndroid")
    @Test
    void verifyNavMoreContainers1() {
        $(id("com.habitrpg.android.habitica:id/skipButton")).click();
        step("Проверить, что на вкладке `Explore` есть ", () -> {
            $(id("com.habitrpg.android.habitica:id/new_game_button")).click();
           $(id("com.habitrpg.android.habitica:id/username")).sendKeys("111");
           $(id("com.habitrpg.android.habitica:id/email")).sendKeys("1");
           $(id("com.habitrpg.android.habitica:id/password")).sendKeys("111");
           $(id("com.habitrpg.android.habitica:id/confirm_password")).sendKeys("111");
           $(id("com.habitrpg.android.habitica:id/login_btn")).click();
           $(id("com.habitrpg.android.habitica:id/messageTextView")).shouldHave(text("Your password has to be at least 8 characters long"));
        });
    }
}
