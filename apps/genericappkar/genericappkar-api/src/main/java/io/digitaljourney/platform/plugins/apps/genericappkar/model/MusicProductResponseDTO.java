package io.digitaljourney.platform.plugins.apps.genericappkar.model;

import java.io.Serializable;

import org.osgi.dto.DTO;

@SuppressWarnings("serial")
public class MusicProductResponseDTO extends DTO implements Serializable  {

	public String artistName;
	public String collectionName;
	public String kind;
	public String trackName;
	public String trackViewUrl;
	public String artworkUrl100;
	public String country;
	public String primaryGenreName;
}