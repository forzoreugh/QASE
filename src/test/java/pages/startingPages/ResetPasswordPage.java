package pages.startingPages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import pages.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertEquals;

@Log4j2
public class ResetPasswordPage extends BasePage implements SideShape {

    private static final SelenideElement EMAIL_FIELD = $("[name=email]");
    private final SelenideElement RESET_PASSWORD_BUTTON =
            $x("//span[text()='Send password reset link']/ancestor::button[@type='submit']");
    private final SelenideElement SENDING_LETTER_MESSAGE =
            $x("//span[text()='We have e-mailed your password reset link!']/ancestor::div[@role='alert']");
    private final SelenideElement ERROR_LETTER_MESSAGE = $x("//span[text()='Value 'test' does not match format email of type string']/ancestor::div[@role='alert']");
    private final SelenideElement EMAIL_ERROR_MESSAGE = $x("//small[text()='This field is required']");
    private final SelenideElement LOGIN_BUTTON = $x("//a[@href='/login']");
    private final SelenideElement LOGIN_SSO_BUTTON = $x("//a[@href='/sso/login']");

    @Override
    public ResetPasswordPage openPage() {
        log.info("Open Reset Password Page");
        open("/password/reset");
        return this;
    }

    @Override
    public ResetPasswordPage isPageOpened() {
        try {
            $(EMAIL_FIELD).shouldHave(visible);
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Reset Password Page isn't opened");
        }
        return this;
    }

    public ResetPasswordPage sendPassword(String email) {
        log.info("Sending updated password to email: {}", email);
        $(EMAIL_FIELD).setValue(email);
        $(RESET_PASSWORD_BUTTON).submit();
        log.info("Confirmation of sending a notification about password change");
        return this;
    }

    public void assertOpenPage() {
        assertEquals($x("//h1[text()='Reset your password']").getText(), "Reset your password");
    }

    public void assertAllertMessage(String fillingEmail) {
        if (fillingEmail == "correct") {
            assertEquals($(SENDING_LETTER_MESSAGE).getText(), "We have e-mailed your password reset link!");
        } else if (fillingEmail == "not correct") {
            assertEquals($(ERROR_LETTER_MESSAGE).getText(), "Value 'email_not_correct' does not match " +
                    "format email of type string");
        } else if (fillingEmail == null) {
            assertEquals(EMAIL_ERROR_MESSAGE.getText(), "This field is required");
        }
    }

    public ResetPasswordPage goToLoginPage() {
        log.info("Go to Login Page");
        LOGIN_BUTTON.click();
        return this;
    }
}
