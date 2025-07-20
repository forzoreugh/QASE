package tests.api;

import io.restassured.response.Response;
import models.EnvironmentModels;
import models.EnvironmentTestData;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class EnvironmentsTests extends BaseApiTest {

    @Test(description = "This method allows to retrieve all environments stored in selected project.", priority = 1)
    public void getAllEnvironments() {
        spec
                .when()
                .get(BASE_URL_QASE + "/environment/ARTEM")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Test(description = "This method allows to create an environment in selected project.", priority = 2)
    public void createNewEnvironment() {
        EnvironmentModels environmentModels = EnvironmentModels.builder()
                .title("Тестовый сценарий6")
                .description("Тестовый сценарий6")
                .slug("Тестовый сценарий6")
                .host("Тестовый сценарий6")
                .build();

        Response response = spec
                .body(gson.toJson(environmentModels))
                .when()
                .post(BASE_URL_QASE + "/environment/ARTEM")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true))
                .extract().response();

        EnvironmentTestData.createdEnvironmentId = response.path("result.id");
    }

    @Test(description = "This method allows to retrieve a specific environment.", priority = 3)
    public void getSpecificEnvironment() {

        int environmentId = EnvironmentTestData.createdEnvironmentId;

        spec
                .when()
                .get(BASE_URL_QASE + "/environment/ARTEM/" + environmentId)
                .then()
                .log().all()
                .statusCode(200)
                .body("result.id", equalTo(environmentId));
    }

    @Test(description = "This method completely deletes an environment from repository.", priority = 5,
            dependsOnMethods = "createNewEnvironment")
    public void deleteEnvironment() {

        int environmentId = EnvironmentTestData.createdEnvironmentId;

        spec
                .when()
                .delete(BASE_URL_QASE + "/environment/ARTEM/" + environmentId)
                .then()
                .log().all()
                .statusCode(200)
                .body("result.id", equalTo(environmentId));
    }

    @Test(description = "This method updates an environment.", priority = 4)
    public void updateEnvironment() {
        EnvironmentModels environmentModels = EnvironmentModels.builder()
                .title("Тестовый сценарий №3")
                .description("Тестовый сценарий №3")
                .slug("Тестовый сценарий №3")
                .host("Тестовый сценарий №3")
                .build();

        int environmentId = EnvironmentTestData.createdEnvironmentId;

        spec
                .body(gson.toJson(environmentModels))
                .when()
                .patch(BASE_URL_QASE + "/environment/ARTEM/" + environmentId)
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }
}
