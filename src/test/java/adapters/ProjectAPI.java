package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.create_project.CreateProjectRq;
import models.create_project.CreateProjectRs;

import static io.restassured.RestAssured.given;

public class ProjectAPI {

    static String token = "2c5ec1d3ade13001e0c5bffe7f84928ce375b03cfb805eaa906b8e3308c8c593";

    static Gson gson = new GsonBuilder().excludeFieldsWithModifiers().create();

    static RequestSpecification spec =
            given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .header("Token", token);

    public static CreateProjectRs createProject(CreateProjectRq rq) {
        return
            spec
                .body(gson.toJson(rq))
                .when()
                .post("https://api.qase.io/v1/project")
                .then()
                .log().all()
                .statusCode(200)
                    .extract()
                    .as(CreateProjectRs.class);
    }

    public static void delete(String code) {
        spec
        .delete("https://api.qase.io/v1/project/" + code)
                .then()
                .log().all()
                .statusCode(200);
    }
}
