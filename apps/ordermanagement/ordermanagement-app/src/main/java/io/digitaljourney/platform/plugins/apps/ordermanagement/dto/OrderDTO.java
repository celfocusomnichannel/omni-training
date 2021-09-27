package io.digitaljourney.platform.plugins.apps.ordermanagement.dto;

import java.util.List;

import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.ProductDTO;

public class OrderDTO {

	public Integer id;
	public String delivery;
	public List<ProductDTO> products;
	public String address;
	
}
