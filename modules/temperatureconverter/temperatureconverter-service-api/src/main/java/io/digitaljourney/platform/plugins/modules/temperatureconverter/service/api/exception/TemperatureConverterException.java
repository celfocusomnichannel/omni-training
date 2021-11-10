package io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.exception;

import io.digitaljourney.platform.modules.commons.context.Context;
import io.digitaljourney.platform.modules.commons.exception.PlatformCode;
import io.digitaljourney.platform.modules.ws.rs.api.exception.RSException;
import io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.TemperatureConverterProperties;

public class TemperatureConverterException extends RSException {
	private static final long serialVersionUID = -8645577031119256555L;

	protected TemperatureConverterException(PlatformCode statusCode, String errorCode, Context ctx, Object... args) {
		super(statusCode, errorCode, ctx, args);
	}

	protected TemperatureConverterException(Context ctx, Object... args) {
		this(PlatformCode.INTERNAL_SERVER_ERROR, TemperatureConverterProperties.TEMPERATURECONVERTER000, ctx, args);
	}

	protected TemperatureConverterException(String errorCode, Context ctx, Object... args) {
		this(PlatformCode.BUSINESS_ERROR, errorCode, ctx, args);
	}

	public static TemperatureConverterException of(Context ctx, String message) {
		return new TemperatureConverterException(ctx, message);
	}

	public static TemperatureConverterException of(Context ctx, Throwable cause) {
		return new TemperatureConverterException(ctx, cause);
	}
}
