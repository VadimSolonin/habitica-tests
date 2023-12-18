package tests.api.extensions;

import com.codeborne.selenide.Selenide;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import tests.TestData;
import tests.api.apiResponses.AuthorizationApi;
import tests.api.models.HabitMobileSettings;
import tests.api.models.LoginRequestModel;
import tests.api.models.LoginResponseModel;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LoginExtension implements BeforeEachCallback {
    TestData testData = new TestData();

    @Override
    public void beforeEach(ExtensionContext context) throws JsonProcessingException {
        LoginRequestModel loginRequestModel = new LoginRequestModel(testData.username, testData.password);
        AuthorizationApi authorizationApi = new AuthorizationApi();
        LoginResponseModel loginResponse = authorizationApi.login(loginRequestModel);

        HabitMobileSettings habitMobileSettings =
                new HabitMobileSettings(loginResponse.getData().getId(), loginResponse.getData().getApiToken());
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String result = mapper.writeValueAsString(habitMobileSettings);
        step("Авторизация при помощи заполнения localStorage", () -> {
            open("static/svg/cce576f817758fa4398dadf559199d47.svg");
            Selenide.localStorage().setItem("habit-mobile-settings", result);
        });

    }
}