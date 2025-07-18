package wrappers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TextArea {
    private final String fieldName;

    public TextArea(String fieldName) {
        this.fieldName = fieldName;
    }

    public TextArea write(String text) {
        if ("Description".equalsIgnoreCase(fieldName)) {
            // Для поля Description используем специальный подход
            writeToMarkdownEditor(text);
        } else {
            // Для других текстовых полей
            writeToRegularTextArea(text);
        }
        return this;
    }

    private void writeToMarkdownEditor(String text) {
        // Локатор контейнера редактора
        SelenideElement editorContainer = $("div[data-editor-for='description']");
        editorContainer.shouldBe(visible, Duration.ofSeconds(10));

        // Кликаем в область редактирования
        SelenideElement editor = editorContainer.$(".ProseMirror");
        editor.click();

        // Очищаем содержимое
        Selenide.actions()
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .perform();

        // Вводим текст
        editor.sendKeys(text);
    }

    private void writeToRegularTextArea(String text) {
        // Универсальный локатор для обычных текстовых областей
        String xpath = String.format(
                "//label[contains(., '%s')]/following-sibling::div//textarea",
                fieldName.replace("'", "\\'")
        );

        SelenideElement element = $x(xpath)
                .shouldBe(visible, Duration.ofSeconds(10));

        element.clear();
        element.sendKeys(text);
    }
}