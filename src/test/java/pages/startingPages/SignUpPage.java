package pages.startingPages;

import pages.BasePage;

import static com.codeborne.selenide.Selenide.open;

public class SignUpPage extends BasePage {

    @Override
    public SignUpPage openPage() {
        open("/signup");
        return this;
    }

    @Override
    public SignUpPage isPageOpened() {

        return this;
    }
}
