package pages.startingPages;

import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import pages.BasePage;
import pages.mainPages.ProjectsPage;

import static com.codeborne.selenide.Condition.visible;
import static org.testng.Assert.assertEquals;

public class LoginPage extends BasePage {

    private static final Logger log = LogManager.getLogger(LoginPage.class);
    private final SelenideElement EMAIL_FIELD = $("[name=email]");
    private final SelenideElement PASSWORD_FIELD = $("[name=password]");
    private final SelenideElement FORGOT_PASSWORD_LINK = $("[href='/password/reset']");
    private final SelenideElement CREATE_ACCOUNT_LINK = $("[href='/signup']");
    private final SelenideElement REMEMBER_BOOLEAN = $(By.name("remember"));
    private final SelenideElement EMAIL_ERROR_MESSAGE = $x("//input[@type='text']" +
            "/following::small[text()='This field is required']");
    private final SelenideElement PASSWORD_ERROR_MESSAGE = $x("//input[@type='password']" +
            "/following::small[text()='This field is required']");

    @Override
    public LoginPage openPage() {
        log.info("Open Login page");
        open("/login");
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        try {
            $x("//span[text()='Sign in']/ancestor::button[@type='submit']").shouldHave(visible);
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Login page isn't opened");
        }
        return this;
    }

    public ProjectsPage login(String email, String password, boolean isRemember) {
        log.info("Performing authorization with data: email [{}], password [{}]", email, password);
        if (!isRemember) {
            REMEMBER_BOOLEAN.click();
        }
        EMAIL_FIELD.setValue(email);
        PASSWORD_FIELD.setValue(password).submit();
        log.info("Confirmation of entered data upon login");
        return new ProjectsPage();
    }

    public ResetPasswordPage goForgotPassword() {
        log.info("Go to the password return form");
        FORGOT_PASSWORD_LINK.click();
        return new ResetPasswordPage();
    }

    public SignUpPage goCreateAccount() {
        log.info("Go to the account creation form");
        CREATE_ACCOUNT_LINK.click();
        return new SignUpPage();
    }

    public void assertErrorMessage(String errorName) {
        if (errorName == "Email") {
            EMAIL_ERROR_MESSAGE.shouldHave(visible);
            assertEquals(EMAIL_ERROR_MESSAGE.text(),
                    "This field is required");
        } else if (errorName == "Password") {
            PASSWORD_ERROR_MESSAGE.shouldHave(visible);
            assertEquals(PASSWORD_ERROR_MESSAGE.text(),
                    "This field is required");
        } else if (errorName == "Email" && errorName == "Password") {
            EMAIL_ERROR_MESSAGE.shouldHave(visible);
            assertEquals(EMAIL_ERROR_MESSAGE.text(),
                    "This field is required");
            assertEquals(PASSWORD_ERROR_MESSAGE.text(),
                    "This field is required");
        }
    }

    public void assertOpenPage() {
        assertEquals($x("//h1[text()='Log in to your account']").getText(), "Log in to your account");
    }
}
