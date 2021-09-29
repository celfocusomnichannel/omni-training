/*-
 * #%L
 * Apps :: Training JWE Order Management App :: Core Agent
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
package io.digitaljourney.platform.plugins.apps.ordermanagement.agents.core.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
//import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.plugins.apps.ordermanagement.AppProperties;
//import io.digitaljourney.platform.modules.security.api.AuthenticationUtils;
import io.digitaljourney.platform.plugins.apps.ordermanagement.agent.OrderManagementCoreAgent;
import io.digitaljourney.platform.plugins.apps.ordermanagement.common.api.agent.core.AbstractOrderManagementCoreAgent;
import io.digitaljourney.platform.plugins.modules.configurationmanager.service.api.ConfigurationManagerResource;
import io.digitaljourney.platform.plugins.modules.configurationmanager.service.api.dto.ConfigurationSearchFilterDTO;
import io.digitaljourney.platform.plugins.modules.configurationmanager.service.api.dto.ConfigurationSearchResultDTO;
import io.digitaljourney.platform.plugins.modules.journeyblueprint.service.api.JourneyBlueprintResource;
import io.digitaljourney.platform.plugins.modules.journeyblueprint.service.api.dto.ActionBlueprintDTO;
import io.digitaljourney.platform.plugins.modules.journeyblueprint.service.api.dto.ActionBlueprintDTOBuilder;
import io.digitaljourney.platform.plugins.modules.journeyblueprint.service.api.dto.BlueprintHeaderDTO;
import io.digitaljourney.platform.plugins.modules.journeyblueprint.service.api.dto.CreateBlueprintDTO;
import io.digitaljourney.platform.plugins.modules.journeyblueprint.service.api.dto.CreateBlueprintDTOBuilder;
import io.digitaljourney.platform.plugins.modules.journeyblueprint.service.api.dto.CreateBlueprintHeaderDTOBuilder;
//FIXME Import here your correlated microservice
//import io.digitaljourney.platform.plugins.modules.<microservice_name>.service.api.<microservice_name>Resource;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.ProductManagementResource;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.CategoryDTO;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.ProductDTO;


//@formatter:off
@Component(
	service = { OrderManagementCoreAgent.class },
	configurationPid = OrderManagementCoreAgentConfig.CPID,
	configurationPolicy = ConfigurationPolicy.REQUIRE
)
@Designate(ocd = OrderManagementCoreAgentConfig.class)
//@formatter:on
public class OrderManagementCoreAgentImpl extends AbstractOrderManagementCoreAgent<OrderManagementCoreAgentConfig> implements OrderManagementCoreAgent {

	//FIXME Insert here your correlated microservice i.e resource
	/** The resource. */
	//@Reference
	//private volatile <microservice_name>Resource resource;
	
	@Reference
	private volatile ProductManagementResource productManagementResource;
	
	@Reference
	private volatile JourneyBlueprintResource journeyBlueprintResource;
	
	@Reference
	private volatile ConfigurationManagerResource configurationManagerResource;
	
	/**
	 * Method called whenever the component is activated.
	 *
	 * @param config Component configuration
	 */
	@Activate
	public void activate(OrderManagementCoreAgentConfig config) {
		prepare(config);
	}

	/**
	 * Method called whenever the component configuration changes.
	 *
	 * @param config Component configuration
	 */
	@Modified
	public void modified(OrderManagementCoreAgentConfig config) {
		prepare(config);
	}
	
//	@Override
//	protected <microservice_name>Resource getResource() {
//		return resource;
//	}
	
	@Override
	public String echo(String msg) {
		//FIXME Usually you sould use the microservice i.e resource (getResource()) to invoke some method
		//For example: return AuthenticationUtils.systemCall(getUsername(), getPassword(), () -> getResource().echo(msg));
		return msg;
	}
	
	@Override
	public String secureEcho(String channel, String msg) {
		//FIXME Usually you sould use the microservice i.e resource (getResource()) to invoke some method
		//For example: return AuthenticationUtils.systemCall(getUsername(), getPassword(), () -> getResource().secureEcho(channel, msg));
		return "Message sent by " + channel + " channel. Message: " + msg + ".";
	}

	@Override
	protected String getUsername() {
		return getConfig().systemUserName();
	}

	@Override
	protected String getPassword() {
		return getConfig().systemPassword();
	}
	
	@Override
	protected JourneyBlueprintResource getJourneyBlueprintResource() {
		return journeyBlueprintResource;
	}

	@Override
	protected ProductManagementResource getProductManagementResource() {
		return productManagementResource;
	}
	
	@Override
	public void createBlueprint(String journeyName, int journeyVersion, String path) {
		String content = null;

		try (InputStream is = OrderManagementCoreAgentImpl.class.getResourceAsStream(path)) {
			content = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines().collect(Collectors.joining(""));
		} catch (IOException e) {
		}
		String expression = "isActive==true;journeyName==" + journeyName;
//		List<BlueprintHeaderDTO> bts = AuthenticationUtils.systemCall(getConfig().systemUserName(), getConfig().systemPassword(), () -> journeyBlueprintResource.searchBlueprint(expression, null, null));
		List<BlueprintHeaderDTO> bts = journeyBlueprintResource.searchBlueprint(expression, null, null);

		if(bts == null || bts.isEmpty()){
			CreateBlueprintDTO createBlueprintDTO = new CreateBlueprintDTOBuilder()
					.withContent(content)
					.withHeader(new CreateBlueprintHeaderDTOBuilder()
							.withDescription(AppProperties.APP_NAME + "-blueprint")
							.withJourneyFriendlyName(journeyName)
							.withJourneyName(journeyName)
							.withMajorVersion(journeyVersion)
							.withMinorVersion(0)
							.build())
					.build();

//			BlueprintHeaderDTO blueprintHeaderDTO = AuthenticationUtils.systemCall(getConfig().systemUserName(), getConfig().systemPassword(), () -> journeyBlueprintResource.createBlueprint(createBlueprintDTO));
			BlueprintHeaderDTO blueprintHeaderDTO = journeyBlueprintResource.createBlueprint(createBlueprintDTO);

			ActionBlueprintDTO submit = new ActionBlueprintDTOBuilder()
					.withUpdatedby(AppProperties.APP_NAME + "-app")
					.build();
//			AuthenticationUtils.systemRun(getConfig().systemUserName(), getConfig().systemPassword(), ()->  {
//				journeyBlueprintResource.submitBlueprint(blueprintHeaderDTO.id, submit);
//				journeyBlueprintResource.publishBlueprint(blueprintHeaderDTO.id, submit);
//				});
			// No Impersonate yet
			journeyBlueprintResource.submitBlueprint(blueprintHeaderDTO.id, submit);
			journeyBlueprintResource.publishBlueprint(blueprintHeaderDTO.id, submit);
		}
	}


	@Override
	public List<ProductDTO> getProducts() {
		// No Impersonate yet
		return productManagementResource.getProducts();
	}

	@Override
	public ProductDTO getProduct(Integer productId) {
		return productManagementResource.getProduct(productId);
	}

	@Override
	public CategoryDTO getCategory(Integer categoryId) {
		return productManagementResource.getCategory(categoryId);
	}
	
	/**
	 * Gets the category id from configurations, using the configuration target
	 */
	@Override
	public ConfigurationSearchResultDTO getCategoryFromConfiguration() {
		ConfigurationSearchFilterDTO filter = new ConfigurationSearchFilterDTO();
		filter.target = "omni-training.ordermanagement.category";
		//FIX:ME by default 0 and 150 but this needs to be fixed in order to receive this values or made this defaults as configurations
		filter.offset = 0;
		filter.limit = 150;
		return fromConfigResource((config) -> config.searchByFilter(filter), null);
	}
	
	private <T> T fromConfigResource(Function<ConfigurationManagerResource, T> func, T defaultValue) {
		if(configurationManagerResource == null) {
			return defaultValue;
		}
		return func.apply(configurationManagerResource);
	}

}
