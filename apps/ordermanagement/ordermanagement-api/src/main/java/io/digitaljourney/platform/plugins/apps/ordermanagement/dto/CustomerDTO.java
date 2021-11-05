package io.digitaljourney.platform.plugins.apps.ordermanagement.dto;

import org.osgi.dto.DTO;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class CustomerDTO extends DTO {

	public String name;
	public String address;
}
