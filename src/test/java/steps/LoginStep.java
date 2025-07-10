package steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.startingPages.LoginPage;
import pages.mainPages.ProjectsPage;

public class LoginStep {

    private static final Logger log = LogManager.getLogger(LoginStep.class);
    private final String EMAIL_CSS = "[name=email]";
    private final String PASSWORD_CSS = "[name=password]";
    LoginPage loginPage;
    ProjectsPage projectsPage;

    public LoginStep () {
        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
    }

    public ProjectStep login(String email, String password) {
        log.info("Start 'Login Step [login]'");
        loginPage.openPage()
                .isPageOpened()
                .login(email, password)
                .isPageOpened();
        log.info("End 'Login Step [login]'");
        return new ProjectStep();
    }
}
