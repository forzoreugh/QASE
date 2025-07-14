package tests.ui;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test (testName = "Валидная авторизация", priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    public void checkValideLogin() {
        loginStep.login(email, password, true);
    }

    @Test (testName = "Валидная авторизация c признаком запоминания данных", priority = 2)
    @Severity(SeverityLevel.BLOCKER)
    public void checkValideLoginWithRemember() {
        loginStep.login(email, password, false);
    }

    @Test (testName = "Авторизация с невалидным Email", priority = 3)
    @Severity(SeverityLevel.NORMAL)
    public void checkInvalideLoginWithEmail() {
        loginStep.login("", password, true);
        loginPage.assertErrorEmail("Email");
    }

    @Test (testName = "Авторизация с невалидным Password", priority = 4)
    @Severity(SeverityLevel.NORMAL)
    public void checkInvalideLoginWithPassword() {
        loginStep.login(email, "", true);
        loginPage.assertErrorEmail("Password");
    }

    @Test (testName = "Авторизация с невалидным Email/Password", priority = 5)
    @Severity(SeverityLevel.NORMAL)
    public void checkEmptyLogin() {
        loginStep.login("", "", true);
        loginPage.assertErrorEmail("Login" + "Password");
    }

    @Test (testName = "Переход к форме восстановления пароля", priority = 6)
    @Severity(SeverityLevel.CRITICAL)
    public void checkTransitionToResetPassword() {
        loginPage.openPage()
                .isPageOpened()
                .goForgotPassword()
                .assertOpenPage();
    }

    @Test (testName = "Переход к форме создания аккаунта", priority = 7)
    @Severity(SeverityLevel.CRITICAL)
    public void checkTransitionToCreateAccount() {
        loginPage.openPage()
                .isPageOpened()
                .goCreateAccount()
                .assertOpenPage();
    }
}
