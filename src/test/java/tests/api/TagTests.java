package tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestData;
import tests.api.api.AuthorizationApi;
import tests.api.models.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tests.api.specs.ReqResSpec.requestSpec;
import static tests.api.specs.ReqResSpec.responseSpec;

@Feature("Tags testing")
@Tag("api")
public class TagTests extends TestBase {

    TestData testData = new TestData();
    LoginRequestModel loginRequestModel = new LoginRequestModel(testData.username, testData.password);
    AuthorizationApi authorizationApi = new AuthorizationApi();
    LoginResponseModel loginResponse = authorizationApi.login(loginRequestModel);

    @Test
    @DisplayName("Querying the current list of tags")
    @Story("Verifying successful completion of a request for a list of tags using a GET request")
    void checkCurrentTagsListTest() {
            GetTagsResponseModel response = step("Query a list of tags and capture the response", () ->
                given(requestSpec)
                        .when()
                        .header("X-Api-User", loginResponse.getData().getId())
                        .header("X-Api-Key", loginResponse.getData().getApiToken())
                        .get("/v3/tags")
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .extract().as(GetTagsResponseModel.class));
        step("Check the success of the request and its data", () -> {
            assertTrue(response.getSuccess());
            assertEquals("Работа", response.getData().get(0).getName());
        });
    }

    @Test
    @DisplayName("Performing a tag removal request")
    @Story("Verify successful completion of a GET request to remove a tag")
    void deleteExistingTag() {
        AddTagRequestModel addTag = new AddTagRequestModel(testData.programmingLanguage);

        TagResponseModel postResponse = step("Add a new tag using POST", () ->
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
        step("Check the success of the add request", () -> {
            assertTrue(postResponse.getSuccess());
        });

        TagResponseModel deleteResponse = step("Remove a previously added tag using a DELETE request", () ->
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
        step("Verify the success of the deletion request", () -> {
            assertTrue(deleteResponse.getSuccess());
        });
    }
}
