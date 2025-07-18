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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import pages.mainPages.CreateCasePage;
import pages.mainPages.ProjectPage;
import pages.mainPages.ProjectsPage;
import pages.startingPages.*;
import steps.*;
import tests.more.PropertyReader;
import utils.TestListener;

import java.lang.reflect.Method;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {

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

    protected final String email = System.getProperty("qase_email", PropertyReader.getProperty("email"));
    protected final String password = System.getProperty("qase_password", PropertyReader.getProperty("password"));
    protected final String token = System.getProperty("token", PropertyReader.getProperty("token"));

    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("chrome") String browser, ITestContext context, Method method) {
        log.info("Starting test '{}' in {} browser", method.getName(), browser.toUpperCase());

        setupWebDriverManager(browser);
        configureBrowser(browser);
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
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private void configureBrowser(String browser) {
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.timeout = 15000;
        Configuration.clickViaJs = true;
        Configuration.browserSize = "1920x1080";
        Configuration.browserCapabilities = getBrowserOptions(browser);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--ignore-ssl-errors=yes");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--incognito");
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--window-size=1920,1080");
        Configuration.browserCapabilities = options;

    }

    private MutableCapabilities getBrowserOptions(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(
                        "--start-maximized",
                        "--disable-infobars",
                        "--disable-extensions"
                );
                return chromeOptions;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                return firefoxOptions;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                return edgeOptions;

            default:
                return new MutableCapabilities();
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
        setupAllureListener();
        closeWebDriver();
    }
}