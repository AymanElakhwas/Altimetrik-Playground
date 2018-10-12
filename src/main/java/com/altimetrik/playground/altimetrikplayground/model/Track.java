package com.altimetrik.playground.altimetrikplayground.model;

public class Track {
	private String trackName;

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public Track(String trackName, int rank) {
		super();
		this.trackName = trackName;
	}

	public Track() {
		super();
	}

}
