package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DEL_DeleteAProduct {

	@Test
	public void createAProduct() {

		String payloadPath = "./src/main/java/data/deletePayload.json";
		

		Response response = 
				given()
					.log().all()//prints out all the info for u to see for given
					.baseUri("https://techfios.com/api-prod/api/product")
					.header("Content-Type", "application/json;")
					.body(new File (payloadPath))
				.when()
					.log().all() //prints out all the info for u to see when
					.delete("/delete.php")
				.then()
					.log().all()//prints out all the info for u to see for then
					.extract().response();

		int statusCode = response.getStatusCode();
		System.out.println("status code:" + statusCode);
		Assert.assertEquals(statusCode, 200);

		long actualResponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("response time " + actualResponseTime);

		if (actualResponseTime <= 2000) {
			System.out.println("time is within range");
		} else {
			System.out.println("time is out of range");
		}

		String responseBody = response.getBody().asString();
		System.out.println("response body" + responseBody);

		JsonPath jp = new JsonPath(responseBody);
		String successMessage = jp.getString("message");
		System.out.println("successMessage" + successMessage);

		Assert.assertEquals(successMessage, "Product was deleted.");

	}

}
