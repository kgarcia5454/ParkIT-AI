import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

public class Main {

	public static void main(String[] args) {
		DynamoDbClient.builder().region(Region.US_EAST_1).build();
		System.out.println("Hello World");

	}

}
