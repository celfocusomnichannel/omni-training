package io.digitaljourney.platform.plugins.modules.modules.productservice.service.api.exception;

import io.digitaljourney.platform.modules.commons.context.Context;
import io.digitaljourney.platform.modules.commons.exception.PlatformCode;
import io.digitaljourney.platform.modules.ws.rs.api.exception.RSException;
import io.digitaljourney.platform.plugins.modules.modules.productservice.service.api.ProductServiceProperties;

public class ProductServiceException extends RSException {
	private static final long serialVersionUID = -8645577031119256555L;

	protected ProductServiceException(PlatformCode statusCode, String errorCode, Context ctx, Object... args) {
		super(statusCode, errorCode, ctx, args);
	}

	protected ProductServiceException(Context ctx, Object... args) {
		this(PlatformCode.INTERNAL_SERVER_ERROR, ProductServiceProperties.PRODUCTSERVICE000, ctx, args);
	}

	protected ProductServiceException(String errorCode, Context ctx, Object... args) {
		this(PlatformCode.BUSINESS_ERROR, errorCode, ctx, args);
	}

	public static ProductServiceException of(Context ctx, String message) {
		return new ProductServiceException(ctx, message);
	}

	public static ProductServiceException of(Context ctx, Throwable cause) {
		return new ProductServiceException(ctx, cause);
	}
}
