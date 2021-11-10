package io.digitaljourney.platform.plugins.modules.temperatureconverter.service.ri;

import org.osgi.service.metatype.annotations.Icon;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "%name", description = "%description", localization = "OSGI-INF/l10n/temperatureconverter", icon = @Icon(resource = "OSGI-INF/icon/resource.png", size = 32))
public @interface TemperatureConverterResourceConfig {

	public static final String CPID = "io.digitaljourney.platform.plugins.modules.temperatureconverter.service.ri.temperatureconverter";
}
