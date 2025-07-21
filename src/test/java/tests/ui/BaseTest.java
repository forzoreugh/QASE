package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.mainPages.CreateCasePage;
import pages.mainPages.ProjectPage;
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

import java.lang.reflect.Method;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {

    protected static String token = System.getProperty("token", PropertyReader.getProperty("token"));
    protected final String email = System.getProperty("email", PropertyReader.getProperty("email"));
    protected final String password = System.getProperty("password", PropertyReader.getProperty("password"));
    protected LoginStep loginStep;
    protected LoginPage loginPage;
    protected ProjectStep projectStep;
    protected ProjectsPage projectsPage;
    protected ProjectPage projectPage;
    protected ResetPasswordStep resetPasswordStep;
    protected ResetPasswordPage resetPasswordPage;
    protected SignUpPage signUpPage;
    protected InactivePage inactivePage;
    protected CreateCasePage createCasePage;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser, ITestContext context, Method method) {
        log.info("Starting test '{}' in {} browser", method.getName(), browser.toUpperCase());
        setupAllureListener();
        setupWebDriverManager(browser);
        Configuration.browser = browser;
        Configuration.headless = true;
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.timeout = 15000;
        Configuration.clickViaJs = true;
        Configuration.browserSize = "1920x1080";
        Configuration.browserCapabilities = getBrowserOptions(browser);
        initComponents();
    }

    private void setupWebDriverManager(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                break;
            default:
                throw new IllegalArgumentException(browser);
        }
    }

    private MutableCapabilities getBrowserOptions(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(
                        "--disable-dev-shm-usage",
                        "--ignore-ssl-errors=yes",
                        "--ignore-certificate-errors",
                        "--incognito",
                        "--disable-gpu",
                        "--no-sandbox",
                        "--window-size=1920,1080"
                );
                if (Configuration.headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                return chromeOptions;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--window-size=1920,1080");
                if (Configuration.headless) {
                    firefoxOptions.addArguments("--headless");
                }
                return firefoxOptions;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--window-size=1920,1080");
                return edgeOptions;

            default:
                throw new IllegalArgumentException(browser);
        }
    }

    protected void initComponents() {
        loginStep = new LoginStep();
        projectStep = new ProjectStep();
        projectsPage = new ProjectsPage();
        loginPage = new LoginPage();
        resetPasswordStep = new ResetPasswordStep();
        signUpPage = new SignUpPage();
        inactivePage = new InactivePage();
        resetPasswordPage = new ResetPasswordPage();
        projectPage = new ProjectPage();
        createCasePage = new CreateCasePage();
    }

    private void setupAllureListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true)
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeWebDriver();
    }
}