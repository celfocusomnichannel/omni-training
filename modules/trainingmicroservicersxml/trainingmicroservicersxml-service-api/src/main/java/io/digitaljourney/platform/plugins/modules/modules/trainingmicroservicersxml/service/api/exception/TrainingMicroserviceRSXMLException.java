package io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.service.api.exception;

import io.digitaljourney.platform.modules.commons.context.Context;
import io.digitaljourney.platform.modules.commons.exception.PlatformCode;
import io.digitaljourney.platform.modules.ws.rs.api.exception.RSException;
import io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.service.api.TrainingMicroserviceRSXMLProperties;

public class TrainingMicroserviceRSXMLException extends RSException {
	private static final long serialVersionUID = -8645577031119256555L;
	
	protected TrainingMicroserviceRSXMLException(PlatformCode statusCode, String errorCode, Context ctx, Object... args) {
		super(statusCode, errorCode, ctx, args);
	}
	
	protected TrainingMicroserviceRSXMLException(Context ctx, Object... args) {
		this(PlatformCode.INTERNAL_SERVER_ERROR, TrainingMicroserviceRSXMLProperties.TRAININGMICROSERVICERSXML000, ctx, args);
	}
	
	protected TrainingMicroserviceRSXMLException(String errorCode, Context ctx, Object... args) {
		this(PlatformCode.BUSINESS_ERROR, errorCode, ctx, args);
	}

	public static TrainingMicroserviceRSXMLException of(Context ctx, String message) {
		return new TrainingMicroserviceRSXMLException(ctx, message);
	}
	
	public static TrainingMicroserviceRSXMLException of(Context ctx, Throwable cause) {
		return new TrainingMicroserviceRSXMLException(ctx, cause);
	}
}
