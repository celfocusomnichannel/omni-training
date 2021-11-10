package io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.osgi.annotation.versioning.ProviderType;

import io.digitaljourney.platform.modules.commons.type.HttpStatusCode;
import io.digitaljourney.platform.modules.ws.rs.api.RSProperties;
import io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.dto.TemperatureDTO;
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

//@formatter:off
@ProviderType
@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

@SwaggerDefinition(
	securityDefinition = @SecurityDefinition(
		basicAuthDefinitions = {
			@BasicAuthDefinition(
					key = RSProperties.SWAGGER_BASIC_AUTH)},
		apiKeyAuthDefinitions = {
			@ApiKeyAuthDefinition(
					key = RSProperties.SWAGGER_BEARER_AUTH,
					name = RSProperties.HTTP_HEADER_API_KEY,
					in = ApiKeyLocation.HEADER)}),
	schemes = {
		SwaggerDefinition.Scheme.HTTP,
		SwaggerDefinition.Scheme.HTTPS,
		SwaggerDefinition.Scheme.DEFAULT})
@Api(
	value = "Temperature Converter Microservice consuming SOAP Webservice",
	authorizations = {
			@Authorization(value = RSProperties.SWAGGER_BASIC_AUTH),
			@Authorization(value = RSProperties.SWAGGER_BEARER_AUTH)})
@ApiResponses(value = {
	@ApiResponse(code = HttpStatusCode.UNAUTHORIZED_CODE, message = RSProperties.SWAGGER_UNAUTHORIZED_MESSAGE),
	@ApiResponse(code = HttpStatusCode.FORBIDDEN_CODE, message = RSProperties.SWAGGER_FORBIDDEN_MESSAGE)})
//@formatter:on
public interface TemperatureConverterResource {
	
	@GET
	@Path("/fahrenheit/{temperature}")
	@ApiOperation(value = "Convert Celsius to Fahrenheit", response=TemperatureDTO.class)
	TemperatureDTO convertCelsius(@ApiParam(value="Temperature in Celsius (°C)", required = true) @PathParam("temperature") Double temperature);

}
