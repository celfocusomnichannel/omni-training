package io.digitaljourney.platform.plugins.apps.ordermanagement.api;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.osgi.annotation.versioning.ProviderType;

import io.digitaljourney.platform.modules.commons.type.HttpStatusCode;
import io.digitaljourney.platform.modules.ws.rs.api.RSProperties;
import io.digitaljourney.platform.plugins.apps.ordermanagement.AppProperties;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomJourneyDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomerInfoDTO;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.CategoryDTO;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.ProductDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiKeyAuthDefinition.ApiKeyLocation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.BasicAuthDefinition;
import io.swagger.annotations.Info;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;

//@formatter:off
@ProviderType
@Path(AppProperties.ADDRESS)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
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
				version = AppProperties.CURRENT_VERSION
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

	/**
	 * Initializes blueprint import
	 */
	@GET
	@Path("/init")
	@ApiOperation(value = "Initializes blueprint import")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public void init();

	/**
	 * Create action mode creates a new empty instance
	 */
	@POST
	@Path("/")
	@ApiOperation(value = "Creates a process", notes = "Creates a new process instance", response = CustomJourneyDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = CustomJourneyDTO.class, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public CustomJourneyDTO create();
	
	/**
	 * Retrieves existing instance with a given ID
	 */
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
	
	/**
	 * Retrieves products for instance with a given ID
	 */
	@GET
	@Path("/{instanceId}/products")
	@ApiOperation(value = "Get product list", response = CustomJourneyDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = ProductDTO.class, responseContainer = "Array", message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),		
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public List<ProductDTO> getProductList(
			@ApiParam(value = "The unique identifier of the process instance", required = true, example = "1") @PathParam("instanceId") Long instanceId);
	
	/**
	 * Retrieves category for instance with a given ID
	 */
	@GET
	@Path("/{instanceId}/category")
	@ApiOperation(value = "Get category", response = CustomJourneyDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = CategoryDTO.class, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),		
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public CategoryDTO getCategory(
			@ApiParam(value = "The unique identifier of the process instance", required = true, example = "1") @PathParam("instanceId") Long instanceId);
	
	/**
	 * Retrieves delivery options for instance with a given ID
	 */
	@GET
	@Path("/{instanceId}/delivery-options")
	@ApiOperation(value = "Get delivery option list", response = CustomJourneyDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),		
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public List<HashMap<String, Object>> getDeliveryOptions(
			@ApiParam(value = "The unique identifier of the process instance", required = true, example = "1") @PathParam("instanceId") Long instanceId);
	
	@PUT
	@Path("/{instanceId}/products/{productId}")
	@ApiOperation(value = "Select product", response = CustomJourneyDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = CustomJourneyDTO.class, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),		
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public CustomJourneyDTO selectProduct(
			@ApiParam(value = "The unique identifier of the process instance", required = true, example = "1") @PathParam("instanceId") Long instanceId,
			@ApiParam(value = "The unique identifier of the product", required = true, example = "1") @PathParam("productId") Integer productId);
	
	@POST
	@Path("/{instanceId}/order-create")
	@ApiOperation(value = "Creates a order request", response = CustomJourneyDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = CustomJourneyDTO.class, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),		
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public CustomJourneyDTO createOrder(
			@ApiParam(value = "The unique identifier of the process instance", required = true, example = "1") @PathParam("instanceId") Long instanceId);
	
	@POST
	@Path("/{instanceId}/customer-info")
	@ApiOperation(value = "Updates the customer information", response = CustomJourneyDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = CustomJourneyDTO.class, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public CustomJourneyDTO updateCustomerInfo(
			@ApiParam(value = "The unique identifier of the process instance", required = true, example = "1") @PathParam("instanceId") Long instanceId,
			@ApiParam(value = "The customer information", required = true) CustomerInfoDTO customerInfo);

	@POST
	@Path("/{instanceId}/order-submit")
	@ApiOperation(value = "Submits a order request", response = CustomJourneyDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = CustomJourneyDTO.class, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),		
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public CustomJourneyDTO submitOrder(
			@ApiParam(value = "The unique identifier of the process instance", required = true, example = "1") @PathParam("instanceId") Long instanceId);

}
