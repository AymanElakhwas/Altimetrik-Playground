package com.altimetrik.playground.altimetrikplayground.external;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.altimetrik.playground.altimetrikplayground.common.GenericException;
import com.altimetrik.playground.altimetrikplayground.model.Artist;
import com.altimetrik.playground.altimetrikplayground.model.Bio;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ArtistInfoProxy {

	private static final String API_KEY = "api_key=dc12771deb52f29732290da83756eaf5";
	private static final String FORMAT = "format=json";
	private static final String SERVICE_URL = "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo";

	@Autowired
	private RestOperations restTemplate;

	public String getArtistInfo(String artistName) {
		String url = SERVICE_URL + "&artist=" + artistName + "&" + API_KEY + "&" + FORMAT;
		System.out.println(url);
		ResponseEntity<String> recipesResponse = this.restTemplate.getForEntity(url, String.class);
		return recipesResponse.getBody();
	}

	public static void mapToArtist(String jsonStr, Artist artist) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = null;
		try {
			rootNode = objectMapper.readTree(jsonStr);
		} catch (IOException e) {
			throw new GenericException(e);
		}

		if (rootNode != null) {

			artist.setName(rootNode.get("artist").get("name").asText());
			JsonNode bioNode = rootNode.get("artist").get("bio");
			if (artist.getBio() == null)
				artist.setBio(new Bio());
			artist.getBio().setPublished(bioNode.get("published").asText());
			artist.getBio().setSummary(bioNode.get("summary").asText());
			artist.getBio().setContent(bioNode.get("content").asText());
		}

	}
}
