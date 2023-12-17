package tests.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestData;
import tests.api.apiResponses.AuthorizationApi;
import tests.api.models.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tests.api.specs.ReqResSpec.requestSpec;
import static tests.api.specs.ReqResSpec.responseSpec;

@Tag("api")
public class TagTests extends TestBase {

    TestData testData = new TestData();
    LoginRequestModel loginRequestModel = new LoginRequestModel(testData.username, testData.password);
    AuthorizationApi authorizationApi = new AuthorizationApi();
    LoginResponseModel loginResponse = authorizationApi.login(loginRequestModel);

    @Test
    @DisplayName("Запрос текущего списка тегов")
    void checkCurrentTagsListTest() {
        GetTagsResponseModel response = step("Выполнить запрос списка тегов и зафиксировать ответ", () ->
                given(requestSpec)
                        .when()
                        .header("X-Api-User", loginResponse.getData().getId())
                        .header("X-Api-Key", loginResponse.getData().getApiToken())
                        .get("/v3/tags")
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .extract().as(GetTagsResponseModel.class));
        step("Проверить успешность выполнения запроса", () -> {
            assertTrue(response.getSuccess());
            assertEquals("Работа", response.getData().get(0).getName());
        });
    }

    @Test
    @DisplayName("Удаление тега")
    void deleteExistingTag() {
        AddTagRequestModel addTag = new AddTagRequestModel(testData.programmingLanguage);

        TagResponseModel postResponse = step("Добавить новый тег", () ->
                given(requestSpec)
                        .contentType("application/json")
                        .body(addTag)
                        .header("X-Api-User", loginResponse.getData().getId())
                        .header("X-Api-Key", loginResponse.getData().getApiToken())
                        .when()
                        .post("/v4/tags")
                        .then()
                        .spec(responseSpec)
                        .statusCode(201)
                        .extract().as(TagResponseModel.class));
        step("Проверить успешность выполнения запроса на добавление", () -> {
            assertTrue(postResponse.getSuccess());
        });

        TagResponseModel deleteResponse = step("Удалить ранее добавленный тег", () ->
                given(requestSpec)
                        .contentType("application/json")
                        .header("X-Api-Key", "81a07ee4-ba27-4ec4-9364-32264918424d")
                        .header("X-Api-User", "67c44fa3-14f9-4642-86e3-8714884b8c0e")
                        .when()
                        .delete("/v3/tags/" + postResponse.getData().getId())
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .extract().as(TagResponseModel.class));
        step("Проверить успешность выполнения запроса на удаление", () -> {
            assertTrue(deleteResponse.getSuccess());
        });
    }
}
