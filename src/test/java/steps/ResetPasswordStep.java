package steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.startingPages.ResetPasswordPage;

public class ResetPasswordStep {

    private static final Logger log = LogManager.getLogger(LoginStep.class);
    ResetPasswordPage resetPasswordPage;

    public ResetPasswordStep() {
        resetPasswordPage = new ResetPasswordPage();
    }

    public ResetPasswordStep resetPassword(String email, String allert) {
        resetPasswordPage.openPage()
                .sendPassword(email)
                .assertAllertMessage(allert);
        return this;
    }
}
