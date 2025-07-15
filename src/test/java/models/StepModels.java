package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StepModels {

    @SerializedName("hash")
    @Expose
    public String hash;
    @SerializedName("action")
    @Expose
    public String action;
    @SerializedName("expected_result")
    @Expose
    public String expectedResult;
    @SerializedName("data")
    @Expose
    public String data;
}
