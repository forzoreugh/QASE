package tests.api;

import io.restassured.response.Response;
import models.MilestoneModels;
import models.MilestoneTestData;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class MilestoneTests extends BaseApiTest {

    @Test(description = "This method allows to retrieve all milestones stored in selected project.", priority = 1)
    public void getAllMilestones() {
        spec
                .when()
                .get(BASE_URL_QASE + "/milestone/ARTEM")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Test(description = "This method allows to create a milestone in selected project.", priority = 2)
    public void createNewMilestone() {
        MilestoneModels milestoneModels = MilestoneModels.builder()
                .title("test")
                .description("test")
                .dueDate(35)
                .status("active")
                .build();

        Response response = spec
                .body(gson.toJson(milestoneModels))
                .when()
                .post(BASE_URL_QASE + "/milestone/ARTEM")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true))
                .extract().response();

        MilestoneTestData.createdMilestoneId = response.path("result.id");
    }

    @Test(description = "This method allows to retrieve a specific milestone.", priority = 3)
    public void getSpecificMilestone() {
        spec
                .when()
                .get(BASE_URL_QASE + "/milestone/ARTEM/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Test(description = "This method completely deletes a milestone from repository.", priority = 4,
            dependsOnMethods = "createNewMilestone")
    public void deleteMilestone() {

        int milestoneId = MilestoneTestData.createdMilestoneId;

        spec
                .when()
                .delete(BASE_URL_QASE + "/milestone/ARTEM/" + milestoneId)
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Test(description = "This method updates a milestone.", priority = 5)
    public void updateMilestone() {
        MilestoneModels milestoneModels = MilestoneModels.builder()
                .title("test2")
                .description("test2")
                .dueDate(32)
                .status("active")
                .build();

        spec
                .body(gson.toJson(milestoneModels))
                .when()
                .patch(BASE_URL_QASE + "/milestone/ARTEM/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }
}
