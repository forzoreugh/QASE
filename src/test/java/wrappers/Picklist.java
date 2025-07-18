package wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;  // Важный импорт!

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$;

public class Picklist {
    private final String fieldName;
    private final String selectPattern = "//label[contains(., '%s')]/following::div[@role='combobox'][1]";

    public Picklist(String fieldName) {
        this.fieldName = fieldName;
    }

    public Picklist waitForVisibility() {
        $x(String.format(selectPattern, fieldName))
                .shouldBe(visible, Duration.ofSeconds(10));  // Теперь работает
        return this;
    }

    public void select(String option) {
        String locator = String.format(selectPattern, fieldName);
        $x(locator)
                .shouldBe(visible.or(enabled), Duration.ofSeconds(5))
                .click();
        $(byText(option)).shouldBe(visible, Duration.ofSeconds(5)).click();
    }
}