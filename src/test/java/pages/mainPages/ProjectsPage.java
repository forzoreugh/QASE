package pages.mainPages;

import lombok.extern.log4j.Log4j2;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import pages.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.*;

@Log4j2
public class ProjectsPage extends BasePage {

    @Override
    public ProjectsPage openPage() {
        log.info("Open Projects page");
        open("/projects");
        return this;
    }

    @Override
    public ProjectsPage isPageOpened() {
        try {
            $(byText("Create new project")).shouldHave(visible);
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Projects page isn't opened");
        }
        return this;
    }

    public NewProjectModal openFormCreateProject() {
        log.info("Open form Create Project");
        $x("//span[text()='Create new project']").click();
        return new NewProjectModal();
    }

    public ProjectsPage assertNameProject(String project) {
        @NonNull String element = $(byText(project)).getText();
        assertEquals(element, "SVIDZINSKI ARTEM");
        return this;
    }

    public void assertOpenPage() {
        assertEquals($x("//span[text()='Create new project']").text(), "Create new project");
    }
}
