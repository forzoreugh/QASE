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
                .title("Тестовый сценарий")
                .description("Тестовый сценарий")
                .slug("Тестовый сценарий")
                .host("Тестовый сценарий")
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
        spec
                .when()
                .get(BASE_URL_QASE + "/environment/ARTEM/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("result.id", equalTo(2));
    }

    @Test(description = "This method completely deletes an environment from repository.", priority = 4,
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

    @Test(description = "This method updates an environment.", priority = 5)
    public void updateEnvironment() {
        EnvironmentModels environmentModels = EnvironmentModels.builder()
                .title("Тестовый сценарий №2")
                .description("Тестовый сценарий №2")
                .slug("Тестовый сценарий №2")
                .host("Тестовый сценарий №2")
                .build();

        spec
                .body(gson.toJson(environmentModels))
                .when()
                .patch(BASE_URL_QASE + "/environment/ARTEM/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }
}
