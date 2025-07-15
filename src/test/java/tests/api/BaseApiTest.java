package tests.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.java.Log;
import pages.startingPages.LoginPage;
import tests.ui.BaseTest;

import static io.restassured.RestAssured.given;

public class BaseApiTest extends BaseTest {

    private static final String token = "547cdb0c12411f7a48f7df418ef6457b3cd5fc3477b92e69a467b15d783e12ca";
    protected static final String BASE_URL_QASE = "https://api.qase.io/v1";
    protected static final String FILE_PATH = "C:\\Users\\admin\\Desktop\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe";

    Gson gson = new GsonBuilder().excludeFieldsWithModifiers().create();

    static RequestSpecification spec =
            given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .header("Token", token);

}
