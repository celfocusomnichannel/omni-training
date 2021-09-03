package io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.api;

import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface FlagServiceDAO {
	
	public String getFlag(String alphaCode);
	
}