package tests.ui;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class ResetPasswordTest extends BaseTest {

    @Test (testName = "Восстановление пароля с валидным Email", priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    public void resetPasswordWithValideEmail() {
        resetPasswordPage.openPage()
                .sendPassword(email)
                .assertAllertMessage("correct");
    }

    @Test (testName = "Восстановление пароля с невалидным Email", priority = 2)
    @Severity(SeverityLevel.NORMAL)
    public void resetPasswordWithInvalideEmail() {
        resetPasswordPage.openPage()
                .sendPassword("email_not_correct")
                .assertAllertMessage("not correct");
    }

    @Test (testName = "Восстановление пароля с пустым Email", priority = 3)
    @Severity(SeverityLevel.NORMAL)
    public void resetPasswordWithEmptyEmail() {
        resetPasswordPage.openPage()
                .sendPassword("")
                .assertAllertMessage(null);
    }

    @Test (testName = "Переход к Login Page", priority = 4)
    @Severity(SeverityLevel.MINOR)
    public void goToLoginPage() {
        resetPasswordPage.openPage()
                .goToLoginPage();
        loginPage.assertOpenPage();
    }
}
