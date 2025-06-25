package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.ProjectsPage;
import steps.LoginStep;
import steps.ProjectStep;
import utils.AllureUtils;
import utils.TestListener;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static utils.AllureUtils.takeScreenshot;

@Log4j2
@Listeners (TestListener.class)
public class BaseTest {

    LoginStep loginStep;
    ProjectStep projectStep;
    ProjectsPage projectsPage;
    WebDriver driver;
    String user = System.getProperty("user");
    String password = System.getProperty("password");

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

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(false)
                .savePageSource(true)
        );
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(driver);
        }
        closeWebDriver();
    }
}
