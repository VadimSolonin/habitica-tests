package tests.api;

import com.codeborne.selenide.Configuration;
import config.WebConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());

        RestAssured.baseURI = "https://habitica.com/";
        RestAssured.basePath = "/api";
        Configuration.baseUrl = "https://habitica.com/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = webConfig.getRemoteUrl();
    }
}
