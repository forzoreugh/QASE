package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class VacancyResponse extends Vacancy {

    @Expose
    @SerializedName("found")
    int found;
    @Expose
    @SerializedName("pages")
    int pages;
    @Expose
    @SerializedName("items")
    List<Vacancy> items;
}
