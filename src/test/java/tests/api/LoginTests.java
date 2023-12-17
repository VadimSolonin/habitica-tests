package tests.api;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import tests.TestData;
import tests.api.models.LoginRequestModel;
import tests.api.models.LoginResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tests.api.specs.ReqResSpec.requestSpec;
import static tests.api.specs.ReqResSpec.responseSpec;

@Owner("VS")
@Tag("api")
public class LoginTests extends TestBase {
TestData testData = new TestData();

    @Test
    @Tag("api")
    void loginTest() {
        LoginRequestModel loginData = new LoginRequestModel(testData.username, testData.password);

        LoginResponseModel response =
                step("Выполнение post-запроса и фиксация ответа", () ->
                        given(requestSpec)
                                .contentType("application/json")
                                .body(loginData)
                                .when()
                                .post("/v4/user/auth/local/login")
                                .then()
                                .spec(responseSpec)
                                .statusCode(200)
                                .extract().as(LoginResponseModel.class));
        step("Проверка успешности выполнения запроса", () -> {
            assertTrue(response.getSuccess());
        });

    }




}