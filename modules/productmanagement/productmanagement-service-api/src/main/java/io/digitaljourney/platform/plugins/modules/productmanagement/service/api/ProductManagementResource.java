package io.digitaljourney.platform.plugins.modules.productmanagement.service.api;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.validator.constraints.NotEmpty;
import org.osgi.annotation.versioning.ProviderType;

import io.digitaljourney.platform.modules.commons.type.HttpStatusCode;
import io.digitaljourney.platform.modules.ws.rs.api.RSProperties;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.SampleDTO;
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

@ProviderType
@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

@SwaggerDefinition(securityDefinition = @SecurityDefinition(basicAuthDefinitions = {
		@BasicAuthDefinition(key = RSProperties.SWAGGER_BASIC_AUTH) }, apiKeyAuthDefinitions = {
				@ApiKeyAuthDefinition(key = RSProperties.SWAGGER_BEARER_AUTH, name = RSProperties.HTTP_HEADER_API_KEY, in = ApiKeyLocation.HEADER) }), schemes = {
						SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS,
						SwaggerDefinition.Scheme.DEFAULT })
@Api(value = "Training JPA Product Management Microservice", authorizations = { @Authorization(value = RSProperties.SWAGGER_BASIC_AUTH),
		@Authorization(value = RSProperties.SWAGGER_BEARER_AUTH) })
@ApiResponses(value = {
		@ApiResponse(code = HttpStatusCode.UNAUTHORIZED_CODE, message = RSProperties.SWAGGER_UNAUTHORIZED_MESSAGE),
		@ApiResponse(code = HttpStatusCode.FORBIDDEN_CODE, message = RSProperties.SWAGGER_FORBIDDEN_MESSAGE) })
public interface ProductManagementResource {
	@GET
	@Path("/{id}")
	@ApiOperation(value = "Looks up a sample by id", response = SampleDTO.class)
	SampleDTO getSample(@ApiParam(value = "The sample id", required = true) @PathParam("id") Integer id);

	@GET
	@ApiOperation(value = "Searches the samples", response = SampleDTO.class, responseContainer = "List")
	List<SampleDTO> searchSamples(@ApiParam(value = "The search expression", required = true) @NotEmpty @QueryParam("expression") String expression);

	@POST
	@ApiOperation(value = "Adds a new sample", response = SampleDTO.class)
	SampleDTO addSample(@ApiParam(value = "The new sample", required = true) @Valid SampleDTO sample);

	@PUT
	@ApiOperation(value = "Updates a sample", response = SampleDTO.class)
	SampleDTO updateSample(@ApiParam(value = "The sample to update", required = true) @Valid SampleDTO sample);

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Deletes a sample")
	void deleteSample(@ApiParam(value = "The sample id", required = true) @PathParam("id") Integer id);
}
