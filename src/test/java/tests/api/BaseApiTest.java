package tests.api;

import com.codeborne.selenide.Configuration;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseApiTest {

    static String Token = "QASE_TOKEN";
    static String BASE_URL_QASE = "https://api.qase.io/v1";

    static RequestSpecification spec =
            given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .header("Token:", Token);

}
