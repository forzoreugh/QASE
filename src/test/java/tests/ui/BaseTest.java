package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.mainPages.ProjectsPage;
import pages.startingPages.LoginPage;
import pages.startingPages.ResetPasswordPage;
import pages.startingPages.SignUpPage;
import steps.LoginStep;
import steps.ProjectStep;
import utils.TestListener;
import static com.codeborne.selenide.Selenide.closeWebDriver;

@Log4j2
@Listeners (TestListener.class)
public class BaseTest {

    LoginStep loginStep;
    LoginPage loginPage;
    ProjectStep projectStep;
    ProjectsPage projectsPage;
    ResetPasswordPage resetPasswordPage;
    SignUpPage signUpPage;
    String email = System.getProperty("EMAIL", System.getProperty("EMAIL"));
    String password = System.getProperty("PASSWORD", System.getProperty("PASSWORD"));

    @BeforeMethod
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.timeout = 10000;
        Configuration.clickViaJs = true;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximazed");
        Configuration.browserCapabilities = options;

        loginStep = new LoginStep();
        projectStep = new ProjectStep();
        projectsPage = new ProjectsPage();
        loginPage = new LoginPage();
        resetPasswordPage = new ResetPasswordPage();
        signUpPage = new SignUpPage();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(false)
                .savePageSource(true)
        );
    }
/*
    @AfterMethod
    public void tearDown(ITestResult result) {
        closeWebDriver();
    }

 */
}
