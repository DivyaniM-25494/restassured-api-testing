package TestingFiles;

import Files.Payloads;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {
	 
	 @Test
	 public void sumValidation () {
		  
		  JsonPath js3 = new JsonPath(Payloads.CoursePayload());
		  int courseCount = js3.getInt("courses.size()");
		  System.out.println(courseCount);
		  int totalAmount = js3.getInt("dashboard.purchaseAmount");
		  System.out.println(totalAmount);
		  
		  //Asserting total price equal to purchase amount
		  int totalsum = 0;
		  for (int i = 0; i < courseCount; i++) {
			   totalsum += (js3.getInt("courses[" + i + "].price")) * (js3.getInt("courses[" + i + "].copies"));
			   
		  }
		  
		  Assert.assertEquals(totalAmount, totalsum);
	 }
}
