package io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.api.exception;

import io.digitaljourney.platform.modules.commons.context.Context;
import io.digitaljourney.platform.modules.commons.exception.PlatformCode;
import io.digitaljourney.platform.modules.ws.rs.api.exception.RSException;
import io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.api.FlagMicroServiceRSXMLProperties;

public class FlagMicroServiceRSXMLException extends RSException {
	private static final long serialVersionUID = -8645577031119256555L;
	
	protected FlagMicroServiceRSXMLException(PlatformCode statusCode, String errorCode, Context ctx, Object... args) {
		super(statusCode, errorCode, ctx, args);
	}
	
	protected FlagMicroServiceRSXMLException(Context ctx, Object... args) {
		this(PlatformCode.INTERNAL_SERVER_ERROR, FlagMicroServiceRSXMLProperties.FLAGMICROSERVICERSXML000, ctx, args);
	}
	
	protected FlagMicroServiceRSXMLException(String errorCode, Context ctx, Object... args) {
		this(PlatformCode.BUSINESS_ERROR, errorCode, ctx, args);
	}

	public static FlagMicroServiceRSXMLException of(Context ctx, String message) {
		return new FlagMicroServiceRSXMLException(ctx, message);
	}
	
	public static FlagMicroServiceRSXMLException of(Context ctx, Throwable cause) {
		return new FlagMicroServiceRSXMLException(ctx, cause);
	}
}
