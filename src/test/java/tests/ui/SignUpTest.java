package tests.ui;

import org.testng.annotations.Test;
import pages.startingPages.SignUpPage;

public class SignUpTest extends BaseTest {

    @Test
    public void checkValideSignUp() {
        signUpPage.openPage()
                .isPageOpened()
                .checkSignUp(email, password, password);
    }

    @Test
    public void checkSignUpWithInvalideEmail() {
        signUpPage.openPage()
                .isPageOpened()
                .checkSignUp("", password, password);
    }

    @Test
    public void checkSignUpWithInvalidePassword() {
        signUpPage.openPage()
                .isPageOpened()
                .checkSignUp(email, "", password);
    }

    @Test
    public void checkSignUpWithInvalideConfirmPassword() {
        signUpPage.openPage()
                .isPageOpened()
                .checkSignUp(email, password, "");
    }
}
