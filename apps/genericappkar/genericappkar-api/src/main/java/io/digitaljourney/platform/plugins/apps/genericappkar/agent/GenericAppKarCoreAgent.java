package io.digitaljourney.platform.plugins.apps.genericappkar.agent;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

import io.digitaljourney.platform.plugins.apps.genericappkar.model.FlagResponseDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.model.MusicProductResponseDTO;

@ProviderType
public interface GenericAppKarCoreAgent {
	public FlagResponseDTO getFlag(String alphaCode);
	public List<MusicProductResponseDTO> getArtistMusics(String artistName, String limit); 
}
