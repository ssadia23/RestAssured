package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GET_ReadAllProducts {

	@Test
	public void readAllProducts() {
		
//https://techfios.com/api-prod/api/product
//		/read.php	
		
		Response response = 
				given()
					.baseUri("https://techfios.com/api-prod/api/product")
					.header("Content-Type", "application/json; charset=UTF-8")
				.when()
					.get("/read.php	")
				.then()
					.extract().response();
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
//		System.out.println("status code:" + statusCode);
//		System.out.println(response.asString());
		
		String responseBody = response.getBody().prettyPrint();
		System.out.println("response body" + responseBody);
		
	}
	
	
}
