package tests.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import tests.ui.BaseTest;

import static io.restassured.RestAssured.given;

public class BaseApiTest extends BaseTest {

    protected static final String BASE_URL_QASE = "https://api.qase.io/v1";
    private static final String token = "323b862bfce352242d449b15fb1777bcaeac13734daf6f9b7c1bdb19213f53d2";

    Gson gson = new GsonBuilder().excludeFieldsWithModifiers().create();

    static RequestSpecification spec =
            given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .header("Token", token);
}
