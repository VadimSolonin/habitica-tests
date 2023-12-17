package tests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteUserTaskResponseModel {
    Boolean success;
    TaskData data;
    List<UserNotifications> notifications;
    int userV;
    String appVersion;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TaskData{

    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserNotifications{

    }
}
