package tests.api;

import models.EnvironmentModels;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class EnvironmentsTests extends BaseApiTest {

    @Test(description = "This method allows to retrieve all environments stored in selected project.", priority = 1)
    public void getAllEnvironments() {
        spec
                .when()
                .get(BASE_URL_QASE + "/environment/QP")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Test(description = "This method allows to create an environment in selected project.", priority = 2)
    public void createNewEnvironment() {
        EnvironmentModels environmentModels = EnvironmentModels.builder()
                .title("Тестовый сценарий1")
                .description("Тестовый сценарий1")
                .slug("Тестовый сценарий1")
                .host("Тестовый сценарий1")
                .build();

        spec
                .body(gson.toJson(environmentModels))
                .when()
                .post(BASE_URL_QASE + "/environment/QP")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Test(description = "This method allows to retrieve a specific environment.", priority = 3)
    public void getSpecificEnvironment() {
        spec
                .when()
                .get(BASE_URL_QASE + "/environment/QP/8")
                .then()
                .log().all()
                .statusCode(200)
                .body("result.id", equalTo(8));
    }

    @Test(description = "This method completely deletes an environment from repository.", priority = 4)
    public void deleteEnvironment() {
        spec
                .when()
                .delete(BASE_URL_QASE + "/environment/QP/8")
                .then()
                .log().all()
                .statusCode(200)
                .body("result.id", equalTo(8));
    }

    @Test(description = "This method updates an environment.", priority = 5)
    public void updateEnvironment() {
        EnvironmentModels environmentModels = EnvironmentModels.builder()
                .title("Тестовый сценарий №1")
                .description("Тестовый сценарий №1")
                .slug("Тестовый сценарий №1")
                .host("Тестовый сценарий №1")
                .build();

        spec
                .body(gson.toJson(environmentModels))
                .when()
                .patch(BASE_URL_QASE + "/environment/QP/8")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }
}
