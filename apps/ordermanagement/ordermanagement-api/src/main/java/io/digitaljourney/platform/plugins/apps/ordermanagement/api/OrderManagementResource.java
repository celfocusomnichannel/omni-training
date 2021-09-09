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
package io.digitaljourney.platform.plugins.apps.ordermanagement.api;

import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.validator.constraints.NotBlank;
import org.osgi.annotation.versioning.ProviderType;

import io.digitaljourney.platform.modules.commons.type.HttpStatusCode;
import io.digitaljourney.platform.modules.ws.rs.api.RSProperties;
import io.digitaljourney.platform.plugins.apps.ordermanagement.AppProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiKeyAuthDefinition.ApiKeyLocation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.BasicAuthDefinition;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;

//@formatter:off
@ProviderType
@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SwaggerDefinition(
		securityDefinition = @SecurityDefinition (
				basicAuthDefinitions = {
						@BasicAuthDefinition(key = RSProperties.SWAGGER_BASIC_AUTH)
				},
				apiKeyAuthDefinitions = {
						@ApiKeyAuthDefinition (
								key = RSProperties.SWAGGER_BEARER_AUTH,
								name = RSProperties.HTTP_HEADER_API_KEY,
								in = ApiKeyLocation.HEADER
						)
				}
		),
		schemes = {
				SwaggerDefinition.Scheme.HTTP,
				SwaggerDefinition.Scheme.HTTPS,
				SwaggerDefinition.Scheme.DEFAULT
		},
		info = @Info(
				title = "Training JWE Order Management App",
				description = "The Training JWE Order Management App API provides a way to interface with correlated services and entities",
				version = AppProperties.CURRENT_VERSION,
				license = @License(
						name = "Digital Journey License",
						url = "http://www.digitaljourney.io/license"
				)
		),
		basePath = "bin/mvc.do/"+ AppProperties.APP_NAME + AppProperties.APP_VERSION
)
@Api(
		value = "OrderManagement",
		authorizations = {
				@Authorization(value = RSProperties.SWAGGER_BASIC_AUTH),
				@Authorization(value = RSProperties.SWAGGER_BEARER_AUTH)
		},
		tags = {
				AppProperties.ORDERMANAGEMENT_TAG
		}
)
@ApiResponses(
		value = {
				@ApiResponse(code = HttpStatusCode.UNAUTHORIZED_CODE, message = RSProperties.SWAGGER_UNAUTHORIZED_MESSAGE),
				@ApiResponse(code = HttpStatusCode.FORBIDDEN_CODE, message = RSProperties.SWAGGER_FORBIDDEN_MESSAGE),
				@ApiResponse(code = HttpStatusCode.INTERNAL_SERVER_ERROR_CODE, message = AppProperties.ORDERMANAGEMENT000)
		}
)
//@formatter:on
public interface OrderManagementResource {

	/** The Constant SUCCESSFUL_OPERATION. */
	public static final String SUCCESSFUL_OPERATION = "successful operation";

	/**
	 * Print the given message.
	 *
	 * @param msg the message
	 * @return The given message
	 */
	@GET
	@Path("/echo")
	@Produces(value = MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Print the given message", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = HttpStatusCode.OK_CODE, message = SUCCESSFUL_OPERATION, response = String.class),
			@ApiResponse(code = HttpStatusCode.BAD_REQUEST_CODE, message = "Erro") })
	public String echo(
			@ApiParam(value = "The message", required = true) 
			@QueryParam("msg") @NotBlank @Size(min = 3, max = 60) String msg
	);
	
	/**
	 * Print the given message and channel over security context.
	 *
	 * @param channel 	the channel message
	 * @param msg 		the message
	 * @return The given message
	 */
	@GET
	@Path("/secure/{channel}/echo")
	@Produces(value = MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Print the given message using security context", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = HttpStatusCode.OK_CODE, message = SUCCESSFUL_OPERATION, response = String.class),
			@ApiResponse(code = HttpStatusCode.BAD_REQUEST_CODE, message = "Erro") })
	public String secureEcho(
			@ApiParam(value = "The channel", required = true) @PathParam("channel") @NotBlank String channel,
			@ApiParam(value = "The message", required = true) @QueryParam("msg") @NotBlank @Size(min = 3, max = 60) String msg
	);
}
