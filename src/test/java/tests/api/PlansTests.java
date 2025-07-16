package tests.api;

import models.PlanModels;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;

public class PlansTests extends BaseApiTest {

    @Test(description = "This method allows to retrieve all plans stored in selected project.", priority = 1)
    public void getAllPlans() {
        spec
                .when()
                .get(BASE_URL_QASE + "/plan/QP")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Test(description = "This method allows to create a plan in selected project.", priority = 2)
    public void createNewPlan() {
        PlanModels planModels = PlanModels.builder()
                .title("Тестовые планы")
                .description("Тестовые планы")
                .cases(new ArrayList<>(5))
                .build();

        spec
                .body(gson.toJson(planModels))
                .when()
                .get(BASE_URL_QASE + "/plan/QP")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Test(description = "This method allows to retrieve a specific plan.", priority = 3)
    public void getSpecificPlan() {
        spec
                .when()
                .get(BASE_URL_QASE + "/plan/QP/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Test(description = "This method completely deletes a plan from repository.", priority = 4)
    public void deletePlan() {
        spec
                .when()
                .delete(BASE_URL_QASE + "/plan/QP/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }
}
