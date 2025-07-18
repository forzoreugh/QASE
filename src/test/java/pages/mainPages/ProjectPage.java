package pages.mainPages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import pages.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

@Log4j2
public class ProjectPage extends BasePage {

    private static SelenideElement SAVE_MESSAGE =
            $x("//span[text()='Test case was created successfully!']");

    public ProjectPage choiceButton(String text) {
        String xpath = String.format("//button/following::span[text()='%s']", text);
        $x(xpath).shouldBe(visible)
                .click();
        return this;
    }

    public void assertOpenPage() {
        assertEquals($x("//button/following::span[text()='New test']").getText(), "New test");
    }

    public void assertOpenCreateCaseForm() {
        assertEquals($x("//h1[text()='Create test case']").getText(), "Create test case");
    }

    public void assertSaveCase() {
        assertEquals(SAVE_MESSAGE.getText(), "Test case was created successfully!");
    }

    @Override
    public ProjectPage openPage() {
        log.info("Open Project page 'QASE'");
        open("/project/QASE");
        return this;
    }

    @Override
    public BasePage isPageOpened() {
        return null;
    }
}
