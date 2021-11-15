package io.digitaljourney.platform.plugins.modules.temperatureconverter.data.ri;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Icon;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "%name", description = "%description",
localization = "OSGI-INF/l10n/temperatureconverter", icon = @Icon(resource = "OSGI-INF/icon/ws.png", size = 32))
public @interface TemperatureConverterDAOConfig {

	public static final String CPID = "io.digitaljourney.platform.plugins.modules.temperatureconverter.data.ri.temperatureservice";

	@AttributeDefinition(name = "%wsdlLocation.name", description = "%wsdlLocation.description")
	String wsdlLocation();

	@AttributeDefinition(name = "%address.name", description = "%address.description")
	String address();

	@AttributeDefinition(name = "%namespaceUri.name", description = "%namespaceUri.description")
	String namespaceUri();

	@AttributeDefinition(name = "%serviceName.name", description = "%serviceName.description")
	String serviceName();
}
