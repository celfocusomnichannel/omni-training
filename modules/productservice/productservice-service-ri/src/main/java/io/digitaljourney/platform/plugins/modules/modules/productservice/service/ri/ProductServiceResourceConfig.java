package io.digitaljourney.platform.plugins.modules.modules.productservice.service.ri;

import org.osgi.service.metatype.annotations.Icon;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "%name", description = "%description", localization = "OSGI-INF/l10n/productservice", icon = @Icon(resource = "OSGI-INF/icon/resource.png", size = 32))
public @interface ProductServiceResourceConfig { 	
	public static final String CPID = "platform.modules.productservice.service.ri.productservice";
}
