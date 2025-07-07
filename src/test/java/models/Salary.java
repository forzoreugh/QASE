package models;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Salary {

    @Expose
    Integer from;
    @Expose
    Integer to;
    @Expose
    String currency;
}
