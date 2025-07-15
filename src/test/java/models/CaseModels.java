package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaseModels {

    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("preconditions")
    @Expose
    public String preconditions;
    @SerializedName("postconditions")
    @Expose
    public String postconditions;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("severity")
    @Expose
    public Integer severity;
    @SerializedName("priority")
    @Expose
    public Integer priority;
    @SerializedName("behavior")
    @Expose
    public Integer behavior;
    @SerializedName("type")
    @Expose
    public Integer type;
    @SerializedName("layer")
    @Expose
    public Integer layer;
    @SerializedName("automation")
    @Expose
    public Integer automation;
    @SerializedName("status")
    @Expose
    public Integer status;
}
