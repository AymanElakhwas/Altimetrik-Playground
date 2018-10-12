package com.altimetrik.playground.altimetrikplayground.external;

import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;
import org.springframework.stereotype.Component;

@Component
public class LyricsProxy {

	private final static String apiKey = "e612157c8a69da7e305201492218ecd8";

	public String getLyrics(String artistName, String trackName) {
		MusixMatch musixMatch = new MusixMatch(apiKey);

		Track track;
		Lyrics lyrics = null;
		try {
			track = musixMatch.getMatchingTrack(trackName, artistName);
			lyrics = musixMatch.getLyrics(track.getTrack().getTrackId());
		} catch (MusixMatchException e) {
			throw new GenericException(e);
		}
		String lyricsStr = lyrics.getLyricsBody();
		return lyricsStr;
	}

}
