package tests.ui;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {

    @Test (testName = "Валидное создание аккаунта", priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    public void checkValideSignUp() {
        signUpPage.openPage()
                .isPageOpened()
                .checkSignUp("forzoreugh12388@gmail.com",
                        "forzoreugh12388@gmail.com",
                        "forzoreugh12388@gmail.com");
        inactivePage.waitForPageLoad()
                .assertOpenPage();
    }

    @Test (testName = "Создание аккаунта с невалидным Email", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    public void checkSignUpWithInvalideEmail() {
        signUpPage.openPage()
                .isPageOpened()
                .checkSignUp("", password, password)
                .assertErrorEmail("Email");
    }

    @Test (testName = "Создание аккаунта с невалидным Password", priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    public void checkSignUpWithInvalidePassword() {
        signUpPage.openPage()
                .isPageOpened()
                .checkSignUp(email, "", password)
                .assertErrorEmail("Password");
    }

    @Test (testName = "Создание аккаунта с невалидным Confirm Password", priority = 4)
    @Severity(SeverityLevel.CRITICAL)
    public void checkSignUpWithInvalideConfirmPassword() {
        signUpPage.openPage()
                .isPageOpened()
                .checkSignUp(email, password, "")
                .assertErrorEmail("Confirm password");
    }
}
