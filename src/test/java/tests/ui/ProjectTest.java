package tests.ui;

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
        loginStep.login("forzoreuh@gmail.com", "SCartjom17171717");
        projectStep.createProjects(project);
        projectsPage.openPage()
                .assertNameProject("SVIDZINSKI ARTEM");
    }

    @Test (testName = "Удаление существующего проекта")
    @Severity(SeverityLevel.BLOCKER)
    public void deleteProject() {
        loginStep.login("forzoreuh@gmail.com", "SCartjom17171717")
                .createProjects(project);
        projectsPage.openPage();
        projectStep.deleteProject("SVIDZINSKI ARTEM");
    }
}
