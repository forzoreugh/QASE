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
            $x("//span[text()='Send password reset link']/ancestor::button[type=submit]");
    private final SelenideElement SENDING_LETTER =
            $x("//span[text='We have e-mailed your password reset link!']/ancestor::div[role=alert]");
    private final SelenideElement LOGIN_BUTTON = $x("//a[href='/login']");
    private final SelenideElement LOGIN_SSO_BUTTON = $x("//a[href='/sso/login']");

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
        $(EMAIL_FIELD).setValue(email);
        $(RESET_PASSWORD_BUTTON).submit();
        assertEquals($(SENDING_LETTER).getText(), "We have e-mailed your password reset link!");
        return this;
    }
}
