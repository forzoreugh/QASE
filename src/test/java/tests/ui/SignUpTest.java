package tests.ui;

import io.qameta.allure.*;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {

    @Test (testName = "Создание аккаунта с невалидным Email", priority = 1)
    @Epic("Форма добавления аккаунта")
    @Feature("Создание нового аккаунта")
    @Story("Создание аккаунта с некорректным Email")
    @Description("Проверка успешного создания аккаунта через Email")
    @Link(name = "Документация", url = "https://app.qase.io/")
    @Severity(SeverityLevel.CRITICAL)
    public void checkSignUpWithInvalideEmail() {
        signUpPage.openPage()
                .isPageOpened()
                .checkSignUp("", password, password)
                .assertErrorEmail("Email");
    }

    @Test (testName = "Создание аккаунта с невалидным Password", priority = 2)
    @Epic("Форма добавления аккаунта")
    @Feature("Создание нового аккаунта")
    @Story("Создание аккаунта с некорректным Password")
    @Description("Проверка успешного создания аккаунта через Email")
    @Link(name = "Документация", url = "https://app.qase.io/")
    @Severity(SeverityLevel.CRITICAL)
    public void checkSignUpWithInvalidePassword() {
        signUpPage.openPage()
                .isPageOpened()
                .checkSignUp(email, "", password)
                .assertErrorEmail("Password");
    }

    @Test (testName = "Создание аккаунта с невалидным Confirm Password", priority = 3)
    @Epic("Форма добавления аккаунта")
    @Feature("Создание нового аккаунта")
    @Story("Создание аккаунта с некорректным Confirm password")
    @Description("Проверка успешного создания аккаунта через Email")
    @Link(name = "Документация", url = "https://app.qase.io/")
    @Severity(SeverityLevel.CRITICAL)
    public void checkSignUpWithInvalideConfirmPassword() {
        signUpPage.openPage()
                .isPageOpened()
                .checkSignUp(email, password, "")
                .assertErrorEmail("Confirm password");
    }
}
