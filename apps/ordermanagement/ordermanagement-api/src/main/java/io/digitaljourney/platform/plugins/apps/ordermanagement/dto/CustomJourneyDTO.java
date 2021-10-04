package io.digitaljourney.platform.plugins.apps.ordermanagement.dto;

import java.util.ArrayList;
import java.util.List;

import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.dto.JourneyResponseDTO;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.ProductDTO;

public class CustomJourneyDTO extends JourneyResponseDTO {
	public List<ProductDTO> products = new ArrayList<ProductDTO>();
	public OrderDTO order;
	public CustomerDTO customer;
}
