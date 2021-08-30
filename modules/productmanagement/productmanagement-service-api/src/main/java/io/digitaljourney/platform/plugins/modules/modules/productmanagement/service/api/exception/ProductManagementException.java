package io.digitaljourney.platform.plugins.modules.modules.productmanagement.service.api.exception;

import io.digitaljourney.platform.modules.commons.context.Context;
import io.digitaljourney.platform.modules.commons.exception.PlatformCode;
import io.digitaljourney.platform.modules.ws.rs.api.exception.RSException;
import io.digitaljourney.platform.plugins.modules.modules.productmanagement.service.api.ProductManagementProperties;

public class ProductManagementException extends RSException {
	private static final long serialVersionUID = -8645577031119256555L;
	
	protected ProductManagementException(PlatformCode statusCode, String errorCode, Context ctx, Object... args) {
		super(statusCode, errorCode, ctx, args);
	}
	
	protected ProductManagementException(Context ctx, Object... args) {
		this(PlatformCode.INTERNAL_SERVER_ERROR, ProductManagementProperties.PRODUCTMANAGEMENT000, ctx, args);
	}
	
	protected ProductManagementException(String errorCode, Context ctx, Object... args) {
		this(PlatformCode.BUSINESS_ERROR, errorCode, ctx, args);
	}

	public static ProductManagementException of(Context ctx, String message) {
		return new ProductManagementException(ctx, message);
	}
	
	public static ProductManagementException of(Context ctx, Throwable cause) {
		return new ProductManagementException(ctx, cause);
	}
}
