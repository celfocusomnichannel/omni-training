package io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.ri;

import org.osgi.service.metatype.annotations.Icon;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "%name", description = "%description", localization = "OSGI-INF/l10n/flagmicroservicersxml", icon = @Icon(resource = "OSGI-INF/icon/resource.png", size = 32))
public @interface FlagMicroServiceRSXMLResourceConfig { 	
	public static final String CPID = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.ri.flagmicroservicersxml";
}
