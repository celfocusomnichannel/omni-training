package io.digitaljourney.platform.plugins.apps.ordermanagement.dto;

import org.osgi.dto.DTO;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class CustomerInfoDTO extends DTO {
	
	public CustomerDTO customer;
	public String deliveryOption;

}
