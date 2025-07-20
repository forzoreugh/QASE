package tests.ui;

import dto.CreateCase;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import wrappers.Button;

public class CreateCaseTest extends BaseTest {

    @Test(testName = "Открытие формы создания тест-кейса", priority = 1)
    @Epic("Проект")
    @Feature("Открытие формы создания тест-кейса")
    @Story("Отображение формы тест-кейса")
    @Description("Проверка успешного открытия тест-кейса")
    @Link(name = "Документация", url = "https://app.qase.io/")
    @Severity(SeverityLevel.CRITICAL)
    public void checkOpenFormCreateCase() {
        loginStep.login(email, password, true)
                .openProject("ARTEM");
        projectPage.choiceButton("New test");
        projectPage.assertOpenCreateCaseForm();
    }

    @Test(testName = "Создание тест-кейса", priority = 2,
            description = "Корректное добавление тест-кейса в в количестве")
    @Epic("Проект")
    @Feature("Добавление тест-кейса")
    @Story("Отображение тест-кейса в проекте")
    @Description("Проверка успешного добавления тест-кейса в проект")
    @Link(name = "Документация", url = "https://app.qase.io/")
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
                        .openProject("ARTEM");
        projectPage.choiceButton("New test");
        createCasePage.createTestCase(createCase);
        new Button("Save").click();
        projectPage.assertSaveCase();
    }

    @Test(testName = "Отмена создания тест-кейса", priority = 3)
    @Epic("Проект")
    @Feature("Отмена создания тест-кейса")
    @Story("Отображение закрытия формы создания тест-кейса")
    @Description("Проверка успешного открытия тест-кейса")
    @Link(name = "Документация", url = "https://app.qase.io/")
    @Severity(SeverityLevel.CRITICAL)
    public void cancelCreateTestCase() {
        loginStep.login(email, password, true)
                .openProject("ARTEM");
        projectPage.choiceButton("New test");
        new Button("Cancel").click();
        projectPage.assertOpenPage();
    }
}
