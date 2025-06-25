package tests;

import dto.Project;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class ProjectTest extends BaseTest {

    Project project = Project.builder()
            .projectName("SVIDZINSKI ARTEM")
            .description("Hello, World!")
            .isPrivate(true)
            .addAllMembers(true)
            .build();

    @Test (testName = "Создание проекта")
    @Severity(SeverityLevel.BLOCKER)
    public void createProject() {
        loginStep.login();
        projectStep.createProjects(project);
        projectsPage.open()
                .assertNameProject("SVIDZINSKI ARTEM");
    }

    @Test (testName = "Удаление существующего проекта")
    @Severity(SeverityLevel.BLOCKER)
    public void deleteProject() {
        loginStep.login()
                .createProjects(project);
        projectsPage.open();
        projectStep.deleteProject("SVIDZINSKI ARTEM");
    }
}
