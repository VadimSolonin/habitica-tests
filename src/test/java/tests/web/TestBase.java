package tests.web;

import com.codeborne.selenide.Configuration;
import config.WebConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());
        Configuration.baseUrl = "https://habitica.com/";
        RestAssured.baseURI = "https://habitica.com/";
        RestAssured.basePath = "/api";
        Configuration.browser = webConfig.getBrowserName();
        Configuration.browserVersion = webConfig.getBrowserVersion();
        Configuration.remote = webConfig.getRemoteUrl();
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }
}
