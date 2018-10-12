package com.altimetrik.playground.altimetrikplayground.external;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.altimetrik.playground.altimetrikplayground.common.GenericException;
import com.altimetrik.playground.altimetrikplayground.model.Artist;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ArtistImageProxy {

	private static final String API_KEY = "AIzaSyCLpbTNBmaCOFgIE2YQiI8uRNc1BcS_ZVU";
	private static final String SEARCH_URL = "https://www.googleapis.com/customsearch/v1?key=" + API_KEY
			+ "&cx=011207550249162460478:9pj1pqr3mko&tbm=isch";

	@Autowired
	private RestOperations restTemplate;

	public String getImage(String artistName) {
		String url = SEARCH_URL + "&q=" + artistName;
		ResponseEntity<String> recipesResponse = this.restTemplate.getForEntity(url, String.class);
		return recipesResponse.getBody();
	}

	public static void mapImageToArtist(String jsonStr, Artist artist) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = null;
		try {
			rootNode = objectMapper.readTree(jsonStr);
		} catch (IOException e) {
			throw new GenericException(e);
		}
		if (rootNode != null) {
			JsonNode node = rootNode.findValue("cse_image");
			artist.setImageUrl(node.get(0).get("src").asText());
		}
	}

}
