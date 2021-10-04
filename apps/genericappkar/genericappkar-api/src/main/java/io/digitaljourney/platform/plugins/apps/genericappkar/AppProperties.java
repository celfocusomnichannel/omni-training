package io.digitaljourney.platform.plugins.apps.genericappkar;

import io.digitaljourney.platform.modules.ws.rs.api.RSProperties;

/**
 * GenericAppKar properties	
 */
public final class AppProperties extends RSProperties {
	
	private AppProperties() {}

	public static final String APP_NAME = "genericappkar";
	public static final String CURRENT_VERSION = "1";
	public static final String APP_VERSION = "/v" + CURRENT_VERSION;
	public static final String ADDRESS = "/" + APP_NAME + APP_VERSION;
	
	public static final String DOCS_ADDRESS = CMS_RESOURCE_PATTERN + ADDRESS + DOCS_PATH;

	public static final String PERMISSION_ALL = APP_NAME + ACTION_ALL;
	public static final String PERMISSION_CREATE = APP_NAME + ACTION_CREATE;
	public static final String PERMISSION_READ = APP_NAME + ACTION_READ;
	public static final String PERMISSION_UPDATE = APP_NAME + ACTION_UPDATE;
	public static final String PERMISSION_DELETE = APP_NAME + ACTION_DELETE;
	public static final String PERMISSION_EXECUTE = APP_NAME + ACTION_EXECUTE;
	
	public static final String GENERICAPPKAR_TAG = APP_NAME;

	/** General Exception error code */
	public static final String GENERICAPPKAR000 = "GENERICAPPKAR000";
	
	/** Cache **/
	public static final String GENERICAPPKAR_CACHE = "genericappkarcache";
}
