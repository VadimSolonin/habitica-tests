package tests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagResponseModel {
    Boolean success;
    List<TagDataModel> data;
    List<NotificationsModel> notifications;

}
