import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation 
{
	
	@Test
	public void sumOfCourses()
	{
		int sum=0;
		
		JsonPath js=new JsonPath(Payload.CoursePrice());
		
		int count=js.get("courses.size()");
		
		for(int i=0; i<count; i++)
		{
			int price=js.get("courses["+i+"].price");
			int copies=js.get("courses["+i+"].copies");
			int amt=price*copies;
			
			System.out.println(amt);
			
			sum=sum+amt;
		}
		
		System.out.println("Sum of all Courses" +sum);
			
			
			
			int purchaseamt =js.get("dashborad.purchaseAmount");
			System.out.println(purchaseamt);
			
		
			
			Assert.assertEquals(sum, purchaseamt);
			
			
	
		
		
		
		
		
	}

}
