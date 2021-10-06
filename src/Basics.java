import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.Payload;
import files.ReUseableMethods;

public class Basics 
{
	
	// POST Request to the 
	public static void main(String[] args) throws Exception 
	{
		//given  -  All input details in "given()" section
		//when  -  Submit the API (resource URI , http method(GET,POST))
		//then  - validate the response 
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		 String responce = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
         .body(new String(Files.readAllBytes(Paths.get("C:\\Users\\mohd.shariq2\\eclipse-workspace\\DemoProject\\Files\\addPlace.json"))))			 
		//.body(Payload.AddPlace())
         .when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200)
		.body("scope",equalTo("APP"))
		.header("Server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
		 
		 System.out.println(responce);
		 
		 JsonPath js=new JsonPath(responce);
		 String placeId=js.get("place_id");
		 System.out.println("place_id :"+placeId);
		 
		 //Update Place
		 
		 
		String newAddress="70 Summer walk, Africa";
		 
		 given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		 .body("{\r\n"
		 		+ "\"place_id\":\""+placeId+"\",\r\n"
		 		+ "\"address\":\""+newAddress+"\",\r\n"
		 		+ "\"key\":\"qaclick123\"\r\n"
		 		+ "}\r\n"
		 		+ "")
		 .when().put("/maps/api/place/update/json")
		 .then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
		 
		 // Get Place
		 
		  String getPlaceResponce= given().log().all().queryParam("key", "qaclick123")
		 .queryParam("place_id", placeId)
		 .when().get("/maps/api/place/get/json")
		 .then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		
		 JsonPath js1=ReUseableMethods.rawToJson(getPlaceResponce);
		  
	     //JsonPath js1=new JsonPath(getPlaceResponce);
	     String actualaddress=js1.getString("address");
	     System.out.println(actualaddress);
	     
	     Assert.assertEquals(actualaddress,newAddress );
			
		 
		
	}

}