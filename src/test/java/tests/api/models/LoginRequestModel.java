package tests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequestModel {
    String username, password;
    public LoginRequestModel(String username, String password){
        this.username = username;
        this.password = password;
    }


}
