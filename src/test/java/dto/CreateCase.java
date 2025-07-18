package dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateCase {

    private String title;
    private String status;
    private String severity;
    private String priority;
    private String type;
    private String layer;
    private String isFlaky;
    private String behavior;
    private String automationStatus;

    public CreateCase(String title, String status, String severity, String priority, String type, String layer,
                    String isFlaky, String behavior, String automationStatus) {
        this.title = title;
        this.status = status;
        this.severity = severity;
        this.priority = priority;
        this.type = type;
        this.layer = layer;
        this.isFlaky = isFlaky;
        this.behavior = behavior;
        this.automationStatus = automationStatus;
    }
}
