package files;

import io.restassured.path.json.JsonPath;

public class ReUseableMethods 
{
	public static JsonPath rawToJson(String responce)
	{
		JsonPath js1=new JsonPath(responce);
		return js1;
	}

}
