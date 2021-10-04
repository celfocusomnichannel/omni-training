package io.digitaljourney.platform.plugins.apps.genericappkar.mvc.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.osgi.service.component.annotations.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import io.digitaljourney.platform.modules.commons.type.HttpStatusCode;
import io.digitaljourney.platform.modules.ws.rs.api.RSProperties;
import io.digitaljourney.platform.plugins.apps.genericappkar.AppProperties;
import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CmsRsProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiKeyAuthDefinition.ApiKeyLocation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.BasicAuthDefinition;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;

//@formatter:off
@Component(
	service = {AppController.class},
	property = {
		"digitaljourney.service.name=GenericAppKar"
	}
)
@CmsRsProvider(value=AppProperties.ADDRESS)
@RequestMapping(AppProperties.ADDRESS)
@Path(AppProperties.ADDRESS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SwaggerDefinition(
	securityDefinition = @SecurityDefinition(
		basicAuthDefinitions = {
			@BasicAuthDefinition(key = RSProperties.SWAGGER_BASIC_AUTH)
		},
		apiKeyAuthDefinitions = {
			@ApiKeyAuthDefinition(
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
		title = "Generic Kar App Description API",
		description = "The Generic Kar App Description API.",
		version = AppProperties.CURRENT_VERSION,
		license = @License(name = "Digital Journey License", url = "http://www.digitaljourney.io/license")
	),
	basePath = "bin/mvc.do/"+AppProperties.ADDRESS
)
@Api(
	value = "Generic Kar App Description API",
	authorizations = {
		@Authorization(value = RSProperties.SWAGGER_BASIC_AUTH),
		@Authorization(value = RSProperties.SWAGGER_BEARER_AUTH)
	}
)
@ApiResponses(
	value = {
		@ApiResponse(code = HttpStatusCode.UNAUTHORIZED_CODE, message = RSProperties.SWAGGER_UNAUTHORIZED_MESSAGE),
		@ApiResponse(code = HttpStatusCode.FORBIDDEN_CODE, message = RSProperties.SWAGGER_FORBIDDEN_MESSAGE)
	}
)
//@formatter:on
public class AppController extends AbstractAppController {
	
	// nothing here we just fake some endpoints to enable swagger
	
	/** Base CXF path */
	public static final String BASE_CXF_PATH = "cms";

	/**
	 * Fake endpoint to document resources
	 */
	@GET
	@Path("/")
	@ApiOperation(value = "Endpoint description", notes = "This is a mock endpoint")
	@ExternalDocs(url = BASE_CXF_PATH + AppProperties.ADDRESS + "/api-docs?url=../swagger.json", value = "Endpoint description")
	public void get() {
		// no op
	}
}
