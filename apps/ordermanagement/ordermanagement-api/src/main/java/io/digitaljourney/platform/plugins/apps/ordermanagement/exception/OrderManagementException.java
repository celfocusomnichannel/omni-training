/*-
 * #%L
 * Apps :: Training JWE Order Management App App
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
package io.digitaljourney.platform.plugins.apps.ordermanagement.exception;

import io.digitaljourney.platform.modules.commons.context.Context;
import io.digitaljourney.platform.modules.commons.exception.PlatformCode;
import io.digitaljourney.platform.modules.mvc.api.exception.MVCException;
import io.digitaljourney.platform.plugins.apps.ordermanagement.AppProperties;

public class OrderManagementException extends MVCException {
	private static final long serialVersionUID = 1154130703565675579L;
	
	protected OrderManagementException(PlatformCode statusCode, String errorCode, Context ctx, Object... args) {
		super(statusCode, errorCode, ctx, args);
	}
	
	protected OrderManagementException(Context ctx, Object... args) {
		this(PlatformCode.INTERNAL_SERVER_ERROR, AppProperties.ORDERMANAGEMENT000, ctx, args);
	}
	
	protected OrderManagementException(String errorCode, Context ctx, Object... args) {
		this(PlatformCode.BUSINESS_ERROR, errorCode, ctx, args);
	}

	public static OrderManagementException of(Context ctx, String message) {
		return new OrderManagementException(ctx, message);
	}
	
	public static OrderManagementException of(Context ctx, Throwable cause) {
		return new OrderManagementException(ctx, cause);
	}
}
