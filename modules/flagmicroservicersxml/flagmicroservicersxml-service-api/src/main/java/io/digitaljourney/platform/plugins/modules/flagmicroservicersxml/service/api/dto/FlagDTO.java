package io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.api.dto;

import java.io.Serializable;
import org.osgi.dto.DTO;
import io.swagger.annotations.ApiModelProperty;

public class FlagDTO extends DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "the alphaCode")
	public String alphaCode;
	
	public String urlFlag;
	
	public FlagDTO(String alphaCode, String urlFlag) {
		    this.alphaCode = alphaCode;
		    this.urlFlag = urlFlag;
	}
}
