package tests.api;

import models.DefectModels;
import org.hamcrest.core.IsEqual;
import org.testng.annotations.Test;

public class DefectsTests extends BaseApiTest {

    @Test(description = "This method allows to retrieve all defects stored in selected project.", priority = 1)
    public void getAllDefects() {
        spec
                .when()
                .get(BASE_URL_QASE + "/defect/QP")
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

        spec
                .body(gson.toJson(defectModels))
                .when()
                .post(BASE_URL_QASE + "/defect/QP")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", IsEqual.equalTo(true));
    }

    @Test(description = "This method allows to retrieve a specific defect.", priority = 3)
    public void getSpecificDefect() {
        spec
                .when()
                .get(BASE_URL_QASE + "/defect/QP/4")
                .then()
                .log().all()
                .statusCode(200)
                .body("result.id", IsEqual.equalTo(4));
    }

    @Test(description = "This method completely deletes a defect from repository.", priority = 4)
    public void deleteDefect() {
        spec
                .when()
                .delete(BASE_URL_QASE + "/defect/QP/4")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", IsEqual.equalTo(true));
    }

    @Test(description = "This method updates a defect.", priority = 5)
    public void updateDefect() {
        DefectModels defectModels = DefectModels.builder()
                .title("[Хранилище] Не закрывается тост подтверждения")
                .actualResult("Тост подтверждения закрывается")
                .severity(3)
                .build();

        spec
                .body(gson.toJson(defectModels))
                .when()
                .patch(BASE_URL_QASE + "/defect/QP/4")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", IsEqual.equalTo(true));
    }

    @Test(description = "This method allows to resolve a specific defect.", priority = 6)
    public void resolveSpecificDefect() {
        spec
                .when()
                .patch(BASE_URL_QASE + "/defect/QP/resolve/4")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", IsEqual.equalTo(true));
    }
}
