package tests.api;

import io.restassured.response.Response;
import models.CustomFieldModels;
import models.CustomFieldTestData;
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

        Response response = spec
                .body(gson.toJson(customFieldModels))
                .when()
                .post(BASE_URL_QASE + "/custom_field")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true))
                .extract().response();

        CustomFieldTestData.createdCustomFieldId = response.path("result.id");
    }

    @Test(description = "This method allows to retrieve custom field.", priority = 3)
    public void getCustomFieldById() {

        int customFieldId = CustomFieldTestData.createdCustomFieldId;

        spec
                .when()
                .get(BASE_URL_QASE + "/custom_field/" + + customFieldId)
                .then()
                .log().all()
                .statusCode(200)
                .body("result.id", equalTo(customFieldId));
    }

    @Test(description = "This method allows to delete custom field.", priority = 4,
            dependsOnMethods = "createNewCustomField")
    public void deleteCustomFieldById() {

        int customFieldId = CustomFieldTestData.createdCustomFieldId;

        spec
                .when()
                .delete(BASE_URL_QASE + "/custom_field/" + customFieldId)
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }
}
