package steps;

import dto.Project;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.mainPages.ProjectsPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class ProjectStep {

    ProjectsPage projectsPage;

    public ProjectStep() {
        projectsPage = new ProjectsPage();
    }

    public ProjectStep createProjects(Project project) {
        log.info("Start 'Project Step [createProjects]'");
        projectsPage.openFormCreateProject()
                .createProject(project);
        log.info("End 'Project Step [createProjects]'");
        return this;
    }

    public ProjectStep deleteProject(String project) {
        log.info("Start 'Project Step [deleteProject]'");
        $(byText(project))
                .ancestor("tr")
                .find("button[aria-label='Open action menu']")
                .click();
        $("[data-testid=remove]").click();
        $x("//span[text()='Delete project']").click();
        log.info("End 'Project Step [deleteProject]'");
        return this;
    }
}
