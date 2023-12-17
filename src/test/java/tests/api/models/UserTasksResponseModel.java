package tests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTasksResponseModel {
    Boolean success;
    List<UserTasksModel> data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserTasksModel {
        Challenge challenge;
        Group group;
        Boolean up, down, byHabitica;
        int counterUp, counterDown, value, priority;
        String text, frequency, userId, id, type, notes, attribute, createdAt, updatedAt, _id;
        List<History> history;
        List<Tags> tags;
        List<Reminders> reminders;
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Challenge{

        }
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Group{

        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class History{

        }
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Tags{

        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Reminders{

        }
    }
}
