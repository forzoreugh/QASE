package tests.ui;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test (testName = "Валидная авторизация без признака запоминания данных", priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    public void checkValideLogin() {
        loginStep.login(email, password, true);
        projectsPage.assertOpenPage();
    }

    @Test (testName = "Валидная авторизация c признаком запоминания данных", priority = 2)
    @Severity(SeverityLevel.BLOCKER)
    public void checkValideLoginWithRemember() {
        loginStep.login(email, password, false);
        projectsPage.assertOpenPage();
    }

    @Test (dataProvider = "Наборы для тест-кейса 'Авторизация с невалидным Email'", testName = "Авторизация с невалидным Email", priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    public void checkInvalideLoginWithEmail(String email, String password, boolean isRemember) {
        loginStep.login(email, password, isRemember);
        loginPage.assertErrorMessage("Email");
    }

    @Test(dataProvider = "Наборы для тест-кейса 'Авторизация с невалидным Password'", testName = "Авторизация с невалидным Email/Password", priority = 4)
    @Severity(SeverityLevel.CRITICAL)
    public void checkEmptyLogin(String email, String password, boolean isRemember) {
        loginStep.login(email, password, isRemember);
        loginPage.assertErrorMessage("Password");
    }

    @Test (testName = "Авторизация с невалидным Email/Password", priority = 5)
    @Severity(SeverityLevel.CRITICAL)
    public void checkEmptyLogin() {
        loginStep.login("", "", true);
        loginPage.assertErrorMessage("Login" + "Password");
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

    @DataProvider(name = "Наборы для тест-кейса 'Авторизация с невалидным Password'")
    public Object[][] invalidePasswordCheckLogin() {
        return new Object[][]{
                {"Artem@gmail.com", "", true},
                {"Артем@gmail.com", "", false},
                {"     ", "", false},
                {"     ", "", true}
        };
    }

    @DataProvider(name = "Наборы для тест-кейса 'Авторизация с невалидным Email'")
    public Object[][] invalideEmailCheckLogin() {
        return new Object[][]{
                {"", "PASSWORD", true},
                {"", "ПАРОЛЬ", false},
                {"", "    ", false},
                {"", "    ", true}
        };
    }
}
