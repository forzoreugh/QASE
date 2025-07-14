package tests.api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class AttachmentsTests extends BaseApiTest {

    @Test
    public void checkOne() {
        spec
                .when()
                .get(BASE_URL_QASE + "/attachment")
                .then()
                .log().all()
                .statusCode(200);
    }
}
