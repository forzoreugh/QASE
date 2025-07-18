package tests.ui;

import dto.CreateCase;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import wrappers.Button;

import static com.codeborne.selenide.Selenide.sleep;

public class CreateCaseTest extends BaseTest {

    @Test(testName = "Открытие формы создания тест-кейса", priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    public void checkOpenFormCreateCase() {
        loginStep.login(email, password, true)
                .openProject("ffff");
        projectPage.choiceButton("New test");
        projectPage.assertOpenCreateCaseForm();
    }

    @Test(testName = "Создание тест-кейса", priority = 2)
    @Severity(SeverityLevel.BLOCKER)
    public void createTestCase() {
        CreateCase createCase = CreateCase.builder()
                .title("Тест")
                .status("Draft")
                .priority("Low")
                .severity("Major")
                .type("Smoke")
                .layer("API")
                .isFlaky("Yes")
                .behavior("Positive")
                .automationStatus("Manual")
                .build();

        loginStep.login(email, password, true)
                        .openProject("ffff");
        projectPage.choiceButton("New test");
        createCasePage.createTestCase(createCase);
        new Button("Save").click();
        projectPage.assertSaveCase();
    }

    @Test(testName = "Отмена создания тест-кейса", priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    public void cancelCreateTestCase() {
        loginStep.login(email, password, true)
                .openProject("ffff");
        projectPage.choiceButton("New test");
        new Button("Cancel").click();
        projectPage.assertOpenPage();
    }

    @DataProvider(name = "Позитивные тесты для заполнения тест-кейса")
    public Object[][] valideCheckoutInformationData() {
        return new Object[][]{
                {"Artem", "Svidzinski", "17"},
                {"Марина", "Проверкина", "222"},
        };
    }
}
