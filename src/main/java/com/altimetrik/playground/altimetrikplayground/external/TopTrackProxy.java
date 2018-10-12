package com.altimetrik.playground.altimetrikplayground.external;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.altimetrik.playground.altimetrikplayground.model.Artist;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TopTrackProxy {
	private static final String API_KEY = "api_key=dc12771deb52f29732290da83756eaf5";
	private static final String FORMAT = "format=json";
	private static final String SERVICE_URL = "http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks";

	@Autowired
	private RestOperations restTemplate;

	public String getTopTrackByCountry(String country) {
		String url = SERVICE_URL + "&country=" + country + "&" + API_KEY + "&" + FORMAT;
		ResponseEntity<String> recipesResponse = this.restTemplate.getForEntity(url, String.class);
		return recipesResponse.getBody();
	}

	public static void mapTopTrackToArtist(String jsonStr, Artist artist) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = null;
		try {
			rootNode = objectMapper.readTree(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (rootNode != null) {
			Iterator<JsonNode> iter = rootNode.get("tracks").get("track").elements();
			while(iter.hasNext()) {
				JsonNode trackNode = iter.next();
				System.out.println(trackNode.get("artist").get("name").asText());
				if(artist.getName().equals(trackNode.get("artist").get("name").asText())){
					artist.setTopTrack(trackNode.get("name").asText());
				}
			}
		}

	}
}
