package io.digitaljourney.platform.plugins.apps.genericappkar.common.api.facade;

import java.util.List;

import io.digitaljourney.platform.plugins.apps.genericappkar.model.FlagResponseDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.model.MusicProductResponseDTO;

public interface GenericAppKarFacade {
	public FlagResponseDTO getFlag(String isoCode);
	public List<MusicProductResponseDTO> getArtistMusics(String artistName, String limit);
}
