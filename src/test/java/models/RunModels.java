package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RunModels {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("include_all_cases")
    @Expose
    public Boolean includeAllCases;
    @SerializedName("is_autotest")
    @Expose
    public Boolean isAutotest;
}
