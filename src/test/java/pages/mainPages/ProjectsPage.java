package pages.mainPages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import pages.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.error.ShouldBe.shouldBe;
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
        $x("//span[text()='Create new project']")
                .shouldBe(visible, Duration.ofSeconds(15))
                .click();
        return new NewProjectModal();
    }

    public ProjectsPage assertNameProject(String project) {
        $(byText(project)).shouldBe(visible).shouldHave(exactText(project));
        return this;
    }

    public void assertOpenPage() {
        $(byText("Create new project")).shouldBe(visible);
    }

    public void openProject(String nameProject) {
        SelenideElement viewCart = $x("//button[@aria-label='Grid view']")
                .shouldBe(visible, Duration.ofSeconds(15));
        viewCart.scrollIntoView(true).click();
        String xpath = String.format("//div/following::h4[text()='%s']", nameProject);
        $x(xpath)
                .shouldBe(visible, Duration.ofSeconds(5))
                .shouldBe(interactable)
                .click();
    }
}
