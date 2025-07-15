package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomFieldModels {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("entity")
    @Expose
    public Integer entity;
    @SerializedName("type")
    @Expose
    public Integer type;
    @SerializedName("placeholder")
    @Expose
    public String placeholder;
    @SerializedName("default_value")
    @Expose
    public String defaultValue;
    @SerializedName("is_filterable")
    @Expose
    public Boolean isFilterable;
    @SerializedName("is_visible")
    @Expose
    public Boolean isVisible;
    @SerializedName("is_required")
    @Expose
    public Boolean isRequired;
    @SerializedName("is_enabled_for_all_projects")
    @Expose
    public Boolean isEnabledForAllProjects;
}
