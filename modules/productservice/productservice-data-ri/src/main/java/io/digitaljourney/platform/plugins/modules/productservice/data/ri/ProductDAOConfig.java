package io.digitaljourney.platform.plugins.modules.productservice.data.ri;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Icon;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "%name", description = "%description", localization = "OSGI-INF/l10n/product",
icon = @Icon(resource = "OSGI-INF/icon/ws.png", size = 32))
public @interface ProductDAOConfig {

	public static final String CPID = "platform.modules.productservice.data.ri.productservice";
	
	@AttributeDefinition(name = "%address.name", description = "%address.description")
	String address();
}