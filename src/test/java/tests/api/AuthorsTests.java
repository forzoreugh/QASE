package tests.api;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class AuthorsTests extends BaseApiTest {

    @Test(description = "This method allows to retrieve all authors in selected project.", priority = 1)
    public void getAllAuthors() {
        spec
                .when()
                .get(BASE_URL_QASE + "/author")
                .then()
                .log().all()
                .statusCode(200)
                .body("result.entities[0].email", equalTo("fon.der.sniper@gmail.com"));
    }

    @Test(description = "This method allows to retrieve a specific author.", priority = 2)
    public void getSpecificAuthor() {
        spec
                .when()
                .get(BASE_URL_QASE + "/author/296481")
                .then()
                .log().all()
                .statusCode(200)
                .body("result.email", equalTo("fon.der.sniper@gmail.com"));
    }
}
