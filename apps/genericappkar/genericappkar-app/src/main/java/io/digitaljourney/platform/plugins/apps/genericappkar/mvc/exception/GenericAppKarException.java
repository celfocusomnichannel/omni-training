package io.digitaljourney.platform.plugins.apps.genericappkar.mvc.exception;

import io.digitaljourney.platform.modules.commons.context.Context;
import io.digitaljourney.platform.modules.commons.exception.PlatformCode;
import io.digitaljourney.platform.modules.mvc.api.exception.MVCException;
import io.digitaljourney.platform.plugins.apps.genericappkar.AppProperties;

/**
 * Generic Kar App Description App exception. Extends an {@link MVCException MVC Exception}.
 */
public class GenericAppKarException extends MVCException {
	private static final long serialVersionUID = 1154130703565675579L;
	
	/**
	 * Creates a new Generic Kar App Description Exception with the given arguments.
	 *
	 * @param statusCode Exception Status Code (which will be translated to an HTTP
	 *                   Status Code)
	 * @param errorCode  Error message
	 * @param ctx        Context
	 * @param args       Optional arguments
	 */
	protected GenericAppKarException(PlatformCode statusCode, String errorCode, Context ctx, Object... args) {
		super(statusCode, errorCode, ctx, args);
	}
	
	/**
	 * Creates a new Internal Server Error exception with the UFE000 error code.
	 *
	 * @param ctx  Context
	 * @param args Optional arguments
	 */
	protected GenericAppKarException(Context ctx, Object... args) {
		this(PlatformCode.INTERNAL_SERVER_ERROR, AppProperties.GENERICAPPKAR000, ctx, args);
	}
	
	/**
	 * Creates a new Internal Server Error exception with the given error code.
	 *
	 * @param errorCode Error message
	 * @param ctx       Context
	 * @param args      Optional arguments
	 */
	protected GenericAppKarException(String errorCode, Context ctx, Object... args) {
		this(PlatformCode.BUSINESS_ERROR, errorCode, ctx, args);
	}

	/**
	 * Creates a new Generic Kar App Description Exception (500 - Internal Error) with a given
	 * context and message.
	 *
	 * @param ctx     Context
	 * @param message Exception message
	 * @return Created exception
	 */
	public static GenericAppKarException of(Context ctx, String message) {
		return new GenericAppKarException(ctx, message);
	}
	
	/**
	 * Creates a new Generic Kar App Description Exception (500 - Internal Error) with a given
	 * context and error cause.
	 *
	 * @param ctx   Context
	 * @param cause Error cause
	 * @return Created exception
	 */
	public static GenericAppKarException of(Context ctx, Throwable cause) {
		return new GenericAppKarException(ctx, cause);
	}
}