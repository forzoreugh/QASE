package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Project {

    @Builder.Default
    private final String projectName = "";
    @Builder.Default
    private final String projectCode = "";
    @Builder.Default
    private final String description = "";

    // Project access type
    @Builder.Default
    private final boolean isPrivate = false;
    @Builder.Default
    private final boolean isPublic = false;

    // Member access
    @Builder.Default
    private final boolean addAllMembers = false;
    @Builder.Default
    private final boolean groupAccess = false;
    @Builder.Default
    private final boolean dontAddMembers = false;
}
