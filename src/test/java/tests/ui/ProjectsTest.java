package tests.ui;

import dto.Project;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class ProjectsTest extends BaseTest {

    protected static final String PROJECT_NAME = "QASE_PROJECT";
    protected static final String PROJECT_CODE = "QP";

    Project project = Project.builder()
            .projectName(PROJECT_NAME)
            .projectCode(PROJECT_CODE)
            .isPrivate(true)
            .addAllMembers(true)
            .build();

    @Test (testName = "Создание проекта", priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    public void createProject() {
        loginStep.login(email, password, false);
        projectStep.createProjects(project);
        projectsPage.openPage()
                .assertNameProject(PROJECT_NAME);
    }

    @Test (testName = "Удаление существующего проекта", priority = 3)
    @Severity(SeverityLevel.BLOCKER)
    public void deleteProject() {
        loginStep.login(email, password, false);
        projectStep.deleteProject(PROJECT_NAME);
    }

    @Test (testName = "Фильтрация профилей", priority = 2)
    @Severity(SeverityLevel.NORMAL)
    public void checkSearchProject() {
        loginStep.login(email, password, false);
        projectsPage.assertNameProject(PROJECT_NAME);
    }
}
