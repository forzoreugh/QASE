package tests.api;

import io.restassured.response.Response;
import models.PlanModels;
import models.PlanTestData;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PlansTests extends BaseApiTest {

    @Test(description = "This method allows to retrieve all plans stored in selected project.", priority = 1)
    public void getAllPlans() {
        spec
                .when()
                .get(BASE_URL_QASE + "/plan/ARTEM")
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
                .cases(List.of(2))
                .build();

        Response response = spec
                .body(gson.toJson(planModels))
                .when()
                .post(BASE_URL_QASE + "/plan/ARTEM")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true))
                .extract().response();

        PlanTestData.createdPlanId = response.path("result.id");
    }

    @Test(description = "This method allows to retrieve a specific plan.", priority = 3)
    public void getSpecificPlan() {
        spec
                .when()
                .get(BASE_URL_QASE + "/plan/ARTEM")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Test(description = "This method completely deletes a plan from repository.", priority = 4,
            dependsOnMethods = "createNewPlan")
    public void deletePlan() {

        int planId = PlanTestData.createdPlanId;

        spec
                .when()
                .delete(BASE_URL_QASE + "/plan/ARTEM/" + planId)
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }
}
