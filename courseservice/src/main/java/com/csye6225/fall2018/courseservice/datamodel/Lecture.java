package com.csye6225.fall2018.courseservice.datamodel;

import java.util.*;

public class Lecture {
	private List<String> notesURL;
	private String associatedMaterialURL;
	
	public Lecture(List<String> notesURL, String associatedMaterialURL) {
		this.notesURL = notesURL;
		this.associatedMaterialURL = associatedMaterialURL;
	}

	public List<String> getNotesURL() {
		return notesURL;
	}

	public void setNotesURL(List<String> notesURL) {
		this.notesURL = notesURL;
	}

	public String getAssociatedMaterialURL() {
		return associatedMaterialURL;
	}

	public void setAssociatedMaterialURL(String associatedMaterialURL) {
		this.associatedMaterialURL = associatedMaterialURL;
	}
	
}
