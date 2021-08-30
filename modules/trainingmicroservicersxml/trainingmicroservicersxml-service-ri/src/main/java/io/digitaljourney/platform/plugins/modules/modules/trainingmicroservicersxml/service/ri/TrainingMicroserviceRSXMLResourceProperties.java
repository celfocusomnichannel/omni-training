package io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.service.ri;

import io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.service.api.TrainingMicroserviceRSXMLProperties;

public final class TrainingMicroserviceRSXMLResourceProperties extends TrainingMicroserviceRSXMLProperties {
	private TrainingMicroserviceRSXMLResourceProperties() {}
	
	private static final String RESOURCE_NAME = "trainingmicroservicersxml";
	
	private static final String RESOURCE_VERSION = "/v1";
	
	public static final String ADDRESS = "/" + RESOURCE_NAME + RESOURCE_VERSION;
	
	public static final String DOCS_ADDRESS = CORE_RESOURCE_PATTERN + ADDRESS + DOCS_PATH;

	public static final String PERMISSION_ALL = RESOURCE_NAME + ACTION_ALL;
	public static final String PERMISSION_CREATE = RESOURCE_NAME + ACTION_CREATE;
	public static final String PERMISSION_READ = RESOURCE_NAME + ACTION_READ;
	public static final String PERMISSION_UPDATE = RESOURCE_NAME + ACTION_UPDATE;
	public static final String PERMISSION_DELETE = RESOURCE_NAME + ACTION_DELETE;
	public static final String PERMISSION_EXECUTE = RESOURCE_NAME + ACTION_EXECUTE;
}
