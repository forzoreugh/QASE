package tests.api;

import models.RunModels;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class RunsTests extends BaseApiTest {

    @Test(description = "This method allows to retrieve all runs stored in selected project.", priority = 1)
    public void getAllRuns() {
        spec
                .when()
                .get(BASE_URL_QASE + "/run/QP")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Test(description = "This method allows to create a run in selected project.", priority = 2)
    public void createNewRun() {
        RunModels runModels = RunModels.builder()
                .title("Test")
                .description("Test")
                .includeAllCases(true)
                .isAutotest(true)
                .build();

        spec
                .body(runModels)
                .when()
                .get(BASE_URL_QASE + "/run/QP")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Test(description = "This method allows to retrieve a specific run.", priority = 3)
    public void getSpecificRun() {
        spec
                .when()
                .get(BASE_URL_QASE + "/run/QP/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("result.id", equalTo(1));
    }

    @Test(description = "This method completely deletes a run from repository.", priority = 4)
    public void deleteRun() {
        spec
                .when()
                .delete(BASE_URL_QASE + "/run/QP/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }
}
