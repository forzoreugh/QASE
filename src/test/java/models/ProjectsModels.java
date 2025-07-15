package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectsModels {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("access")
    @Expose
    public String access;
    @SerializedName("group")
    @Expose
    public String group;
}
