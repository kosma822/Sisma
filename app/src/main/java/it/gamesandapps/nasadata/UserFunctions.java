package it.gamesandapps.nasadata;

public class UserFunctions {

	private JSONParser jsonParser;

	public UserFunctions() {
		jsonParser = new JSONParser();
	}

	public String getEarthquakes(String url){
		return jsonParser.simpleRequest(url, "GET");
	}
}