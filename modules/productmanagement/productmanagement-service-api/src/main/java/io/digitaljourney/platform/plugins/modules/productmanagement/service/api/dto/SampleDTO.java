package io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;
import org.osgi.dto.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.karneim.pojobuilder.GeneratePojoBuilder;

@XmlRootElement(name = "Sample")
@GeneratePojoBuilder
@ApiModel("Sample")
public class SampleDTO extends DTO {
  @ApiModelProperty(hidden = true)
  public Integer id;
  @NotEmpty
  @ApiModelProperty(position = 1, required = true, value = "the title")
  public String title;
}

