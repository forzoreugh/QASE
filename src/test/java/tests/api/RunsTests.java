package tests.api;

import io.restassured.response.Response;
import models.MilestoneTestData;
import models.ProjectTestData;
import models.RunModels;
import models.RunsTestData;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class RunsTests extends BaseApiTest {

    @Test(description = "This method allows to retrieve all runs stored in selected project.", priority = 1)
    public void getAllRuns() {
        spec
                .when()
                .get(BASE_URL_QASE + "/run/ARTEM")
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
                .cases(List.of(2))
                .build();

        Response response = spec
                .body(runModels)
                .when()
                .post(BASE_URL_QASE + "/run/ARTEM")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true))
                .extract().response();

        RunsTestData.createdRunsId = response.path("result.id");
    }

    @Test(description = "This method allows to retrieve a specific run.", priority = 3)
    public void getSpecificRun() {

        int runsId = RunsTestData.createdRunsId;

        spec
                .when()
                .get(BASE_URL_QASE + "/run/ARTEM/" + runsId)
                .then()
                .log().all()
                .statusCode(200)
                .body("result.id", equalTo(runsId));
    }

    @Test(description = "This method completely deletes a run from repository.", priority = 4)
    public void deleteRun() {

        int runsId = RunsTestData.createdRunsId;

        spec
                .when()
                .delete(BASE_URL_QASE + "/run/ARTEM/" + runsId)
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }
}
