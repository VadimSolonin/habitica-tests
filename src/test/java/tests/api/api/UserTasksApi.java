package tests.api.api;

import tests.TestData;
import tests.api.models.DeleteUserTaskResponseModel;
import tests.api.models.LoginRequestModel;
import tests.api.models.LoginResponseModel;
import tests.api.models.UserTasksResponseModel;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static tests.api.specs.ReqResSpec.requestSpec;
import static tests.api.specs.ReqResSpec.responseSpec;

public class UserTasksApi {
    TestData testData = new TestData();
    LoginRequestModel loginRequestModel = new LoginRequestModel(testData.username, testData.password);
    AuthorizationApi authorizationApi = new AuthorizationApi();
    LoginResponseModel loginResponse = authorizationApi.login(loginRequestModel);


    public UserTasksResponseModel getUserTasks() {
        return given(requestSpec)
                .contentType(JSON)
                .header("X-Api-User", loginResponse.getData().getId())
                .header("X-Api-Key", loginResponse.getData().getApiToken())
                .when()
                .get("v4/tasks/user")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(UserTasksResponseModel.class);
    }

    public DeleteUserTaskResponseModel deleteUserTask(String taskId) {
        return given(requestSpec)
                .contentType(JSON)
                .header("X-Api-User", loginResponse.getData().getId())
                .header("X-Api-Key", loginResponse.getData().getApiToken())
                .when()
                .delete("v4/tasks/" + taskId)
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(DeleteUserTaskResponseModel.class);
    }
}
