/*-
 * #%L
 * Apps :: App to manage categories :: Core Agent
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
package io.digitaljourney.platform.plugins.apps.categorymanagementapp.agents.core.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.plugins.apps.categorymanagementapp.agent.CategoryManagementAppCoreAgent;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.common.api.agent.core.AbstractCategoryManagementAppCoreAgent;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.ProductManagementResource;

//@formatter:off
@Component(
	service = { CategoryManagementAppCoreAgent.class },
	configurationPid = CategoryManagementAppCoreAgentConfig.CPID,
	configurationPolicy = ConfigurationPolicy.REQUIRE
)
@Designate(ocd = CategoryManagementAppCoreAgentConfig.class)
//@formatter:on
public class CategoryManagementAppCoreAgentImpl extends AbstractCategoryManagementAppCoreAgent<CategoryManagementAppCoreAgentConfig>  {

	//FIXME Insert here your correlated microservice i.e resource
	/** The resource. */
	//@Reference
	//private volatile <microservice_name>Resource resource;

	@Reference
	private volatile ProductManagementResource categoriesResource;
	
	/**
	 * Method called whenever the component is activated.
	 *
	 * @param config Component configuration
	 */
	@Activate
	public void activate(CategoryManagementAppCoreAgentConfig config) {
		prepare(config);
	}

	/**
	 * Method called whenever the component configuration changes.
	 *
	 * @param config Component configuration
	 */
	@Modified
	public void modified(CategoryManagementAppCoreAgentConfig config) {
		prepare(config);
	}
	
//	@Override
//	protected <microservice_name>Resource getResource() {
//		return resource;
//	}

	@Override
	protected String getUsername() {
		return getConfig().systemUserName();
	}

	@Override
	protected String getPassword() {
		return getConfig().systemPassword();
	}

	public ProductManagementResource getCategoryResource() {
		return categoriesResource;
	}
}
