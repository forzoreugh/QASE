package pages.mainPages;

import com.codeborne.selenide.SelenideElement;
import dto.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.BasePage;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class NewProjectModal extends BasePage {

    private static final Logger log = LogManager.getLogger(NewProjectModal.class);
    // Field
    private final SelenideElement PROJECT_NAME = $(byId("project-name"));
    private final SelenideElement PROJECT_CODE = $(byId("project-code"));
    private final SelenideElement DESCRIPTION = $(byId("description-area"));
    private final SelenideElement IS_PRIVATE = $("[value=private]");
    private final SelenideElement IS_PUBLIC = $("[value=public]");
    private final SelenideElement ADD_ALL_MEMBERS = $("[value=all]");
    private final SelenideElement GROUP_ACCESS = $("group");
    private final SelenideElement DONT_ADD_MEMBERS = $("none");

    // Button
    private final SelenideElement CANCEL_BUTTON = $x("//span[text()='Cancel']/ancestor::button");
    private final SelenideElement CREATE_BUTTON = $x("//span[text()='Create project']/ancestor::button");

    public NewProjectModal createProject(Project project) {
        log.info("Start of project creation");
        PROJECT_NAME.setValue(project.getProjectName());
        PROJECT_CODE.setValue(project.getProjectCode());
        DESCRIPTION.setValue(project.getDescription());

        if (project.isPrivate() == true) {
            IS_PRIVATE.click();
        } else if (project.isPublic() == true) {
            IS_PUBLIC.click();
        } else {
            return this;
        }

        if (project.isGroupAccess() == true) {
            GROUP_ACCESS.click();
        } else if (project.isDontAddMembers() == true) {
            DONT_ADD_MEMBERS.click();
        } else if (project.isAddAllMembers() == true) {
            ADD_ALL_MEMBERS.click();
        } else {
            return this;
        }

        CREATE_BUTTON.click();
        log.info("Completion of the project creation");
        return this;
    }

    @Override
    public NewProjectModal openPage() {
        log.info("New project form open");
        open("/projects");
        $x("//span[text()='Create new project']").click();
        return this;
    }

    @Override
    public BasePage isPageOpened() {
        return null;
    }
}
