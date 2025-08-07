package TestingFiles;

import Files.Payloads;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParsing {
	 
	 public static void main (String[] args) {
		  ///////// Complex JSON Parsing and Testing //////////
		  
		  JsonPath js3 = new JsonPath(Payloads.CoursePayload());
		  int courseCount = js3.getInt("courses.size()");
		  System.out.println(courseCount);
		  int totalAmount = js3.getInt("dashboard.purchaseAmount");
		  System.out.println(totalAmount);
		  
		  String firstIndexTitle = js3.get("courses[0].title");
		  System.out.println(firstIndexTitle);
		  
		  String rpaIndexTitle = js3.get("courses[2].title");
		  System.out.println(rpaIndexTitle);
		  
		  for (int i = 0; i < courseCount; i++) {
			   System.out.println(js3.getString("courses[" + i + "].title") + ":" + js3.getString("courses[" + i + "].price"));
			   
		  }
		  
		  //RPA copies
		  for (int i = 0; i < courseCount; i++) {
			   String courseTitle = js3.getString("courses[" + i + "].title");
			   if(courseTitle.equals("RPA")) {
					System.out.println("copies sold by RPA: " + js3.getString("courses[" + i + "].copies"));
					break;
			   }
		  }
		  
	 }
}
