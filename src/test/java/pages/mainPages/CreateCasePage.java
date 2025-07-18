package pages.mainPages;

import dto.CreateCase;
import pages.BasePage;
import wrappers.Input;
import wrappers.Picklist;

public class CreateCasePage extends BasePage {

    public void createTestCase(CreateCase createCase) {

        new Input("Title").write(createCase.getTitle());

        new Picklist("Status")
                .waitForVisibility()
                .select(createCase.getStatus());

        new Picklist("Priority")
                .waitForVisibility()
                .select(createCase.getPriority());

        new Picklist("Severity")
                .waitForVisibility()
                .select(createCase.getSeverity());

        new Picklist("Type")
                .waitForVisibility()
                .select(createCase.getType());

        new Picklist("Layer")
                .waitForVisibility()
                .select(createCase.getLayer());

        new Picklist("Is flaky")
                .waitForVisibility()
                .select(createCase.getIsFlaky());

        new Picklist("Behavior")
                .waitForVisibility()
                .select(createCase.getBehavior());

        new Picklist("Automation status")
                .waitForVisibility()
                .select(createCase.getAutomationStatus());
    }

    @Override
    public BasePage openPage() {
        return null;
    }

    @Override
    public BasePage isPageOpened() {
        return null;
    }
}
