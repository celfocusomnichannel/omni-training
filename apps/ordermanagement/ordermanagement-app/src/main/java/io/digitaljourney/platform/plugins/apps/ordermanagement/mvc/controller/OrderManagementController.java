package io.digitaljourney.platform.plugins.apps.ordermanagement.mvc.controller;

import java.io.IOException;
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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.digitaljourney.platform.modules.events.api.PlatformEventManagerConstants;
import io.digitaljourney.platform.modules.events.api.PlatformEventReceiver;
import io.digitaljourney.platform.plugins.apps.ordermanagement.AppProperties;
import io.digitaljourney.platform.plugins.apps.ordermanagement.api.OrderManagementResource;
import io.digitaljourney.platform.plugins.apps.ordermanagement.common.api.facade.OrderManagementFacade;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomJourneyDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomerInfoDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.websocket.JourneyStatusDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.websocket.JourneyUpdateDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.websocket.WebSocketDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.events.ProcessContinuityEvent;
import io.digitaljourney.platform.plugins.apps.ordermanagement.instance.CustomJourneyInstance;
import io.digitaljourney.platform.plugins.apps.ordermanagement.websocket.KarafWebSocketServlet;
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
		service = {PlatformEventReceiver.class},
	    property = {
	            "digitaljourney.service.name=OrderManagement",
	            PlatformEventManagerConstants.RECEIVER + "=events/topic1"
	    }
		
	)
public class OrderManagementController extends AbstractAppController implements OrderManagementResource, JourneyProcess<CustomJourneyInstance>, PlatformEventReceiver {

	private static final String ORDERMANAGEMENT = "ordermanagement";
	
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
		
		WebSocketDTO dto = new WebSocketDTO();
		
		dto.setInstanceId(Long.toString(instanceId));
		
		try {
			KarafWebSocketServlet.publishMessage(dto,ORDERMANAGEMENT);
		} catch (IOException e) {
			error("Could not publish message on websocket : exception - {}",e.getMessage());
		}
	
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
		
		WebSocketDTO dto = new WebSocketDTO();
		
		dto.setInstanceId(Long.toString(instanceId));
		
		try {
			KarafWebSocketServlet.publishMessage(dto,ORDERMANAGEMENT);
		} catch (IOException e) {
			error("Could not publish message on websocket : exception - {}",e.getMessage());
		}
		
		return facade.selectProduct(this, productId);
	}

	@PostMapping("/{instanceId}/order-create")
	@JourneyMethod(value = "CREATE ORDER REQUEST")
	@Override
	public @ResponseBody CustomJourneyDTO createOrder(@PathVariable @JourneyReference Long instanceId) {
		info("Entering createOrder");
		
		WebSocketDTO dto = new WebSocketDTO();
		
		dto.setInstanceId(Long.toString(instanceId));
		
		try {
			KarafWebSocketServlet.publishMessage(dto,ORDERMANAGEMENT);
		} catch (IOException e) {
			error("Could not publish message on websocket : exception - {}",e.getMessage());
		}
		
		return facade.createOrder(this);
	}

	@PostMapping("/{instanceId}/customer-info")
	@JourneyMethod(value = "UPDATE CUSTOMER INFORMATION")
	@Override
	public @ResponseBody CustomJourneyDTO updateCustomerInfo(@PathVariable @JourneyReference Long instanceId, @RequestBody CustomerInfoDTO customerInfo) {
		info("Entering updateCustomerInfo");
		
		WebSocketDTO dto = new WebSocketDTO();
		
		dto.setInstanceId(Long.toString(instanceId));
		
		try {
			KarafWebSocketServlet.publishMessage(dto,ORDERMANAGEMENT);
		} catch (IOException e) {
			error("Could not publish message on websocket : exception - {}",e.getMessage());
		}
		
		return facade.updateCustomerInfo(this, customerInfo);
	}

	@PostMapping("/{instanceId}/order-submit")
	@JourneyMethod(value = "SUBMIT ORDER REQUEST")
	@Override
	public @ResponseBody CustomJourneyDTO submitOrder(@PathVariable @JourneyReference Long instanceId) {
		info("Entering submitOrder");
		
		WebSocketDTO dto = new WebSocketDTO();
		
		dto.setInstanceId(Long.toString(instanceId));
		
		try {
			KarafWebSocketServlet.publishMessage(dto,ORDERMANAGEMENT);
		} catch (IOException e) {
			error("Could not publish message on websocket : exception - {}",e.getMessage());
		}
		
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

	private static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
		mapper.registerModule(new JavaTimeModule());
	}
	
	@Override
	public void handleEvent(String topic, Object object) {
		if (object != null && object instanceof String) {

			try {

				ProcessContinuityEvent event = mapper.readValue(object.toString(), ProcessContinuityEvent.class);

				String processType = event.processType;
				String eventType = event.eventType.toString();

				if (eventType == ProcessContinuityEvent.EventType.STATUS_CHANGED.toString()
						&& AppProperties.JOURNEY_NAME.equals(processType) && event.instanceId != null) {

					JourneyStatusDTO dto = new JourneyStatusDTO();
					dto.id = event.instanceId.toString();
					dto.state = event.state.toString();
					dto.status = event.status;

					KarafWebSocketServlet.publishMessage(dto, ORDERMANAGEMENT);
				}

				if (eventType == ProcessContinuityEvent.EventType.UPDATED.toString()
						&& AppProperties.JOURNEY_NAME.equals(processType) && event.instanceId != null) {

					JourneyUpdateDTO dto = new JourneyUpdateDTO();
					dto.id = event.instanceId.toString();
					dto.state = event.state.toString();
					dto.status = event.status;
					dto.updatedBy = event.updatedBy;
					dto.updatedChannel = event.updatedChannel;
					KarafWebSocketServlet.publishMessage(dto, ORDERMANAGEMENT);
				}

			} catch (Throwable t) {
				error("Failed to handle event", t);
			}
		}

	}
	
}
