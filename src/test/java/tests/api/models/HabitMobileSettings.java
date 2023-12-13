package tests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "auth")
public class HabitMobileSettings {
    String apiId, apiToken;

    public HabitMobileSettings(String apiId, String apiToken) {
        this.apiId = apiId;
        this.apiToken = apiToken;
    }
}
