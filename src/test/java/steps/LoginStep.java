package steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.LoginPage;
import pages.ProjectsPage;

public class LoginStep {

    private static final Logger log = LogManager.getLogger(LoginStep.class);
    LoginPage loginPage;
    ProjectsPage projectsPage;

    public LoginStep () {
        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
    }

    public ProjectStep login() {
        log.info("Start 'Login Step [login]'");
        loginPage.open()
                .isPageOpened()
                .login("forzoreugh@gmail.com", "SCartjom17171717")
                .isPageOpened();
        log.info("End 'Login Step [login]'");
        return new ProjectStep();
    }
}
