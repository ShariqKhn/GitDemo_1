import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse 
{
	public static void main(String[] args) 
	{
		 JsonPath js=new JsonPath(Payload.CoursePrice());
		 
		 // print the number of courses in API
		 
		 int count=js.getInt("courses.size()");
		 System.out.println("Total Courses-->" + count);
		 
		 // print the purchase amount
		 
		 
		 int amount =js.getInt("dashborad.purchaseAmount");
		 System.out.println(amount);
		 
		 //print the title of first course 
		 
		 String title=js.get("courses[0].title");
		 System.out.println(title);
		 
		 // Print all course title and their respective prices
		 
		 for(int i=0; i<count; i++)
		 {
			String CourseTitles=js.get("courses["+i+"].title");
			int price=js.get("courses["+i+"].price");
			
			System.out.println(CourseTitles +" : "+price );
			 
		 }
		 
		 System.out.println("-------------------------------------");
		 
		 
		 for(int i=0; i<count; i++)
		 {
			String CourseTitles=js.get("courses["+i+"].title");
			
			if(CourseTitles.equalsIgnoreCase("RPA"))
			{
				int copies= js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
			
			 
		 }
		 
		 
		 
		 
		 
		 
		
	}

}
