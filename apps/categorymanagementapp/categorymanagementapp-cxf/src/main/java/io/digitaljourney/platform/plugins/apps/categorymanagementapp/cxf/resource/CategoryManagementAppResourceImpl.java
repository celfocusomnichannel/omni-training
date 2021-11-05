/*-
 * #%L
 * Apps :: App to manage categories :: CXF
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
package io.digitaljourney.platform.plugins.apps.categorymanagementapp.cxf.resource;

import java.util.List;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.ws.rs.api.resource.AbstractResource;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.CategoryManagementAppProperties;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.api.CategoryManagementAppResource;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.common.api.facade.CategoryManagementAppFacade;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.cxf.CategoryManagementAppConfiguration;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.cxf.CategoryManagementAppContext;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.model.CategoryCreateDTO;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.model.CategoryRequestDTO;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.model.CategoryResponseDTO;
import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CustomRsProvider;

//@formatter:off
//OSGi setup and configuration.
@Component(
	service = { CategoryManagementAppResource.class },
	configurationPid = CategoryManagementAppConfiguration.CPID,
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	reference = {
		@Reference (
			name = CategoryManagementAppProperties.REF_CONTEXT,
			service = CategoryManagementAppContext.class,
			cardinality = ReferenceCardinality.MANDATORY
		)
	},
	property = {
		"digitaljourney.service.name=CategoryManagementApp"
	}
)
//@formatter:on
@Designate(ocd = CategoryManagementAppConfiguration.class)
@CustomRsProvider(CategoryManagementAppProperties.ADDRESS)
public class CategoryManagementAppResourceImpl extends AbstractResource<CategoryManagementAppContext, CategoryManagementAppConfiguration>
	implements CategoryManagementAppResource {

	@Reference
	private volatile CategoryManagementAppFacade cacheManagerAppFacade;

	/**
	 * Method called whenever the component is activated.
	 *
	 * @param ctx    Component context
	 * @param config Component configuration
	 */
	@Activate
	public void activate(ComponentContext ctx, CategoryManagementAppConfiguration config) {
		prepare(ctx, config);
	}

	/**
	 * Method called whenever the component configuration is modified.
	 *
	 * @param config Component configuration
	 */
	@Modified
	public void modified(CategoryManagementAppConfiguration config) {
		prepare(config);
	}
	
	@Override
	public CategoryResponseDTO createCategory(CategoryCreateDTO category) {
		return cacheManagerAppFacade.createCategory(category);
	}
	
	@Override
	public CategoryResponseDTO getCategory(Integer id) {
		return cacheManagerAppFacade.getCategory(id);
	}
	
	@Override
	public List<CategoryResponseDTO> getCategories() {
		return cacheManagerAppFacade.getCategories();
	}
	
	@Override
	public CategoryResponseDTO updateCategory(CategoryRequestDTO category) {
		return cacheManagerAppFacade.updateCategory(category);
	}
	
	@Override
	public CategoryResponseDTO deleteCategory(Integer id) {
		return cacheManagerAppFacade.deleteCategory(id);
	}

}
