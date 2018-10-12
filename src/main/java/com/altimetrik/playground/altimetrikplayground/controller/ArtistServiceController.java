package com.altimetrik.playground.altimetrikplayground.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.playground.altimetrikplayground.external.ArtistImageProxy;
import com.altimetrik.playground.altimetrikplayground.external.ArtistInfoProxy;
import com.altimetrik.playground.altimetrikplayground.external.LyricsProxy;
import com.altimetrik.playground.altimetrikplayground.external.TopTrackProxy;
import com.altimetrik.playground.altimetrikplayground.model.Artist;

@RestController
public class ArtistServiceController {

	@Autowired
	private ArtistInfoProxy artistInfoProxy;
	@Autowired
	private TopTrackProxy topTrackProxy;
	@Autowired
	private LyricsProxy lyricsProxy;
	@Autowired
	private ArtistImageProxy imageProxy;

	@GetMapping("/test")
	public void testService() {
		String imageJsonStr = this.imageProxy.getImage("Adele");
		ArtistImageProxy.mapImageToArtist(imageJsonStr, null);
	}

	@GetMapping("/get-artist/{artistName}/{country}")
	public Artist getArtistService(@PathVariable String artistName, @PathVariable String country) {
		String artistJsonStr = this.artistInfoProxy.getArtistInfo(artistName);
		Artist artist = new Artist();
		ArtistInfoProxy.mapToArtist(artistJsonStr, artist);
		String topTrackJsonStr = topTrackProxy.getTopTrackByCountry(country);
		System.out.println(topTrackJsonStr);
		TopTrackProxy.mapTopTrackToArtist(topTrackJsonStr, artist);
		if (artist.getTopTrack() != null)
			artist.setLyrics(lyricsProxy.getLyrics(artistName, artist.getTopTrack()));
		String imageJsonStr = this.imageProxy.getImage("Adele");
		ArtistImageProxy.mapImageToArtist(imageJsonStr, artist);
		return artist;
	}
}
