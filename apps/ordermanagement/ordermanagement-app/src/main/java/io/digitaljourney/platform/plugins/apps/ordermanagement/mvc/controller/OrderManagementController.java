package io.digitaljourney.platform.plugins.apps.ordermanagement.mvc.controller;

import java.util.HashMap;
import java.util.List;

import org.eclipse.gemini.blueprint.extensions.annotation.ServiceReference;
import org.osgi.service.component.annotations.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.digitaljourney.platform.plugins.apps.ordermanagement.AppProperties;
import io.digitaljourney.platform.plugins.apps.ordermanagement.api.OrderManagementResource;
import io.digitaljourney.platform.plugins.apps.ordermanagement.common.api.facade.OrderManagementFacade;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomJourneyDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomerInfoDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.instance.CustomJourneyInstance;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.api.trigger.ActionMode;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.JourneyProcess;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.annotation.JourneyMethod;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.annotation.JourneyReference;
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
@RequestMapping(AppProperties.ADDRESS + "/app")
@CmsRsProvider(value = AppProperties.ADDRESS + "/app")
@Component(
	    property = {
	            "digitaljourney.service.name=OrderManagement"
	    }
	)
public class OrderManagementController extends AbstractAppController implements OrderManagementResource, JourneyProcess<CustomJourneyInstance> {

	@ServiceReference
	private OrderManagementFacade facade;
	
	@GetMapping("/init")
	@Override
	public @ResponseBody void init() {
		info("Entering init");
		facade.init(AppProperties.JOURNEY_NAME, AppProperties.JOURNEY_VERSION);
	}
	
	@PostMapping("/")
	@JourneyMethod(value = "CREATE", mode = ActionMode.CREATE)
	@Override
	public @ResponseBody CustomJourneyDTO create() {
		info("Entering create");
		return facade.create(this);
	}
	
	@GetMapping("/{instanceId}")
	@JourneyMethod(value = "READ PROCESS")
	@Override
	public @ResponseBody CustomJourneyDTO read(@PathVariable @JourneyReference Long instanceId) {
		info("Entering read");
		return facade.read(this);
	}
	
	@GetMapping("/{instanceId}/products")
	@JourneyMethod(value = "GET PRODUCTS LIST")
	@Override
	public @ResponseBody List<ProductDTO> getProductList(@PathVariable @JourneyReference Long instanceId) {
		info("Entering getProducts");
		return facade.getProductList();
	}
	
	@GetMapping("/{instanceId}/category")
	@JourneyMethod(value = "GET CATEGORIES LIST")
	@Override
	public @ResponseBody CategoryDTO getCategory(@PathVariable @JourneyReference Long instanceId) {
		info("Entering getCategory");
		return facade.getCategory();
	}
	
	@GetMapping("/{instanceId}/delivery-options")
	@JourneyMethod(value = "GET DELIVERY OPTIONS")
	@Override
	public @ResponseBody List<HashMap<String, Object>> getDeliveryOptions(@PathVariable @JourneyReference Long instanceId) {
		info("Entering getDeliveryOptions");
		return facade.getDeliveryOptions();
	}

	@PutMapping("/{instanceId}/products/{productId}")
	@JourneyMethod(value = "UPDATE PRODUCT SELECTED")
	@Override
	public @ResponseBody CustomJourneyDTO selectProduct(@PathVariable @JourneyReference Long instanceId, @PathVariable Integer productId) {
		info("Entering selectProduct");
		return facade.selectProduct(this, productId);
	}

	@PostMapping("/{instanceId}/order-create")
	@JourneyMethod(value = "CREATE ORDER REQUEST")
	@Override
	public @ResponseBody CustomJourneyDTO createOrder(@PathVariable @JourneyReference Long instanceId) {
		info("Entering createOrder");
		return facade.createOrder(this);
	}

	@PostMapping("/{instanceId}/customer-info")
	@JourneyMethod(value = "UPDATE CUSTOMER INFORMATION")
	@Override
	public @ResponseBody CustomJourneyDTO updateCustomerInfo(@PathVariable @JourneyReference Long instanceId, @RequestBody CustomerInfoDTO customerInfo) {
		info("Entering updateCustomerInfo");
		return facade.updateCustomerInfo(this, customerInfo);
	}

	@PostMapping("/{instanceId}/order-submit")
	@JourneyMethod(value = "SUBMIT ORDER REQUEST")
	@Override
	public @ResponseBody CustomJourneyDTO submitOrder(@PathVariable @JourneyReference Long instanceId) {
		info("Entering submitOrder");
		return facade.submitOrder(this);
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
