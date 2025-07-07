package models;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Vacancy {

    @Expose
    String name;
    @Expose
    Salary salary;
}
