package tests.api;

import models.SharedStepModels;
import models.StepModels;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class SharedStepTests extends BaseApiTest {

    @Test(description = "This method allows to retrieve all shared steps stored in selected project.", priority = 1)
    public void getAllSharedSteps() {
        spec
                .when()
                .get(BASE_URL_QASE + "/shared_step/QP")
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

        spec
                .body(sharedStepModels)
                .when()
                .post(BASE_URL_QASE + "/shared_step/QP")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Test(description = "This method allows to retrieve a specific shared step.", priority = 3)
    public void getSpecificSharedStep() {
        spec
                .when()
                .get(BASE_URL_QASE + "/shared_step/QP/efa8515183599adc7e06d1f40ac62df5fa362e13")
                .then()
                .log().all()
                .statusCode(200)
                .body("result.title", equalTo("TEST"));
    }

    @Test(description = "This method completely deletes a shared step from repository.", priority = 4)
    public void deleteSharedStep() {
        spec
                .when()
                .delete(BASE_URL_QASE + "/shared_step/QP/efa8515183599adc7e06d1f40ac62df5fa362e13")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }
}
