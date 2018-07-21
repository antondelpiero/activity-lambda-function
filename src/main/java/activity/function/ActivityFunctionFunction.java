package activity.function;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import io.micronaut.function.FunctionBean;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@FunctionBean("activity-function")
public class ActivityFunctionFunction implements Function<String, List<Activity>> {

    @Override
    public List<Activity> apply(String input) {
        String cleanedInput = cleanUpInputJson(input);
        String month = getAllowedMonth(cleanedInput);

        return queryActivitiesForMonth(month);
    }

    private String cleanUpInputJson(String input) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(input);
            JSONObject jsonObject = (JSONObject) obj;
            return (String) jsonObject.get("month");
        } catch (Exception e) {
            return "";
        }
    }

    private String getAllowedMonth(String input) {
        if (input != null) {
            int n = Integer.parseInt(input);

            if (n > 0 && n < 13) {
                return String.valueOf(n);
            }
        }

        return String.valueOf(LocalDate.now().getMonthValue());
    }

    private List<Activity> queryActivitiesForMonth(String month) {
        AmazonDynamoDBClientBuilder clientBuilder = AmazonDynamoDBClientBuilder.standard();
        clientBuilder.setRegion("eu-central-1");
        AmazonDynamoDB client = clientBuilder.build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withN(month));

        DynamoDBQueryExpression<Activity> queryExpression = new DynamoDBQueryExpression<Activity>()
                .withKeyConditionExpression("activityMonth = :val1").withExpressionAttributeValues(eav);

        return mapper.query(Activity.class, queryExpression);
    }
}
