package pages.startingPages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import pages.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

@Log4j2
public class SignUpPage extends BasePage {

    private final SelenideElement EMAIL_FIELD = $(By.name("email"));
    private final SelenideElement PASSWORD_FIELD = $(By.name("password"));
    private final SelenideElement PASSWORD_CONFIRM_FIELD = $(By.name("passwordConfirmation"));
    private final SelenideElement SIGNUP_BUTTON =
            $x("//span[text()='Sign up with email']/ancestor::button");
    private final SelenideElement VALIDATE_MESSAGE_EMAIL = $x("//input[@name='email']" +
            "/following::small[text()='This field is required']");
    private final SelenideElement VALIDATE_MESSAGE_PASSWORD = $x("//input[@name='password']" +
            "/following::small[text()='Password must has at least 12 characters']");
    private final SelenideElement VALIDATE_MESSAGE_CONFIRM_PASSWORD =
            $x("//input[@name='passwordConfirmation']/following::small[text()='Passwords must match']");

    @Override
    public SignUpPage openPage() {
        log.info("Open SignUp page");
        open("/signup");
        return this;
    }

    @Override
    public SignUpPage isPageOpened() {
        try {
            $x("//span[text()='Sign up with email']/ancestor::button[@type='submit']").shouldHave(visible);
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("SignUp page isn't opened");
        }
        return this;
    }

    public void assertOpenPage() {
        assertEquals($x("//h1[text()='Get started for free']").getText(), "Get started for free");
    }

    public SignUpPage checkSignUp(String email, String password, String confirmPassword) {
        log.info("Performing registration by email: {}", email);
        EMAIL_FIELD.setValue(email);
        PASSWORD_FIELD.setValue(password);
        PASSWORD_CONFIRM_FIELD.setValue(confirmPassword);
        SIGNUP_BUTTON.click();
        log.info("Completion of registration");
        return this;
    }

    public void assertErrorEmail(String errorName) {
        if (errorName == "Email") {
            VALIDATE_MESSAGE_EMAIL.shouldHave(visible);
            assertEquals(VALIDATE_MESSAGE_EMAIL.text(),
                    "This field is required");
        } else if (errorName == "Password") {
            VALIDATE_MESSAGE_PASSWORD.shouldHave(visible);
            assertEquals(VALIDATE_MESSAGE_PASSWORD.text(),
                    "Password must has at least 12 characters");
        } else if (errorName == "Confirm password") {
            VALIDATE_MESSAGE_CONFIRM_PASSWORD.shouldHave(visible);
            assertEquals(VALIDATE_MESSAGE_CONFIRM_PASSWORD.text(),
                    "Passwords must match");
        }
    }
}
