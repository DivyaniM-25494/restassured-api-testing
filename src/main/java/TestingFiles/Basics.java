package TestingFiles;

import Files.Payloads;
import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

// given - all input details.
//when - submit the api
//then -validate the response
public class Basics {
	 public static void main (String[] args) {
		  
		  //Validate add place api works as expected
		  
		  RestAssured.baseURI = "https://rahulshettyacademy.com";
		  String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
									.body(Payloads.addPlace()).when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
									.body("scope", equalTo("APP")).extract().response().asString();
		  
		  System.out.println("response" + response);
		  JsonPath js = new JsonPath(response); //for parsing Json
		  String place_id = js.getString("place_id");
		  System.out.println(js.getString("place_id"));
		  
		  String newAddress = "70 Summer walk, USA";
		  //Add place ->update address -> Get placeto validate if new address is present in response
		  String response1 = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
									 .body("{\n" +
												   "\"place_id\":\"" + place_id + "\",\n" +
												   "\"address\":\"" + newAddress + "\",\n" +
												   "\"key\":\"qaclick123\"\n" +
												   "}\n").when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
									 .body("msg", equalTo("Address successfully updated")).extract().response().asString();
		  
		  String getplaceRespnse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
										   .when().get("/maps/api/place/get/json").then().log().all().statusCode(200).extract().asString();
		  String actualAddress = ReusableMethods.rawToJson(getplaceRespnse).getString("address");
		  Assert.assertEquals(actualAddress, newAddress);
		  
	 }
}
