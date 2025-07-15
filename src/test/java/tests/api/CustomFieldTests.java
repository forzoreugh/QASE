package tests.api;

import models.CustomFieldModels;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class CustomFieldTests extends BaseApiTest {

    @Test(description = "This method allows to retrieve and filter custom fields.", priority = 1)
    public void getAllCustomFields() {
        spec
                .when()
                .get(BASE_URL_QASE + "/custom_field")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Test(description = "This method allows to create custom field.", priority = 2)
    public void createNewCustomField() {
        CustomFieldModels customFieldModels = CustomFieldModels.builder()
                .title("test")
                .entity(0)
                .type(4)
                .placeholder("test")
                .defaultValue("false")
                .isFilterable(false)
                .isVisible(true)
                .isRequired(true)
                .isEnabledForAllProjects(true)
                .build();

        spec
                .body(gson.toJson(customFieldModels))
                .when()
                .post(BASE_URL_QASE + "/custom_field")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Test(description = "This method allows to retrieve custom field.", priority = 3)
    public void getCustomFieldById() {
        spec
                .when()
                .get(BASE_URL_QASE + "/custom_field/3")
                .then()
                .log().all()
                .statusCode(200)
                .body("result.id", equalTo(3));
    }

    @Test(description = "This method allows to delete custom field.", priority = 4)
    public void deleteCustomFieldById() {
        spec
                .when()
                .delete(BASE_URL_QASE + "/custom_field/6")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }
}
