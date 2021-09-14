package io.digitaljourney.platform.plugins.apps.appkar.common.api.facade;

import java.util.List;

import io.digitaljourney.platform.plugins.apps.appkar.model.FlagResponseDTO;
import io.digitaljourney.platform.plugins.apps.appkar.model.MusicProductResponseDTO;

public interface AppKarFacade {

	
	public FlagResponseDTO getFlag(String isoCode);
	
	public List<MusicProductResponseDTO> getArtistMusics(String artistName, String limit);
}
