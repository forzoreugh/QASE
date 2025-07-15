package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SharedStepModels {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("steps")
    @Expose
    public List<StepModels> steps;
}
