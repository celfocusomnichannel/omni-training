package io.digitaljourney.platform.plugins.apps.appkar.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.osgi.annotation.versioning.ProviderType;

import io.digitaljourney.platform.modules.commons.type.HttpStatusCode;
import io.digitaljourney.platform.modules.ws.rs.api.RSProperties;
import io.digitaljourney.platform.plugins.apps.appkar.AppProperties;
import io.digitaljourney.platform.plugins.apps.appkar.model.FlagResponseDTO;
import io.digitaljourney.platform.plugins.apps.appkar.model.MusicProductResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiKeyAuthDefinition.ApiKeyLocation;
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
				title = "Kar App Description",
				description = "The Kar App Description API provides a way to interface with correlated services and entities",
				version = AppProperties.CURRENT_VERSION,
				license = @License(
						name = "Digital Journey License",
						url = "http://www.digitaljourney.io/license"
				)
		),
		basePath = "bin/mvc.do/"+ AppProperties.APP_NAME + AppProperties.APP_VERSION
)
@Api(
		value = "AppKar",
		authorizations = {
				@Authorization(value = RSProperties.SWAGGER_BASIC_AUTH),
				@Authorization(value = RSProperties.SWAGGER_BEARER_AUTH)
		},
		tags = {
				AppProperties.APPKAR_TAG
		}
)
@ApiResponses(
		value = {
				@ApiResponse(code = HttpStatusCode.UNAUTHORIZED_CODE, message = RSProperties.SWAGGER_UNAUTHORIZED_MESSAGE),
				@ApiResponse(code = HttpStatusCode.FORBIDDEN_CODE, message = RSProperties.SWAGGER_FORBIDDEN_MESSAGE),
				@ApiResponse(code = HttpStatusCode.INTERNAL_SERVER_ERROR_CODE, message = AppProperties.APPKAR000)
		}
)
//@formatter:on
public interface AppKarResource {

	@GET
    @Path("/{alphaCode}")
    @ApiOperation(value = "Get flag", response = FlagResponseDTO.class)
    FlagResponseDTO getFlag(@ApiParam(value = "alphaCode", required = true) @PathParam("alphaCode") String isoCode);
	
	@GET
	@Path("/search")
	List<MusicProductResponseDTO> getArtistMusics(
			@QueryParam("term") @ApiParam(value = "Artist name", required = true) String artistName,
			@QueryParam("limit") @ApiParam(value = "Limit", required = false) String limit);
}
