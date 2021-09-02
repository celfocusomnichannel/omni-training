package io.digitaljourney.platform.plugins.modules.productmanagement.service.api;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@ProviderType
@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

@SwaggerDefinition(securityDefinition = @SecurityDefinition(basicAuthDefinitions = {
		@BasicAuthDefinition(key = RSProperties.SWAGGER_BASIC_AUTH) }, apiKeyAuthDefinitions = {
				@ApiKeyAuthDefinition(key = RSProperties.SWAGGER_BEARER_AUTH, name = RSProperties.HTTP_HEADER_API_KEY, in = ApiKeyLocation.HEADER) }), schemes = {
						SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS,
						SwaggerDefinition.Scheme.DEFAULT }, tags = {
								@Tag(name = ProductManagementProperties.PRODUCT_TAG),
								@Tag(name = ProductManagementProperties.CATEGORY_TAG), })
@Api(value = "Training JPA Product Management Microservice", authorizations = {
		@Authorization(value = RSProperties.SWAGGER_BASIC_AUTH),
		@Authorization(value = RSProperties.SWAGGER_BEARER_AUTH) })
@ApiResponses(value = {
		@ApiResponse(code = HttpStatusCode.UNAUTHORIZED_CODE, message = RSProperties.SWAGGER_UNAUTHORIZED_MESSAGE),
		@ApiResponse(code = HttpStatusCode.FORBIDDEN_CODE, message = RSProperties.SWAGGER_FORBIDDEN_MESSAGE) })
public interface ProductManagementResource {
	@POST
	@Path("/category")
	@ApiOperation(value = "Adds a new category", response = CategoryDTO.class, tags = ProductManagementProperties.CATEGORY_TAG)
	CategoryDTO createCategory(@ApiParam(value = "The new category", required = true) @Valid CategoryDTO category);

	@GET
	@Path("/category/{id}")
	@ApiOperation(value = "Looks up a category by id", response = CategoryDTO.class, tags = ProductManagementProperties.CATEGORY_TAG)
	CategoryDTO getCategory(@ApiParam(value = "The category id", required = true) @PathParam("id") Integer id);

	@PUT
	@Path("/category/{id}")
	@ApiOperation(value = "Updates a category", response = CategoryDTO.class, tags = ProductManagementProperties.CATEGORY_TAG)
	CategoryDTO updateCategory(@ApiParam(value = "The category id", required = true) @PathParam("id") Integer id,
			@ApiParam(value = "The category to update", required = true) @Valid CategoryDTO category);

	@DELETE
	@Path("/category/{id}")
	@ApiOperation(value = "Deletes a category", response = CategoryDTO.class, tags = ProductManagementProperties.CATEGORY_TAG)
	CategoryDTO deleteCategory(@ApiParam(value = "The category id", required = true) @PathParam("id") Integer id);

	@POST
	@Path("/product")
	@ApiOperation(value = "Adds a new product", response = ProductDTO.class, tags = ProductManagementProperties.PRODUCT_TAG)
	ProductDTO createProduct(@ApiParam(value = "The new product", required = true) @Valid ProductDTO product);

	@GET
	@Path("/product/{id}")
	@ApiOperation(value = "Looks up a product by id", response = ProductDTO.class, tags = ProductManagementProperties.PRODUCT_TAG)
	ProductDTO getProduct(@ApiParam(value = "The product id", required = true) @PathParam("id") Integer id);

	@PUT
	@Path("/product/{id}")
	@ApiOperation(value = "Updates a product", response = ProductDTO.class, tags = ProductManagementProperties.PRODUCT_TAG)
	ProductDTO updateProduct(@ApiParam(value = "The product id", required = true) @PathParam("id") Integer id,
			@ApiParam(value = "The product to update", required = true) @Valid ProductDTO product);

	@DELETE
	@Path("/product/{id}")
	@ApiOperation(value = "Deletes a product", response = ProductDTO.class, tags = ProductManagementProperties.PRODUCT_TAG)
	ProductDTO deleteProduct(@ApiParam(value = "The product id", required = true) @PathParam("id") Integer id);

}
