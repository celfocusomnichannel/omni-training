package io.digitaljourney.platform.plugins.modules.temperatureconverter.service.ri;

import io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.TemperatureConverterProperties;

public final class TemperatureConverterResourceProperties extends TemperatureConverterProperties {
	private TemperatureConverterResourceProperties() {}

	private static final String RESOURCE_NAME = "temperatureconverter";

	public static final String ADDRESS = "/" + RESOURCE_NAME;

	public static final String DOCS_ADDRESS = CORE_RESOURCE_PATTERN + ADDRESS + DOCS_PATH;

	public static final String PERMISSION_ALL = RESOURCE_NAME + ACTION_ALL;
	public static final String PERMISSION_CREATE = RESOURCE_NAME + ACTION_CREATE;
	public static final String PERMISSION_READ = RESOURCE_NAME + ACTION_READ;
	public static final String PERMISSION_UPDATE = RESOURCE_NAME + ACTION_UPDATE;
	public static final String PERMISSION_DELETE = RESOURCE_NAME + ACTION_DELETE;
	public static final String PERMISSION_EXECUTE = RESOURCE_NAME + ACTION_EXECUTE;
}
