package tests.api;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestData;
import tests.api.models.BadRequestLoginResponseModel;
import tests.api.models.LoginRequestModel;
import tests.api.models.LoginResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tests.api.specs.ReqResSpec.requestSpec;
import static tests.api.specs.ReqResSpec.responseSpec;

@Owner("VS")
@Tag("api")
public class LoginTests extends TestBase {
    TestData testData = new TestData();

    @Test
    void successfulLoginTest() {
        LoginRequestModel loginData = new LoginRequestModel(testData.username, testData.password);

        LoginResponseModel response =
                step("Выполнить post-запрос на логин и зафиксировать ответ", () ->
                        given(requestSpec)
                                .contentType("application/json")
                                .body(loginData)
                                .when()
                                .post("/v4/user/auth/local/login")
                                .then()
                                .spec(responseSpec)
                                .statusCode(200)
                                .extract().as(LoginResponseModel.class));
        step("Проверить успешность выполнения запроса", () -> {
            assertTrue(response.getSuccess());
        });

    }

    @Test
    void loginWithEmptyPasswordTest() {
        LoginRequestModel loginData = new LoginRequestModel(testData.username, "");

        BadRequestLoginResponseModel response =
                step("Выполнить post-запрос на логин и зафиксировать ответ", () ->
                        given(requestSpec)
                                .contentType("application/json")
                                .body(loginData)
                                .when()
                                .post("/v4/user/auth/local/login")
                                .then()
                                .spec(responseSpec)
                                .statusCode(400)
                                .extract().as(BadRequestLoginResponseModel.class));
        step("Проверить данные из ответа", () -> {
            assertEquals("Missing password.", response.getErrors().get(0).getMessage());
        });
    }

    @Test
    void loginWithEmptyBody() {
        BadRequestLoginResponseModel response =
                step("Выполнить post-запрос на логин и зафиксировать ответ", () ->
                        given(requestSpec)
                                .contentType("application/json")
                                .body("")
                                .when()
                                .post("/v4/user/auth/local/login")
                                .then()
                                .spec(responseSpec)
                                .statusCode(400)
                                .extract().as(BadRequestLoginResponseModel.class));
        step("Проверить данные из ответа", () -> {
            assertEquals("Invalid request parameters.", response.getMessage());
            assertEquals("Missing username or email.", response.getErrors().get(0).getMessage());
            assertEquals("username", response.getErrors().get(0).getParam());
            assertEquals("Missing password.", response.getErrors().get(1).getMessage());
            assertEquals("password", response.getErrors().get(1).getParam());
        });
    }
}