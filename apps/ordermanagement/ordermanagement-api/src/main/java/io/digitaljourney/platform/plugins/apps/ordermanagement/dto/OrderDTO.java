package io.digitaljourney.platform.plugins.apps.ordermanagement.dto;

import java.util.List;

import org.osgi.dto.DTO;

import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.ProductDTO;
import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class OrderDTO extends DTO {

	public Integer id;
	public String delivery;
	public List<ProductDTO> products;
	public String address;
	
}
