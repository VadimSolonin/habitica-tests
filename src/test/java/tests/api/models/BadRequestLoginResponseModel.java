package tests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BadRequestLoginResponseModel {
    Boolean success;
    String error, message;
    List<LoginErrors> errors;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LoginErrors{
        String message, param, value;
    }
}
