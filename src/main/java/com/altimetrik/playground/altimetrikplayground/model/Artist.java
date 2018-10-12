package com.altimetrik.playground.altimetrikplayground.model;

public class Artist {

	private String name;
	private Bio bio;
	private String topTrack;
	private String lyrics;
	private String imageUrl;

	public Artist() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Bio getBio() {
		return bio;
	}

	public void setBio(Bio bio) {
		this.bio = bio;
	}

	public void setTopTrack(String topTrack) {
		this.topTrack = topTrack;
	}

	public String getTopTrack() {
		return topTrack;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
