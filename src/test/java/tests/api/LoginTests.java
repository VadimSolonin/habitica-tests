package tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
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

@Feature("Authorization testing")
@Tag("api")
public class LoginTests extends TestBase {
    TestData testData = new TestData();

    @Test
    @DisplayName("Making a successful login request")
    @Story("Verifying successful login using POST method")
    void successfulLoginTest() {
        LoginRequestModel loginData = new LoginRequestModel(testData.username, testData.password);

        LoginResponseModel response =
                step("Execute a post-request for login and record the response", () ->
                        given(requestSpec)
                                .contentType("application/json")
                                .body(loginData)
                                .when()
                                .post("/v4/user/auth/local/login")
                                .then()
                                .spec(responseSpec)
                                .statusCode(200)
                                .extract().as(LoginResponseModel.class));
        step("Check the success of the request", () -> {
            assertTrue(response.getSuccess());
        });

    }

    @Test
    @DisplayName("Making unsuccessful login request with an empty password")
    @Story("Verifying unsuccessful login using POST method with an empty password")
    void loginWithEmptyPasswordTest() {
        LoginRequestModel loginData = new LoginRequestModel(testData.username, "");

        BadRequestLoginResponseModel response =
                step("Execute a post-request for login and record the response", () ->
                        given(requestSpec)
                                .contentType("application/json")
                                .body(loginData)
                                .when()
                                .post("/v4/user/auth/local/login")
                                .then()
                                .spec(responseSpec)
                                .statusCode(400)
                                .extract().as(BadRequestLoginResponseModel.class));
        step("Check the message about missing password from the response", () -> {
            assertEquals("Missing password.", response.getErrors().get(0).getMessage());
        });
    }

    @Test
    @DisplayName("Performing a login request with an empty body")
    @Story("Verifying unsuccessful login using POST method an empty body")
    void loginWithEmptyBody() {
        BadRequestLoginResponseModel response =
                step("Execute a post-request for login and record the response", () ->
                        given(requestSpec)
                                .contentType("application/json")
                                .body("")
                                .when()
                                .post("/v4/user/auth/local/login")
                                .then()
                                .spec(responseSpec)
                                .statusCode(400)
                                .extract().as(BadRequestLoginResponseModel.class));
        step("Check data from response", () -> {
            assertEquals("Invalid request parameters.", response.getMessage());
            assertEquals("Missing username or email.", response.getErrors().get(0).getMessage());
            assertEquals("username", response.getErrors().get(0).getParam());
            assertEquals("Missing password.", response.getErrors().get(1).getMessage());
            assertEquals("password", response.getErrors().get(1).getParam());
        });
    }
}