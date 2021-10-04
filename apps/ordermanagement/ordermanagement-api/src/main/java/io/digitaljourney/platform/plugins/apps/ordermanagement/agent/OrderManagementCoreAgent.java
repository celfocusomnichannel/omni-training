/*-
 * #%L
 * Apps :: Training JWE Order Management App :: API
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
package io.digitaljourney.platform.plugins.apps.ordermanagement.agent;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

import io.digitaljourney.platform.plugins.modules.configurationmanager.service.api.dto.ConfigurationSearchResultDTO;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.CategoryDTO;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.ProductDTO;

@ProviderType
public interface OrderManagementCoreAgent {
	
	public List<ProductDTO> getProducts();
	
	public void createBlueprint(String journeyName, int journeyVersion, String blueprintContent);

	public ProductDTO getProduct(Integer productId);

	public CategoryDTO getCategory(Integer categoryId);

	public ConfigurationSearchResultDTO getCategoryFromConfiguration();

}
