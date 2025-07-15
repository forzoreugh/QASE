package tests.api;

import org.testng.annotations.Test;

public class AttachmentsTests extends BaseApiTest {

    @Test(description = "This method allows to retrieve attachments", priority = 1)
    public void getAllAttachments() {
        spec
                .when()
                .get(BASE_URL_QASE + "/attachment")
                .then()
                .log().all()
                .statusCode(200);
    }
}
