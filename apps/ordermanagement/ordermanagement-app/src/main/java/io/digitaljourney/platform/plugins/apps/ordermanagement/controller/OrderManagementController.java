package io.digitaljourney.platform.plugins.apps.ordermanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.eclipse.gemini.blueprint.extensions.annotation.ServiceReference;
import org.osgi.service.component.annotations.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.digitaljourney.platform.modules.commons.PStrings;
import io.digitaljourney.platform.plugins.apps.ordermanagement.AppProperties;
import io.digitaljourney.platform.plugins.apps.ordermanagement.api.JourneyControllerResource;
import io.digitaljourney.platform.plugins.apps.ordermanagement.common.api.facade.OrderManagementFacade;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomJourneyDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomerInfoDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.OrderDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.instance.CustomJourneyInstance;
import io.digitaljourney.platform.plugins.apps.ordermanagement.mapper.JourneyControllerMapper;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.api.trigger.ActionMode;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.JourneyProcess;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.annotation.JourneyMethod;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.annotation.JourneyReference;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.session.JourneySession;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.CategoryDTO;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.ProductDTO;
import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CmsRsProvider;

/**
 * Simple Journey with 3 APIs:
 * - Create
 * - Read
 * - Update
 * - Finish
 * 
 * The matching journey map is included in this project and can be imported into Journey Designer (custom_journey.zip)
 */
@Controller
@Component(
	    property = {
	            "digitaljourney.service.name=OrderManagement"
	    }
	)
@CmsRsProvider(value = AppProperties.ADDRESS + "/app")
@RequestMapping(AppProperties.ADDRESS + "/app")
public class OrderManagementController extends AbstractAppController implements JourneyControllerResource, JourneyProcess<CustomJourneyInstance> {

	@ServiceReference
	private OrderManagementFacade orderManagementFacade;
	
	/**
	 * Initializes blueprint import
	 */
	@Override
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@RequiresPermissions(AppProperties.PERMISSION_CREATE)
	public @ResponseBody void init() {
		info("Entering init");
		orderManagementFacade.init(AppProperties.JOURNEY_NAME, AppProperties.JOURNEY_VERSION, "/journey-blueprint/blueprint.json");
	}
	
	
	/**
	 * Create action mode creates a new empty instance
	 */
	@JourneyMethod(value = "CREATE", mode = ActionMode.CREATE)
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@RequiresPermissions(AppProperties.PERMISSION_CREATE)
	@Override
	public @ResponseBody CustomJourneyDTO create() {
		info("Entering create");
		// Retrieves the instance from JWE (will be empty because it's a CREATE action)
		CustomJourneyInstance instance = JourneySession.getInstance(this);

		return JourneyControllerMapper.INSTANCE.toProcess(instance);
	}
	
	/**
	 * Retrieves existing instance with a given ID
	 */
	@JourneyMethod(value = "READ PROCESS")
	@RequestMapping(value = "/{instanceId}", method = RequestMethod.GET)
	@RequiresPermissions(AppProperties.PERMISSION_READ)
	@Override
	public @ResponseBody CustomJourneyDTO read(@PathVariable @JourneyReference Long instanceId) {

		// retrieves the existing instance from JWE and transforms into DTO
		CustomJourneyInstance instance = JourneySession.getInstance(this);
		return JourneyControllerMapper.INSTANCE.toProcess(instance);
	}
	
	/**
	 * Retrieves products for instance with a given ID
	 */
	@Override
	@JourneyMethod(value = "GET PRODUCTS LIST")
	@RequestMapping(value = "/{instanceId}/products", method = RequestMethod.GET)
	@RequiresPermissions(AppProperties.PERMISSION_READ)
	public @ResponseBody List<ProductDTO> getProducts(@PathVariable @JourneyReference Long instanceId) {
		info("Entering getProducts");
		return orderManagementFacade.getProductList();
	}
	
	/**
	 * Retrieves category for instance with a given ID
	 */
	@Override
	@JourneyMethod(value = "GET CATEGORIES LIST")
	@RequestMapping(value = "/{instanceId}/category", method = RequestMethod.GET)
	@RequiresPermissions(AppProperties.PERMISSION_READ)
	public @ResponseBody CategoryDTO getCategory(@PathVariable @JourneyReference Long instanceId) {
		info("Entering getCategory");
		return orderManagementFacade.getCategory();
	}
	
	/**
	 * Retrieves delivery options for instance with a given ID
	 */
	@Override
	@JourneyMethod(value = "GET DELIVERY OPTIONS")
	@RequestMapping(value = "/{instanceId}/delivery-options", method = RequestMethod.GET)
	@RequiresPermissions(AppProperties.PERMISSION_UPDATE)
	public @ResponseBody List<HashMap<String, Object>> getDeliveryOptions(@PathVariable @JourneyReference Long instanceId) {
		info("Entering getDeliveryOptions");
		// retrieves the existing instance from JWE
		
		return orderManagementFacade.getDeliveryOptions();
		
	}

	@JourneyMethod(value = "UPDATE PRODUCT SELECTED")
	@RequestMapping(value = "/{instanceId}/products/{productId}", method = RequestMethod.PUT)
	@RequiresPermissions(AppProperties.PERMISSION_UPDATE)
	@Override
	public @ResponseBody CustomJourneyDTO selectProduct(@PathVariable @JourneyReference Long instanceId, @PathVariable Integer productId) {
		// retrieves the existing instance from JWE
		info("Entering selectProduct");
		CustomJourneyInstance instance = JourneySession.getInstance(this);
		
		ProductDTO product = orderManagementFacade.getProduct(productId);
		
		if(product == null) {
			error("Product does not exist");
			throw getCtx().exception("Product does not exist");
		}
		
		instance.products.add(product);
		
		return JourneyControllerMapper.INSTANCE.toProcess(instance);
	}

	@JourneyMethod(value = "CREATE ORDER REQUEST")
	@RequestMapping(value = "/{instanceId}/order-create", method = RequestMethod.POST)
	@RequiresPermissions(AppProperties.PERMISSION_UPDATE)
	@Override
	public @ResponseBody CustomJourneyDTO createOrder(@PathVariable @JourneyReference Long instanceId) {
		info("Entering createOrder");
		Random rand = new Random();
		// retrieves the existing instance from JWE
		CustomJourneyInstance instance = JourneySession.getInstance(this);
		
		OrderDTO order = new OrderDTO();
		
		// For the purpose of the exercise, the id will be a random number generated between 0-50
		order.id = rand.nextInt(51) ;
		instance.order = order;
		
		instance.order.products = instance.products;
		
		return JourneyControllerMapper.INSTANCE.toProcess(instance);
	}


	@JourneyMethod(value = "UPDATE CUSTOMER INFORMATION")
	@RequestMapping(value = "/{instanceId}/customer-info", method = RequestMethod.POST)
	@RequiresPermissions(AppProperties.PERMISSION_UPDATE)
	@Override
	public @ResponseBody CustomJourneyDTO updateCustomerInfo(@PathVariable @JourneyReference Long instanceId, @RequestBody CustomerInfoDTO customerInfo) {
		info("Entering updateCustomerInfo");
		// retrieves the existing instance from JWE
		CustomJourneyInstance instance = JourneySession.getInstance(this);

		boolean validCustomer = customerInfo.customer != null && 
				!PStrings.isNullOrEmpty(customerInfo.customer.name) && 
				!PStrings.isNullOrEmpty(customerInfo.customer.address);
		
		if(!validCustomer || PStrings.isNullOrEmpty(customerInfo.deliveryOption)) {
			error("Invalid customer information");
			throw getCtx().exception("Invalid customer information");
		}
		
		boolean deliveryOptionExists = orderManagementFacade.deliveryOptionExists(customerInfo.deliveryOption);
		
		if(!deliveryOptionExists) {
			error("Invalid delivery option");
			throw getCtx().exception("Invalid delivery option");
		}
		
		instance.customer = customerInfo.customer;
		instance.order.address= customerInfo.customer.address;
		instance.order.delivery = customerInfo.deliveryOption;
		
		return JourneyControllerMapper.INSTANCE.toProcess(instance);
	}


	@JourneyMethod(value = "SUBMIT ORDER REQUEST")
	@RequestMapping(value = "/{instanceId}/order-submit", method = RequestMethod.POST)
	@RequiresPermissions(AppProperties.PERMISSION_UPDATE)
	@Override
	public @ResponseBody CustomJourneyDTO submitOrder(@PathVariable @JourneyReference Long instanceId) {
		info("Entering submitOrder");
		CustomJourneyInstance instance = JourneySession.getInstance(this);
		return JourneyControllerMapper.INSTANCE.toProcess(instance);
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
