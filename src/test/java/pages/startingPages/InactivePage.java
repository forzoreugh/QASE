package pages.startingPages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import pages.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

@Log4j2
public class InactivePage extends BasePage {

    @Override
    public InactivePage openPage() {
        log.info("Open Inactive page");
        open("/inactive");
        return this;
    }

    @Override
    public InactivePage isPageOpened() {
        try {
            $(By.id("resend")).shouldHave(visible);
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Login page isn't opened");
        }
        return this;
    }

    public InactivePage waitForPageLoad() {
        $(By.id("resend")).shouldHave(visible);
        return this;
    }

    public void assertOpenPage() {
        assertEquals($x("//h1[@data-qase-test='congratulations']").text(), "Congratulations!");
    }
}
