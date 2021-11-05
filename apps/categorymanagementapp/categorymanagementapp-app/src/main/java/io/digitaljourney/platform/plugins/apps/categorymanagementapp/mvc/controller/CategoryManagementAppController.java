/*-
 * #%L
 * Apps :: App to manage categories :: App
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
package io.digitaljourney.platform.plugins.apps.categorymanagementapp.mvc.controller;

import java.util.List;

import org.eclipse.gemini.blueprint.extensions.annotation.ServiceReference;
import org.osgi.service.component.annotations.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.digitaljourney.platform.plugins.apps.categorymanagementapp.CategoryManagementAppProperties;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.api.CategoryManagementAppResource;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.common.api.facade.CategoryManagementAppFacade;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.model.CategoryCreateDTO;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.model.CategoryRequestDTO;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.model.CategoryResponseDTO;
import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CmsRsProvider;

//@formatter:off
@Controller
@RequestMapping(CategoryManagementAppProperties.ADDRESS + "/app")
@Component(
	property = {
		"digitaljourney.service.name=CategoryManagementApp"
	}
)
@CmsRsProvider(value = CategoryManagementAppProperties.ADDRESS + "/app")
//@formatter:on
public class CategoryManagementAppController extends AbstractAppController implements CategoryManagementAppResource {

	@ServiceReference
	private CategoryManagementAppFacade facade;
	
	@Override
	@PostMapping(path="/category")
	public @ResponseBody CategoryResponseDTO createCategory(@RequestBody CategoryCreateDTO category) {
		return facade.createCategory(category);
	}
	
	@Override
	@GetMapping(path="/category/{id}")
	public @ResponseBody CategoryResponseDTO getCategory(@PathVariable Integer id) {
		return facade.getCategory(id);
	}
	
	@Override
	@GetMapping(path="/category")
	public @ResponseBody List<CategoryResponseDTO> getCategories() {
		return facade.getCategories();
	}
	
	@Override
	@PutMapping(path="/category")
	public @ResponseBody CategoryResponseDTO updateCategory(@RequestBody CategoryRequestDTO category) {
		return facade.updateCategory(category);
	}
	
	@Override
	@DeleteMapping(path="/category/{id}")
	public @ResponseBody CategoryResponseDTO deleteCategory(@PathVariable Integer id) {
		return facade.deleteCategory(id);
	}
	

}
