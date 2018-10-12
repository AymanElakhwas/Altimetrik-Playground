
package com.altimetrik.playground.altimetrikplayground.model;

public class Bio {

    private String published;
    private String summary;
    private String content;

    
    
    public Bio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bio( String published, String summary, String content) {
		super();
		this.published = published;
		this.summary = summary;
		this.content = content;
	}

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
}
