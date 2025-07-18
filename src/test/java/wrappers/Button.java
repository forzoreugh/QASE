package wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class Button {
    private final String buttonText;
    private final String baseLocator = "//span[text()='%s']";

    public Button(String buttonText) {
        this.buttonText = buttonText;
    }

    public Button click() {
        getButtonElement()
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldBe(Condition.enabled, Duration.ofSeconds(10))
                .click();
        return this;
    }

    public Button shouldBeVisible() {
        getButtonElement().shouldBe(Condition.visible, Duration.ofSeconds(10));
        return this;
    }

    public Button shouldBeEnabled() {
        getButtonElement().shouldBe(Condition.enabled, Duration.ofSeconds(10));
        return this;
    }

    public Button shouldHaveText(String expectedText) {
        getButtonElement().shouldHave(Condition.exactText(expectedText));
        return this;
    }

    private SelenideElement getButtonElement() {
        return $x(String.format(baseLocator, buttonText));
    }

    // Альтернативные локаторы для сложных случаев
    private SelenideElement getButtonElementWithFallback() {
        String[] locators = {
                String.format(baseLocator, buttonText),
                String.format("//button[contains(., '%s')]", buttonText),
                String.format("//a[contains(., '%s')]", buttonText),
                String.format("//div[contains(@class, 'btn') and contains(., '%s')]", buttonText)
        };

        for (String locator : locators) {
            SelenideElement element = $x(locator);
            if (element.exists() && element.isDisplayed()) {
                return element;
            }
        }
        return $x(locators[0]);
    }
}