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

public class LoginPage extends BasePage implements SideShape{

    private static final Logger log = LogManager.getLogger(LoginPage.class);
    private final SelenideElement EMAIL_FIELD = $("[name=email]");
    private final SelenideElement PASSWORD_FIELD = $("[name=password]");
    private final SelenideElement FORGOT_PASSWORD_LINK = $("[href='/password/reset']");
    private final SelenideElement CREATE_ACCOUNT_LINK = $("[href='/signup']");
    private final SelenideElement REMEMBER_BOOLEAN = $(By.name("remember"));

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

    public ProjectsPage login(String email, String password) {
        log.info("Performing authorization with data: email [{}], password [{}]", email, password);
        EMAIL_FIELD.setValue(email);
        PASSWORD_FIELD.setValue(password).submit();
        assertEquals($x("//span[text()='Create new project']/ancestor::button").getText(),
                "Create new project");
        return new ProjectsPage();
    }

    public ResetPasswordPage goForgotPassword() {
        log.info("");
        FORGOT_PASSWORD_LINK.click();
        return new ResetPasswordPage();
    }

    public SignUpPage goCreateAccount() {
        CREATE_ACCOUNT_LINK.click();
        return new SignUpPage();
    }
}
