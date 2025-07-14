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
        EMAIL_FIELD.shouldHave(visible);
        EMAIL_FIELD.setValue(email);
        PASSWORD_FIELD.shouldHave(visible);
        PASSWORD_FIELD.setValue(password);
        PASSWORD_CONFIRM_FIELD.shouldHave(visible);
        PASSWORD_CONFIRM_FIELD.setValue(confirmPassword).submit();
        return this;
    }
}
