package steps;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.startingPages.ResetPasswordPage;

@Log4j2
public class ResetPasswordStep {

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
