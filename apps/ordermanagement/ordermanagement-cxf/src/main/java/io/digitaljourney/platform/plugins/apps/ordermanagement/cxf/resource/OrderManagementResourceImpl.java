/*-
 * #%L
 * Apps :: Training JWE Order Management App :: CXF
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
package io.digitaljourney.platform.plugins.apps.ordermanagement.cxf.resource;

import java.util.HashMap;
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
import io.digitaljourney.platform.plugins.apps.ordermanagement.AppProperties;
import io.digitaljourney.platform.plugins.apps.ordermanagement.api.OrderManagementResource;
import io.digitaljourney.platform.plugins.apps.ordermanagement.common.api.facade.OrderManagementFacade;
import io.digitaljourney.platform.plugins.apps.ordermanagement.cxf.OrderManagementConfiguration;
import io.digitaljourney.platform.plugins.apps.ordermanagement.cxf.OrderManagementContext;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomJourneyDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomerInfoDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.instance.CustomJourneyInstance;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.api.trigger.ActionMode;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.JourneyProcess;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.annotation.JourneyMethod;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.annotation.JourneyReference;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.CategoryDTO;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.ProductDTO;
import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CustomRsProvider;

//@formatter:off
//OSGi setup and configuration.
@Component(
	service = { OrderManagementResource.class },
	configurationPid = OrderManagementConfiguration.CPID,
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	reference = {
		@Reference (
			name = AppProperties.REF_CONTEXT,
			service = OrderManagementContext.class,
			cardinality = ReferenceCardinality.MANDATORY
		)
	},
	property = {
		"digitaljourney.service.name=OrderManagement"
	}
)
//@formatter:on
@Designate(ocd = OrderManagementConfiguration.class)
@CustomRsProvider(AppProperties.ADDRESS)
public class OrderManagementResourceImpl extends AbstractResource<OrderManagementContext, OrderManagementConfiguration>
	implements OrderManagementResource, JourneyProcess<CustomJourneyInstance> {

	@Reference
	private volatile OrderManagementFacade orderManagementFacade;

	/**
	 * Method called whenever the component is activated.
	 *
	 * @param ctx    Component context
	 * @param config Component configuration
	 */
	@Activate
	public void activate(ComponentContext ctx, OrderManagementConfiguration config) {
		prepare(ctx, config);
	}

	/**
	 * Method called whenever the component configuration is modified.
	 *
	 * @param config Component configuration
	 */
	@Modified
	public void modified(OrderManagementConfiguration config) {
		prepare(config);
	}

	@Override
	public void init() {
		info("Entering init");
		orderManagementFacade.init(AppProperties.JOURNEY_NAME, AppProperties.JOURNEY_VERSION);
	}

	@JourneyMethod(value = "CREATE", mode = ActionMode.CREATE)
	@Override
	public CustomJourneyDTO create() {
		info("Entering create");
		return orderManagementFacade.create(this);
	}

	@JourneyMethod(value = "READ PROCESS")
	@Override
	public CustomJourneyDTO read(@JourneyReference Long instanceId) {
		info("Entering read");
		return orderManagementFacade.read(this);
	}

	@JourneyMethod(value = "GET PRODUCTS LIST")
	@Override
	public List<ProductDTO> getProductList(@JourneyReference Long instanceId) {
		info("Entering getProducts");
		return orderManagementFacade.getProductList();
	}

	@JourneyMethod(value = "GET CATEGORIES LIST")
	@Override
	public CategoryDTO getCategory(@JourneyReference Long instanceId) {
		info("Entering getCategory");
		return orderManagementFacade.getCategory();
	}

	@JourneyMethod(value = "GET DELIVERY OPTIONS")
	@Override
	public List<HashMap<String, Object>> getDeliveryOptions(@JourneyReference Long instanceId) {
		info("Entering getDeliveryOptions");
		return orderManagementFacade.getDeliveryOptions();
	}

	@JourneyMethod(value = "UPDATE PRODUCT SELECTED")
	@Override
	public CustomJourneyDTO selectProduct(@JourneyReference Long instanceId, Integer productId) {
		info("Entering selectProduct");
		return orderManagementFacade.selectProduct(this, productId);
	}

	@JourneyMethod(value = "CREATE ORDER REQUEST")
	@Override
	public CustomJourneyDTO createOrder(@JourneyReference Long instanceId) {
		info("Entering createOrder");
		return orderManagementFacade.createOrder(this);
	}

	@JourneyMethod(value = "UPDATE CUSTOMER INFORMATION")
	@Override
	public CustomJourneyDTO updateCustomerInfo(@JourneyReference Long instanceId, CustomerInfoDTO customerInfo) {
		info("Entering updateCustomerInfo");
		return orderManagementFacade.updateCustomerInfo(this, customerInfo);
	}

	@JourneyMethod(value = "SUBMIT ORDER REQUEST")
	@Override
	public CustomJourneyDTO submitOrder(@JourneyReference Long instanceId) {
		info("Entering submitOrder");
		return orderManagementFacade.submitOrder(this);
	}
	
	@Override
	public String journeyName() {
		return AppProperties.JOURNEY_NAME;
	}

	@Override
	public int majorVersion() {
		return AppProperties.JOURNEY_VERSION;
	}

	@Override
	public Class<CustomJourneyInstance> getInstanceClass() {
		return CustomJourneyInstance.class;
	}

}
