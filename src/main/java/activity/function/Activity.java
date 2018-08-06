package activity.function;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "activity")
public class Activity {

    @DynamoDBHashKey
    private int activityMonth;
    @DynamoDBAttribute(attributeName = "activityNameAndWeek")
    private String activityKey;
    @DynamoDBAttribute(attributeName = "name")
    private String activity;
    @DynamoDBAttribute
    private String activityDate;
    @DynamoDBAttribute
    private String activityTime;
    @DynamoDBAttribute
    private String location;
    @DynamoDBAttribute
    private String speaker;
    @DynamoDBAttribute
    private String additionalInfos;

    public int getActivityMonth() {
        return activityMonth;
    }

    public void setActivityMonth(int activityMonth) {
        this.activityMonth = activityMonth;
    }

    public String getActivityKey() {
        return activityKey;
    }

    public void setActivityKey(String activityKey) {
        this.activityKey = activityKey;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getAdditionalInfos() {
        return additionalInfos;
    }

    public void setAdditionalInfos(String additionalInfos) {
        this.additionalInfos = additionalInfos;
    }
}
