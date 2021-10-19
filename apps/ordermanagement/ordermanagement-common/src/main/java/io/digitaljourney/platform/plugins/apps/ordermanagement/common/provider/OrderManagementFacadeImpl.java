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
package io.digitaljourney.platform.plugins.apps.ordermanagement.common.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.commons.PStrings;
import io.digitaljourney.platform.modules.security.api.AbstractSecurityComponent;
import io.digitaljourney.platform.plugins.apps.ordermanagement.AppProperties;
import io.digitaljourney.platform.plugins.apps.ordermanagement.agent.OrderManagementCoreAgent;
import io.digitaljourney.platform.plugins.apps.ordermanagement.common.api.facade.OrderManagementFacade;
import io.digitaljourney.platform.plugins.apps.ordermanagement.common.mapper.OrderManagementMapper;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomJourneyDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomerInfoDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.OrderDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.instance.CustomJourneyInstance;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.JourneyProcess;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.session.JourneySession;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.CategoryDTO;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.ProductDTO;

//@formatter:off
@Component(
        service = { OrderManagementFacade.class },
        configurationPid = OrderManagementFacadeConfig.CPID,
        configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = OrderManagementFacadeConfig.class)
//@formatter:on
public class OrderManagementFacadeImpl extends AbstractSecurityComponent<OrderManagementFacadeContext, OrderManagementFacadeConfig>
	implements OrderManagementFacade {

	/** Core Agent instance */
    @Reference(cardinality = ReferenceCardinality.OPTIONAL, policyOption = ReferencePolicyOption.GREEDY, policy = ReferencePolicy.DYNAMIC)
    private volatile OrderManagementCoreAgent coreAgent;
    
	private List<HashMap<String, Object>> deliveryOptions = createDeliveryOptions();

    private OrderManagementCoreAgent getCoreAgent() {
        if (coreAgent == null) {
            coreAgent = getService(OrderManagementCoreAgent.class);
        }
        return coreAgent;
    }
    
	private CustomJourneyDTO process(JourneyProcess<CustomJourneyInstance> jp) {
		CustomJourneyInstance instance = JourneySession.getInstance(jp);
		return OrderManagementMapper.INSTANCE.toProcess(instance);
	}

	@RequiresPermissions(AppProperties.PERMISSION_CREATE)
	@Override
	public void init(String journeyName, int journeyVersion) {
		String content = null;

		try (InputStream is = OrderManagementFacadeImpl.class.getResourceAsStream("/journey-blueprint/blueprint.json")) {
			content = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines().collect(Collectors.joining(""));
		} catch (IOException e) {
		}
		
		getCoreAgent().createBlueprint(journeyName, journeyVersion, content);
	}

	@RequiresPermissions(AppProperties.PERMISSION_CREATE)
	@Override
	public CustomJourneyDTO create(JourneyProcess<CustomJourneyInstance> jp) {
		// Retrieves the instance from JWE (will be empty because it's a CREATE action)
		return process(jp);
	}

	@RequiresPermissions(AppProperties.PERMISSION_READ)
	@Override
	public CustomJourneyDTO read(JourneyProcess<CustomJourneyInstance> jp) {
		return process(jp);
	}

	@RequiresPermissions(AppProperties.PERMISSION_READ)
	@Override
	public List<ProductDTO> getProductList() {
		Integer categoryId = getCategoryId();

		return getCoreAgent().getProducts().stream().filter(product -> categoryId == product.getCategory().getCategoryId()).collect(Collectors.toList());
	}
	
	@RequiresPermissions(AppProperties.PERMISSION_READ)
	@Override
	public CategoryDTO getCategory() {
		Integer categoryId = getCategoryId();
		return getCoreAgent().getCategory(categoryId);
	}

	@RequiresPermissions(AppProperties.PERMISSION_UPDATE)
	@Override
	public List<HashMap<String, Object>> getDeliveryOptions() {
		// Mocked delivery options
		return deliveryOptions;
	}

	@RequiresPermissions(AppProperties.PERMISSION_UPDATE)
	@Override
	public CustomJourneyDTO selectProduct(JourneyProcess<CustomJourneyInstance> jp, Integer productId) {
		CustomJourneyInstance instance = JourneySession.getInstance(jp);

		ProductDTO product = getProduct(productId);

		if (product == null) {
			error("Product does not exist");
			throw getCtx().exception("Product does not exist");
		}

		if(!instance.products.contains(product))
			instance.products.add(product);

		return OrderManagementMapper.INSTANCE.toProcess(instance);
	}
	
	@RequiresPermissions(AppProperties.PERMISSION_UPDATE)
	@Override
	public CustomJourneyDTO createOrder(JourneyProcess<CustomJourneyInstance> jp) {
		Random rand = new Random();
		// retrieves the existing instance from JWE
		CustomJourneyInstance instance = JourneySession.getInstance(jp);
		
		OrderDTO order = new OrderDTO();
		
		// For the purpose of the exercise, the id will be a random number generated between 0-50
		order.id = rand.nextInt(51) ;
		instance.order = order;
		
		instance.order.products = instance.products;
		
		return OrderManagementMapper.INSTANCE.toProcess(instance);
	}
	
	@RequiresPermissions(AppProperties.PERMISSION_UPDATE)
	@Override
	public CustomJourneyDTO updateCustomerInfo(JourneyProcess<CustomJourneyInstance> jp, CustomerInfoDTO customerInfo) {
		// retrieves the existing instance from JWE
		CustomJourneyInstance instance = JourneySession.getInstance(jp);

		boolean validCustomer = customerInfo.customer != null && 
				!PStrings.isNullOrEmpty(customerInfo.customer.name) && 
				!PStrings.isNullOrEmpty(customerInfo.customer.address);
		
		if(!validCustomer || PStrings.isNullOrEmpty(customerInfo.deliveryOption)) {
			error("Invalid customer information");
			throw getCtx().exception("Invalid customer information");
		}
		
		boolean deliveryOptionExists = deliveryOptionExists(customerInfo.deliveryOption);
		
		if(!deliveryOptionExists) {
			error("Invalid delivery option");
			throw getCtx().exception("Invalid delivery option");
		}
		
		instance.customer = customerInfo.customer;
		instance.order.address= customerInfo.customer.address;
		instance.order.delivery = customerInfo.deliveryOption;
		
		return OrderManagementMapper.INSTANCE.toProcess(instance);
	}
	
	@RequiresPermissions(AppProperties.PERMISSION_UPDATE)
	@Override
	public CustomJourneyDTO submitOrder(JourneyProcess<CustomJourneyInstance> jp) {
		return process(jp);
	}

	@Override
	public ProductDTO getProduct(Integer productId) {
		return getCoreAgent().getProduct(productId);
	}

	@Override
	public boolean deliveryOptionExists(String deliveryOption) {
		return deliveryOptions.stream().map(hashmap -> hashmap.get("name")).anyMatch(name -> deliveryOption.equals(name));
	}

	/**
	 * 
	 * @return Mocked hash map of delivery options
	 */
	private List<HashMap<String, Object>> createDeliveryOptions() {
		List<HashMap<String, Object>> deliveryOptions = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> homeOption = new HashMap<String, Object>();
		homeOption.put("id", 1);
		homeOption.put("name", "home");

		HashMap<String, Object> storeOption = new HashMap<String, Object>();
		storeOption.put("id", 2);
		storeOption.put("name", "store");

		deliveryOptions.add(homeOption);
		deliveryOptions.add(storeOption);
		return deliveryOptions;
	}

	private Integer getCategoryId() {
		return Integer.parseInt(getCoreAgent().getCategoryFromConfiguration().result.stream().findFirst().get().value);
	}

}
