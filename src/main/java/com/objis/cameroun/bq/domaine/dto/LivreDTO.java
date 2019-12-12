package com.objis.cameroun.bq.domaine.dto;

import lombok.Data;

@Data
public class LivreDTO {
	private String designation;
	private String auteur;
	private String photo;
	
	
	public LivreDTO(String designation, String auteur, String photo) {
		super();
		this.designation = designation;
		this.auteur = auteur;
		this.photo = photo;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	

}
