package tests.api;

import io.restassured.response.Response;
import models.CaseModels;
import models.CaseTestData;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class CasesTests extends BaseApiTest {

    @Test(description = "This method allows to retrieve all test cases stored in selected project.", priority = 1)
    public void getAllTestCases() {
        spec
                .when()
                .get(BASE_URL_QASE + "/case/ARTEM")
                .then()
                .log().all()
                .statusCode(200)
                .body("result.entities[0].title", equalTo("Тестовый тест-кейс №1"));
    }

    @Test(description = "This method allows to create a new test case in selected project.", priority = 2)
    public void createNewTestCase() {
        CaseModels caseModels = CaseModels.builder()
                .description("Тестовое описание")
                .preconditions("Тестовое предусловие")
                .postconditions("Тестовое постусловие")
                .title("Тестовый тест-кейс")
                .severity(3)
                .priority(3)
                .behavior(3)
                .build();

        Response response = spec
                .body(gson.toJson(caseModels))
                .when()
                .post(BASE_URL_QASE + "/case/ARTEM")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true))
                .extract().response();

        CaseTestData.createdCaseId = response.path("result.id");
    }

    @Test(description = "This method allows to retrieve a specific test case.", priority = 3)
    public void getSpecificTestCase() {
        spec
                .when()
                .get(BASE_URL_QASE + "/case/ARTEM/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("result.title", equalTo("Тестовый тест-кейс №1"));
    }

    @Test(description = "This method completely deletes a test case from repository.", priority = 4,
            dependsOnMethods = "createNewTestCase")
    public void deleteTestCase() {

        int caseId = CaseTestData.createdCaseId;

        spec
                .when()
                .delete(BASE_URL_QASE + "/case/ARTEM/" + caseId)
                .then()
                .log().all()
                .statusCode(200)
                .body("result.id", equalTo(caseId));
    }

    @Test(description = "This method updates a test case.", priority = 5)
    public void updateTestCase() {
        CaseModels caseModels = CaseModels.builder()
                .description("Тестовое описание для тест-кейса")
                .preconditions("Тестовое предусловие для тест-кейса")
                .postconditions("Тестовое постусловие для тест-кейса")
                .title("Тестовый тест-кейс №1")
                .severity(2)
                .priority(2)
                .behavior(2)
                .type(2)
                .layer(2)
                .automation(2)
                .status(2)
                .build();

        spec
                .body(gson.toJson(caseModels))
                .when()
                .patch(BASE_URL_QASE + "/case/ARTEM/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }
}
