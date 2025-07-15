package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DefectModels {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("actual_result")
    @Expose
    public String actualResult;
    @SerializedName("severity")
    @Expose
    public Integer severity;
}
