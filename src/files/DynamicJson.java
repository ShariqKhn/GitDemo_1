package files;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson 
{
	@Test
	public void addBook()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String responce=given().log().all().header("Content-Type","application/json")
		.body(Payload.Addbook())
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=ReUseableMethods.rawToJson(responce);
		String id=js.get("ID");
		System.out.println(id);
		
	}

}
