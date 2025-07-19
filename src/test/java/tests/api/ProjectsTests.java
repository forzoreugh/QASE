package tests.api;

import io.restassured.response.Response;
import models.ProjectTestData;
import models.ProjectsModels;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class ProjectsTests extends BaseApiTest {

    @Test(description = "This method allows to retrieve all projects available for your account. You can limit and " +
            "offset params to paginate.", priority = 1)
    public void getAllProjects() {
        spec
                .when()
                .get(BASE_URL_QASE + "/project")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Test(description = "This method is used to create a new project through API.", priority = 2)
    public void createNewProject() {
        ProjectsModels projectsModels = ProjectsModels.builder()
                .title("Тест")
                .code("Тест")
                .description("Тест")
                .access("group")
                .group("Тест")
                .build();

        Response response = spec
                .body(gson.toJson(projectsModels))
                .when()
                .post(BASE_URL_QASE + "/project")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true))
                .extract().response();

        ProjectTestData.createdProjectId = response.path("result.id");
    }

    @Test(description = "This method allows to retrieve a specific project.", priority = 3)
    public void getProjectByCode() {
        spec
                .when()
                .get(BASE_URL_QASE + "/project/ARTEM")
                .then()
                .log().all()
                .statusCode(200)
                .body("result.code", equalTo("ARTEM"));
    }

    @Test(description = "This method allows to delete a specific project.", priority = 4)
    public void deleteProjectByCode() {

        int projectId = ProjectTestData.createdProjectId;

        spec
                .when()
                .delete(BASE_URL_QASE + "/project/" + projectId)
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }
}
