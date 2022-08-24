package dataAccess;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Reader {
	
	private String path;
	
	public Reader(String path) {
		this.path = path;
	}
	
	public JSONObject getJson() {
		JSONParser parser = new JSONParser();
		JSONObject jsonContent = null;
		try {
			Object obj = parser.parse(new FileReader(this.path));
			jsonContent = (JSONObject) obj;
		}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		catch (ParseException e) {e.printStackTrace();}
		catch (Exception e) {e.printStackTrace();}
		
		return jsonContent;
	}

}
