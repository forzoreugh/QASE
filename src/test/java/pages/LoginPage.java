package pages;

import com.codeborne.selenide.Selenide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {

    private static final Logger log = LogManager.getLogger(LoginPage.class);
    private final String EMAIL_CSS = "[name=email]";
    private final String PASSWORD_CSS = "[name=password]";

    public LoginPage() {
        super(null);
    }

    @Override
    public LoginPage open() {
        log.info("Open Login page");
        Selenide.open("/login");
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
        $(EMAIL_CSS).setValue(email);
        $(PASSWORD_CSS).setValue(password).submit();
        return new ProjectsPage();
    }
}
