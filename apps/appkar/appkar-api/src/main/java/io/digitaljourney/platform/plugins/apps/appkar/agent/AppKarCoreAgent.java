package io.digitaljourney.platform.plugins.apps.appkar.agent;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

import io.digitaljourney.platform.plugins.apps.appkar.model.FlagResponseDTO;
import io.digitaljourney.platform.plugins.apps.appkar.model.MusicProductResponseDTO;

@ProviderType
public interface AppKarCoreAgent {
	public FlagResponseDTO getFlag(String alphaCode);
	public List<MusicProductResponseDTO> getArtistMusics(String artistName, String limit); 
}
