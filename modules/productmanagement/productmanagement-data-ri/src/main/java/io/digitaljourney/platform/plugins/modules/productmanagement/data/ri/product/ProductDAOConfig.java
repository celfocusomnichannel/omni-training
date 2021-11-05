package io.digitaljourney.platform.plugins.modules.productmanagement.data.ri.product;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Icon;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "%name", description = "%description", localization = "OSGI-INF/l10n/product", icon = @Icon(resource = "OSGI-INF/icon/rdb.png", size = 32))
public @interface ProductDAOConfig {

	public static final String CPID = "io.digitaljourney.platform.plugins.modules.productmanagement.data.ri.rdb.product";

	@AttributeDefinition(name = "%provider_target.name", description = "%provider_target.description", required = true)
	String provider_target();
}