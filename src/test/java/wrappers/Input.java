package wrappers;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.$x;

public class Input {
    private final String fieldName;

    public Input(String fieldName) {
        this.fieldName = fieldName;
    }

    public Input write(String text) {
        String sanitizedFieldName = fieldName.replace("'", "\\'");

        String xpath = String.format(
                "//label[contains(., '%s')]/following-sibling::div//input",
                sanitizedFieldName
        );

        SelenideElement element = $x(xpath)
                .shouldBe(Condition.hidden, Duration.ofSeconds(10));

        element.sendKeys(text);
        return this;
    }
}