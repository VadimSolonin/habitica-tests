package tests.mobile;

import com.codeborne.selenide.Configuration;
import driver.LocalDeviceDriver;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = LocalDeviceDriver.class.getName();
        Configuration.browserSize = null;
    }

    @BeforeEach
    void beforeEach() {
        //SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        //Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
//        if ("browserstack".equals(deviceHost)) {
//            Attach.addVideo(sessionId().toString());
//        }
        closeWebDriver();
    }
}
