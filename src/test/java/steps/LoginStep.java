package steps;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.startingPages.LoginPage;
import pages.mainPages.ProjectsPage;

@Log4j2
public class LoginStep {

    private final String EMAIL_CSS = "[name=email]";
    private final String PASSWORD_CSS = "[name=password]";
    LoginPage loginPage;
    ProjectsPage projectsPage;

    public LoginStep () {
        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
    }

    public ProjectsPage login(String email, String password, boolean isRemember) {
        log.info("Start 'Login Step [login]'");
        loginPage.openPage()
                .isPageOpened()
                .login(email, password, isRemember);
        log.info("End 'Login Step [login]'");
        return new ProjectsPage();
    }
}
