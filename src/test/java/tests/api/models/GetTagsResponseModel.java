package tests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTagsResponseModel {
    Boolean success;
    List<TagDataModel> data;
    List<NotificationsModel> notifications;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TagDataModel {
        String name, id;
        Boolean challenge;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NotificationsModel {

    }

}
