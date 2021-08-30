package io.digitaljourney.platform.plugins.modules.modules.productmanagement.service.ri;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import io.digitaljourney.platform.plugins.modules.modules.productmanagement.entity.Sample;
import io.digitaljourney.platform.plugins.modules.modules.productmanagement.service.api.dto.SampleDTO;

@Mapper
public interface ProductManagementResourceMapper {
	public static final ProductManagementResourceMapper INSTANCE = Mappers.getMapper(ProductManagementResourceMapper.class);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "title", target = "title")
	SampleDTO toSample(Sample sample);
	
	@InheritInverseConfiguration
	Sample toSample(SampleDTO sample);
	
	List<SampleDTO> toSampleList(List<Sample> samples);
}
