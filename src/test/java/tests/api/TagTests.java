package tests.api;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.api.models.TagResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tests.api.specs.ReqResSpec.requestSpec;
import static tests.api.specs.ReqResSpec.responseSpec;

public class TagTests extends TestBase{

    @Test
    @Owner("VS")
    @DisplayName("Проверка тегов")
    void checkUsersDataOnPageTest() {
        TagResponseModel response = step("Запрос списка тегов", () ->
                given(requestSpec)
                        .when()
                        .header("X-Api-Key", "81a07ee4-ba27-4ec4-9364-32264918424d")
                        .header("X-Api-User", "67c44fa3-14f9-4642-86e3-8714884b8c0e")
                        .get("/v3/tags")
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .extract().as(TagResponseModel.class));
        step("Проверка успешности выполнения запроса", () -> {
            assertTrue(response.getSuccess());
        });
//        step("Проверка данных о первом объекте из ключа data в ответе", () -> {
//            List<UserListResponseModel.DataList> data = response.getData();
//            assertEquals(7, data.get(0).getId());
//            assertEquals("michael.lawson@reqres.in", data.get(0).getEmail());
//            assertEquals("Michael", data.get(0).getFirstName());
//            assertEquals("Lawson", data.get(0).getLastName());
//            assertEquals("https://reqres.in/img/faces/7-image.jpg", data.get(0).getAvatar());
//        });
    }
}
