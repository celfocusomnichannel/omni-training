/*-
 * #%L
 * Apps :: Training JWE Order Management App :: API
 * %%
 * Copyright (C) 2016 - 2021 Digital Journey
 * %%
 * All rights reserved. This software is protected under several
 * Laws in various countries. All content, layout, design of this document are the
 * intellectual property of Digital Journey, Novabase Business Solutions S.A. 
 * and its licensors. The disclosure,copying, adaptation, citation, transcription, 
 * translation, modification, decompilation, reverse engineering, derivatives, 
 * integration, development and/or any other form of total or partial use of the 
 * content of this document and/or accessible through or via the contents, by any 
 * possible means without the respective authorization or licensing by the owner of 
 * the intellectual property rights is prohibited, the offenders being subject to civil 
 * and/or criminal prosecution and liability. The user or licensee of all or part of this 
 * document by any means may only use the document under the terms and conditions agreed
 * upon with the owner of the intellectual property rights, and for the purposes
 * justifying the granting of the license or authorization, without which the
 * unauthorized use may subject the offenders to civil or criminal prosecution
 * under applicable Laws.
 * #L%
 */
package io.digitaljourney.platform.plugins.apps.ordermanagement;

import io.digitaljourney.platform.modules.ws.rs.api.RSProperties;

/**
 * OrderManagement properties	
 */
public final class AppProperties extends RSProperties {
	
	private AppProperties() {}

	public static final String APP_NAME = "ordermanagement";
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
	
	public static final String ORDERMANAGEMENT_TAG = APP_NAME;

	/** General Exception error code */
	public static final String ORDERMANAGEMENT000 = "ORDERMANAGEMENT000";

	
}
