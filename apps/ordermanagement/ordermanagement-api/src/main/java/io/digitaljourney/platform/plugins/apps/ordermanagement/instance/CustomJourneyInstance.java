package io.digitaljourney.platform.plugins.apps.ordermanagement.instance;

import java.util.ArrayList;
import java.util.List;

import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomerDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.OrderDTO;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.session.JourneyInstance;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.ProductDTO;

public class CustomJourneyInstance extends JourneyInstance {
	public List<ProductDTO> products = new ArrayList<ProductDTO>();
	public OrderDTO order;
	public CustomerDTO customer;
}
