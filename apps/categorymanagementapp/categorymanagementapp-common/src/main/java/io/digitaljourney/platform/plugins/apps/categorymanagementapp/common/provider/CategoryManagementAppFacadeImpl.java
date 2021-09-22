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
package io.digitaljourney.platform.plugins.apps.categorymanagementapp.common.provider;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.security.api.AbstractSecurityComponent;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.agent.CategoryManagementAppCoreAgent;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.common.api.facade.CategoryManagementAppFacade;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.model.CategoryCreateDTO;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.model.CategoryRequestDTO;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.model.CategoryResponseDTO;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.CategoryManagementAppProperties;

//@formatter:off
@Component(
        service = { CategoryManagementAppFacade.class },
        configurationPid = CategoryManagementAppFacadeConfig.CPID,
        configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = CategoryManagementAppFacadeConfig.class)
//@formatter:on
public class CategoryManagementAppFacadeImpl extends AbstractSecurityComponent<CategoryManagementAppFacadeContext, CategoryManagementAppFacadeConfig> implements CategoryManagementAppFacade {

    @Reference(cardinality = ReferenceCardinality.OPTIONAL, policyOption = ReferencePolicyOption.GREEDY, policy = ReferencePolicy.DYNAMIC)
    private volatile CategoryManagementAppCoreAgent coreAgent;
    
    @RequiresAuthentication
    private CategoryManagementAppCoreAgent getCoreAgent() {
        if (coreAgent == null) {
            coreAgent = getService(CategoryManagementAppCoreAgent.class);
        }
        return coreAgent;
    }
    
    @Override
    @RequiresPermissions(CategoryManagementAppProperties.PERMISSION_CREATE)
    public CategoryResponseDTO createCategory(CategoryCreateDTO category) {
    	return getCoreAgent().createCategory(category);
    }
    
    @Override
    @RequiresPermissions(CategoryManagementAppProperties.PERMISSION_READ)
    public CategoryResponseDTO getCategory(Integer id) {
    	return getCoreAgent().getCategory(id);
    }
    
    @Override
    @RequiresPermissions(CategoryManagementAppProperties.PERMISSION_READ)
    public List<CategoryResponseDTO> getCategories(){
    	List<CategoryResponseDTO> categories = getCoreAgent().getCategories();
    	return categories;
    }
    
    @Override
    @RequiresPermissions(CategoryManagementAppProperties.PERMISSION_UPDATE)
    public CategoryResponseDTO updateCategory(CategoryRequestDTO category) {
    	return getCoreAgent().updateCategory(category.categoryId,category);
    }
    
    @Override
    @RequiresPermissions(CategoryManagementAppProperties.PERMISSION_DELETE)
    public CategoryResponseDTO deleteCategory(Integer id) {
    	return getCoreAgent().deleteCategory(id);
    }
}
