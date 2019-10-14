import com.google.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.*;


public class Weather {
	
	private static final String APIKEY = ",us&appid=a5a9dc16705f68bff8de48b90f293cc3";
	private static final String APICALL ="http://api.openweathermap.org/data/2.5/weather?zip=";
	
	
	
	public static DATAFORMATTER getData(String local)
	{
		try {
			
			URL url = new URL(APICALL + local + APIKEY);
			
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			
			http.setRequestMethod("GET");
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
			StringBuilder result = new StringBuilder();
			
			
			String temp;
			
			while((temp = reader.readLine()) != null)
			{
				result.append(temp);
			}
			reader.close();
			
			return parseData(result.toString());
			
		} catch(Exception ex) {
			System.out.println("Couldn't connect to weather API");
		}
		return null;
		
	}
	
	public static DATAFORMATTER parseData(String json)
	{
		JsonElement jelement = new JsonParser().parse(json);
		
		JsonObject obj = jelement.getAsJsonObject();
		
		JsonObject main = obj.getAsJsonObject("main");
		
		double tempF = (9.0/5.0) * (main.get("temp").getAsDouble() - 273.15) +32;
		double hiF = (9.0/5.0) * (main.get("temp_max").getAsDouble() - 273.15) + 32;
		double loF = (9.0/5.0) * (main.get("temp_min").getAsDouble() - 273.15) + 32;
		
		String weather = obj.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
		
		String local = obj.get("name").getAsString();
		
		return new DATAFORMATTER(tempF, hiF, loF, weather, local);
	}

}
