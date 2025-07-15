package tests.api;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class SystemFieldsTests extends BaseApiTest {

    @Test(description = "This method allows to retrieve all system fields.", priority = 1)
    public void getAllSystemFields() {
        spec
                .when()
                .get(BASE_URL_QASE + "/system_field")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }
}
