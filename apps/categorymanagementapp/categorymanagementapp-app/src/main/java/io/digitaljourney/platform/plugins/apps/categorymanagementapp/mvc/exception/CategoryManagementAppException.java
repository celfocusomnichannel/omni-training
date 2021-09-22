/*-
 * #%L
 * Apps :: App to manage categories :: App
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
package io.digitaljourney.platform.plugins.apps.categorymanagementapp.mvc.exception;

import io.digitaljourney.platform.modules.commons.context.Context;
import io.digitaljourney.platform.modules.commons.exception.PlatformCode;
import io.digitaljourney.platform.modules.mvc.api.exception.MVCException;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.CategoryManagementAppProperties;

/**
 * App to manage categories App exception. Extends an {@link MVCException MVC Exception}.
 */
public class CategoryManagementAppException extends MVCException {
	private static final long serialVersionUID = 1154130703565675579L;
	
	/**
	 * Creates a new App to manage categories Exception with the given arguments.
	 *
	 * @param statusCode Exception Status Code (which will be translated to an HTTP
	 *                   Status Code)
	 * @param errorCode  Error message
	 * @param ctx        Context
	 * @param args       Optional arguments
	 */
	protected CategoryManagementAppException(PlatformCode statusCode, String errorCode, Context ctx, Object... args) {
		super(statusCode, errorCode, ctx, args);
	}
	
	/**
	 * Creates a new Internal Server Error exception with the UFE000 error code.
	 *
	 * @param ctx  Context
	 * @param args Optional arguments
	 */
	protected CategoryManagementAppException(Context ctx, Object... args) {
		this(PlatformCode.INTERNAL_SERVER_ERROR, CategoryManagementAppProperties.CATEGORYMANAGEMENTAPP000, ctx, args);
	}
	
	/**
	 * Creates a new Internal Server Error exception with the given error code.
	 *
	 * @param errorCode Error message
	 * @param ctx       Context
	 * @param args      Optional arguments
	 */
	protected CategoryManagementAppException(String errorCode, Context ctx, Object... args) {
		this(PlatformCode.BUSINESS_ERROR, errorCode, ctx, args);
	}

	/**
	 * Creates a new App to manage categories Exception (500 - Internal Error) with a given
	 * context and message.
	 *
	 * @param ctx     Context
	 * @param message Exception message
	 * @return Created exception
	 */
	public static CategoryManagementAppException of(Context ctx, String message) {
		return new CategoryManagementAppException(ctx, message);
	}
	
	/**
	 * Creates a new App to manage categories Exception (500 - Internal Error) with a given
	 * context and error cause.
	 *
	 * @param ctx   Context
	 * @param cause Error cause
	 * @return Created exception
	 */
	public static CategoryManagementAppException of(Context ctx, Throwable cause) {
		return new CategoryManagementAppException(ctx, cause);
	}
}
