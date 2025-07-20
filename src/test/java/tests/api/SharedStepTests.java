package tests.api;

import io.restassured.response.Response;
import models.RunsTestData;
import models.SharedStepModels;
import models.SharedStepTestData;
import models.StepModels;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;

public class SharedStepTests extends BaseApiTest {

    @Test(description = "This method allows to retrieve all shared steps stored in selected project.", priority = 1)
    public void getAllSharedSteps() {
        spec
                .when()
                .get(BASE_URL_QASE + "/shared_step/ARTEM")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Test(description = "This method allows to create a shared step in selected project.", priority = 2)
    public void createNewSharedStep() {
        StepModels stepModels = StepModels.builder()
                .hash("TEST")
                .data("TEST")
                .action("TEST")
                .expectedResult("TEST")
                .build();

        SharedStepModels sharedStepModels = SharedStepModels.builder()
                .title("TEST")
                .steps(Collections.singletonList(stepModels))
                .build();

        Response response = spec
                .body(sharedStepModels)
                .when()
                .post(BASE_URL_QASE + "/shared_step/ARTEM")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true))
                .extract().response();

        SharedStepTestData.createdHashId = response.path("result.hash");
    }

    @Test(description = "This method allows to retrieve a specific shared step.", priority = 3)
    public void getSpecificSharedStep() {

        String hashId = SharedStepTestData.createdHashId;

        spec
                .when()
                .get(BASE_URL_QASE + "/shared_step/ARTEM/" + hashId)
                .then()
                .log().all()
                .statusCode(200)
                .body("result.hash", equalTo(hashId));
    }

    @Test(description = "This method completely deletes a shared step from repository.", priority = 4)
    public void deleteSharedStep() {

        String hashId = SharedStepTestData.createdHashId;

        spec
                .when()
                .delete(BASE_URL_QASE + "/shared_step/ARTEM/" + hashId)
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }
}
