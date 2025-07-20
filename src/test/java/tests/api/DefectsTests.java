package tests.api;

import io.restassured.response.Response;
import models.DefectModels;
import models.DefectTestData;
import org.hamcrest.core.IsEqual;
import org.testng.annotations.Test;

public class DefectsTests extends BaseApiTest {

    @Test(description = "This method allows to retrieve all defects stored in selected project.", priority = 1)
    public void getAllDefects() {
        spec
                .when()
                .get(BASE_URL_QASE + "/defect/ARTEM")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", IsEqual.equalTo(true));
    }

    @Test(description = "This method allows to create a defect in selected project.", priority = 2)
    public void сreateNewDefect() {
        DefectModels defectModels = DefectModels.builder()
                .title("[Хранилище] Не открывается форма добавления тест-плана.")
                .actualResult("Форма открывается.")
                .severity(1)
                .build();

        Response response = spec
                .body(gson.toJson(defectModels))
                .when()
                .post(BASE_URL_QASE + "/defect/ARTEM")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", IsEqual.equalTo(true))
                .extract().response();

        DefectTestData.createdDefectId = response.path("result.id");
    }

    @Test(description = "This method allows to retrieve a specific defect.", priority = 3)
    public void getSpecificDefect() {

        int defectId = DefectTestData.createdDefectId;

        spec
                .when()
                .get(BASE_URL_QASE + "/defect/ARTEM/" + defectId)
                .then()
                .log().all()
                .statusCode(200)
                .body("result.id", IsEqual.equalTo(defectId));
    }

    @Test(description = "This method completely deletes a defect from repository.", priority = 5,
            dependsOnMethods = "сreateNewDefect")
    public void deleteDefect() {

        int defectId = DefectTestData.createdDefectId;

        spec
                .when()
                .delete(BASE_URL_QASE + "/defect/ARTEM/" + defectId)
                .then()
                .log().all()
                .statusCode(200)
                .body("status", IsEqual.equalTo(true));
    }

    @Test(description = "This method updates a defect.", priority = 4)
    public void updateDefect() {
        DefectModels defectModels = DefectModels.builder()
                .title("[Хранилище] Не закрывается тост подтверждения")
                .actualResult("Тост подтверждения закрывается")
                .severity(3)
                .build();

        сreateNewDefect();
        int defectId = DefectTestData.createdDefectId;

        spec
                .body(gson.toJson(defectModels))
                .when()
                .patch(BASE_URL_QASE + "/defect/ARTEM/" + defectId)
                .then()
                .log().all()
                .statusCode(200)
                .body("status", IsEqual.equalTo(true));
    }

    @Test(description = "This method allows to resolve a specific defect.", priority = 6,
            dependsOnMethods = "сreateNewDefect")
    public void resolveSpecificDefect() {

        сreateNewDefect();
        int defectId = DefectTestData.createdDefectId;

        spec
                .when()
                .patch(BASE_URL_QASE + "/defect/ARTEM/resolve/" + defectId)
                .then()
                .log().all()
                .statusCode(200)
                .body("status", IsEqual.equalTo(true));
    }
}
