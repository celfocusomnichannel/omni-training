package io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.service.api.dto;

import java.io.Serializable;

import org.osgi.dto.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.karneim.pojobuilder.GeneratePojoBuilder;

@ApiModel(description = "Flag DTO")
@GeneratePojoBuilder
public class FlagDTO extends DTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "the isoCode")
	public String isoCode;
	
	public String urlFlag;
	
	public FlagDTO(String isoCode, String urlFlag) {
		    this.isoCode = isoCode;
		    this.urlFlag = urlFlag;
	}
}
