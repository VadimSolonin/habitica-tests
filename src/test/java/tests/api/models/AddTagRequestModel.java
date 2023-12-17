package tests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddTagRequestModel {
    String name;
    public AddTagRequestModel(String name){
        this.name = name;
    }
}
