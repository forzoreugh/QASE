package tests.ui;

import org.testng.annotations.Test;
import pages.startingPages.LoginPage;
import steps.LoginStep;

public class LoginTest extends BaseTest {

    @Test
    public void checkValideLogin() {
        loginStep.login(email, password);
    }
}
