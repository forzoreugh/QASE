package tests.ui;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {

    @Test (testName = "Создание аккаунта с невалидным Email", priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    public void checkSignUpWithInvalideEmail() {
        signUpPage.openPage()
                .isPageOpened()
                .checkSignUp("", password, password)
                .assertErrorEmail("Email");
    }

    @Test (testName = "Создание аккаунта с невалидным Password", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    public void checkSignUpWithInvalidePassword() {
        signUpPage.openPage()
                .isPageOpened()
                .checkSignUp(email, "", password)
                .assertErrorEmail("Password");
    }

    @Test (testName = "Создание аккаунта с невалидным Confirm Password", priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    public void checkSignUpWithInvalideConfirmPassword() {
        signUpPage.openPage()
                .isPageOpened()
                .checkSignUp(email, password, "")
                .assertErrorEmail("Confirm password");
    }
}
