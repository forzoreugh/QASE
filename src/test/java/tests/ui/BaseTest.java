package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.mainPages.ProjectsPage;
import pages.startingPages.InactivePage;
import pages.startingPages.LoginPage;
import pages.startingPages.ResetPasswordPage;
import pages.startingPages.SignUpPage;
import steps.LoginStep;
import steps.ProjectStep;
import steps.ResetPasswordStep;
import tests.more.PropertyReader;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseTest {

    LoginStep loginStep;
    LoginPage loginPage;
    ProjectStep projectStep;
    ProjectsPage projectsPage;
    ResetPasswordStep resetPasswordStep;
    ResetPasswordPage resetPasswordPage;
    SignUpPage signUpPage;
    InactivePage inactivePage;
    String email = System.getProperty("QASE_EMAIL", PropertyReader.getProperty("email"));
    String password = System.getProperty("QASE_PASSWORD", PropertyReader.getProperty("password"));
    String token = System.getProperty("token", PropertyReader.getProperty("token"));

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true, description = "Открытие браузера")
    public void setup(@Optional("chrome") String browser, ITestContext context) {
        Configuration.browser = browser;
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.timeout = 10000;
        Configuration.clickViaJs = true;
        Configuration.browserSize = "1920х1080";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximazed");
        Configuration.browserCapabilities = options;

        loginStep = new LoginStep();
        projectStep = new ProjectStep();
        projectsPage = new ProjectsPage();
        loginPage = new LoginPage();
        resetPasswordStep = new ResetPasswordStep();
        signUpPage = new SignUpPage();
        inactivePage = new InactivePage();
        resetPasswordPage = new ResetPasswordPage();

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
