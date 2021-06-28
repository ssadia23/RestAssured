package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class GET_ReadAProduct {

	@Test
	public void readaProducts() {
		
		//https://techfios.com/api-prod/api/product/read_one.php?id=110
		
		Response response = 
				given()
					.baseUri("https://techfios.com/api-prod/api/product")
					.header("Content-Type", "application/json; charset=UTF-8")
					.queryParam("id", "1685")
				.when()
					.get("/read_one.php")
				.then()
					.extract().response();
		
		int statusCode = response.getStatusCode();
		System.out.println("status code:" + statusCode);
		Assert.assertEquals(statusCode, 200);
		//SoftAssert softAssert = 
	
		
		long actualResponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("response time " + actualResponseTime);

		if(actualResponseTime<=200) {
			System.out.println("time is within range");
		}else {
			System.out.println("time is out of range");
		}
		
		
		String responseBody = response.getBody().asString();
		System.out.println("response body" + responseBody);
		
		JsonPath jp = new JsonPath(responseBody);
		String productName = jp.getString("name");
		System.out.println("product name " + productName);
		
		Assert.assertEquals(productName, "iPhone 13.0");
		
		String productDecription = jp.getString("description");
		System.out.println("product description " + productDecription);
		
		Assert.assertEquals(productDecription, "The super phone");
		
		String productPrice = jp.getString("price");
		System.out.println("product price " + productPrice);
		
		Assert.assertEquals(productPrice, "199");
	}
	
	
}
