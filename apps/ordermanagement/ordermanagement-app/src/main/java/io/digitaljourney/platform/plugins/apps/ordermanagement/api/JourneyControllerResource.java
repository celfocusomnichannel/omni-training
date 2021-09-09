package io.digitaljourney.platform.plugins.apps.ordermanagement.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.osgi.annotation.versioning.ProviderType;

import io.digitaljourney.platform.plugins.apps.ordermanagement.AppProperties;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomJourneyDTO;

import io.digitaljourney.platform.modules.commons.type.HttpStatusCode;
import io.digitaljourney.platform.modules.ws.rs.api.RSProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiKeyAuthDefinition.ApiKeyLocation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.BasicAuthDefinition;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@ProviderType
@Path(AppProperties.ADDRESS)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SwaggerDefinition(
		securityDefinition = @SecurityDefinition(
				basicAuthDefinitions = {
						@BasicAuthDefinition(key = RSProperties.SWAGGER_BASIC_AUTH)
				},
				apiKeyAuthDefinitions = {
						@ApiKeyAuthDefinition(key = RSProperties.SWAGGER_BEARER_AUTH, name = RSProperties.HTTP_HEADER_API_KEY, in = ApiKeyLocation.HEADER)
				}),
		schemes = {
				SwaggerDefinition.Scheme.HTTP,
				SwaggerDefinition.Scheme.HTTPS,
				SwaggerDefinition.Scheme.DEFAULT
		},
		tags = { @Tag(name = "Journey", description = "Journey API") })
@Api(
		value = "Custom Journey",
		authorizations = {
				@Authorization(value = RSProperties.SWAGGER_BASIC_AUTH),
				@Authorization(value = RSProperties.SWAGGER_BEARER_AUTH)
		},
		tags = { "Journey" })
@ApiResponses(value = {
		@ApiResponse(code = HttpStatusCode.UNAUTHORIZED_CODE, message = RSProperties.SWAGGER_UNAUTHORIZED_MESSAGE),
		@ApiResponse(code = HttpStatusCode.FORBIDDEN_CODE, message = RSProperties.SWAGGER_FORBIDDEN_MESSAGE) })
public interface JourneyControllerResource {

	@POST
	@Path("/")
	@ApiOperation(value = "Creates a process", notes = "Creates a new process instance", response = CustomJourneyDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = CustomJourneyDTO.class, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public CustomJourneyDTO create();

	@GET
	@Path("/{instanceId}")
	@ApiOperation(value = "Reads a process", notes = "Reads an existing process instance", response = CustomJourneyDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = CustomJourneyDTO.class, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public CustomJourneyDTO read(
			@ApiParam(value = "The unique identifier of the process instance", required = true, example = "1") @PathParam("instanceId") Long instanceId);
	
	@PUT
	@Path("/{instanceId}/custom")
	@ApiOperation(value = "Change custom field", notes = "Changes custom field of an existing process instance", response = CustomJourneyDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = CustomJourneyDTO.class, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public CustomJourneyDTO changeField(
			@ApiParam(value = "The unique identifier of the process instance", required = true, example = "1") @PathParam("instanceId") Long instanceId,
			@ApiParam(value = "New custom field value", required = true, example = "sample1") String value);
	
	@PUT
	@Path("/{instanceId}/finish")
	@ApiOperation(value = "Finish process", notes = "Finishes the process", response = CustomJourneyDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = CustomJourneyDTO.class, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public CustomJourneyDTO finish(
			@ApiParam(value = "The unique identifier of the process instance", required = true, example = "1") @PathParam("instanceId") Long instanceId);
}
