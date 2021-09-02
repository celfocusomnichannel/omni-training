package io.digitaljourney.platform.plugins.modules.productservice.data.api;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

import io.digitaljourney.platform.plugins.modules.productservice.entity.MusicProduct;

@ProviderType
public interface ProductDAO {
	
	List<MusicProduct> getArtistMusics(String artistName, String limit);
}
