/*-
 * #%L
 * Apps :: App to manage categories :: API
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
package io.digitaljourney.platform.plugins.apps.categorymanagementapp.api;

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
import javax.ws.rs.core.MediaType;

import org.osgi.annotation.versioning.ProviderType;

import io.digitaljourney.platform.modules.commons.type.HttpStatusCode;
import io.digitaljourney.platform.modules.ws.rs.api.RSProperties;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.CategoryManagementAppProperties;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.model.CategoryResponseDTO;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.model.CategoryRequestDTO;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.model.CategoryCreateDTO;
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
				title = "App to manage categories",
				description = "The App to manage categories API provides a way to interface with correlated services and entities",
				version = CategoryManagementAppProperties.CURRENT_VERSION,
				license = @License(
						name = "Digital Journey License",
						url = "http://www.digitaljourney.io/license"
				)
		),
		basePath = "bin/mvc.do/"+ CategoryManagementAppProperties.APP_NAME + CategoryManagementAppProperties.APP_VERSION
)
@Api(
		value = "CategoryManagementApp",
		authorizations = {
				@Authorization(value = RSProperties.SWAGGER_BASIC_AUTH),
				@Authorization(value = RSProperties.SWAGGER_BEARER_AUTH)
		},
		tags = {
				CategoryManagementAppProperties.CATEGORYMANAGEMENTAPP_TAG
		}
)
@ApiResponses(
		value = {
				@ApiResponse(code = HttpStatusCode.UNAUTHORIZED_CODE, message = RSProperties.SWAGGER_UNAUTHORIZED_MESSAGE),
				@ApiResponse(code = HttpStatusCode.FORBIDDEN_CODE, message = RSProperties.SWAGGER_FORBIDDEN_MESSAGE),
				@ApiResponse(code = HttpStatusCode.INTERNAL_SERVER_ERROR_CODE, message = CategoryManagementAppProperties.CATEGORYMANAGEMENTAPP000)
		}
)
//@formatter:on
public interface CategoryManagementAppResource {

	/** The Constant SUCCESSFUL_OPERATION. */
	public static final String SUCCESSFUL_OPERATION = "successful operation";
	
	@POST
	@Path("/category")
	@ApiOperation(value = "Adds new category", response = CategoryResponseDTO.class)
	CategoryResponseDTO createCategory(@ApiParam(value = "The new category", required = true) @Valid CategoryCreateDTO category);
	
	@GET
	@Path("/category/{id}")
	@ApiOperation(value = "Looks up a category by id", response = CategoryResponseDTO.class)
	CategoryResponseDTO getCategory(@ApiParam(value = "The category id", required = true) @PathParam("id") Integer id);
	
	@GET
	@Path("/category")
	@ApiOperation(value = "Looks up every category", response = CategoryResponseDTO.class)
	List<CategoryResponseDTO> getCategories();
	
	@PUT
	@Path("/category")
	@ApiOperation(value = "Updates a category", response = CategoryResponseDTO.class)
	CategoryResponseDTO updateCategory(
			@ApiParam(value = "The category to update", required = true) @Valid CategoryRequestDTO category);
	
	@DELETE
	@Path("/category/{id}")
	@ApiOperation(value = "Deletes a category", response = CategoryResponseDTO.class)
	CategoryResponseDTO deleteCategory(@ApiParam(value = "The category id", required = true) @PathParam("id") Integer id);
}
