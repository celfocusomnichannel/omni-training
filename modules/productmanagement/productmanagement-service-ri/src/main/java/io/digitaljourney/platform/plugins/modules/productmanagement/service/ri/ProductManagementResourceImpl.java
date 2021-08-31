package io.digitaljourney.platform.plugins.modules.productmanagement.service.ri;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CustomRsProvider;
import io.digitaljourney.platform.modules.ws.rs.api.resource.AbstractResource;
import io.digitaljourney.platform.plugins.modules.productmanagement.data.api.SampleDAO;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.ProductManagementResource;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.SampleDTO;

// @formatter:off
@Component(
	configurationPid = ProductManagementResourceConfig.CPID, 
	configurationPolicy = ConfigurationPolicy.REQUIRE, 
	reference = {
		@Reference(
			name = ProductManagementResourceProperties.REF_CONTEXT,
			service = ProductManagementContext.class, 
			cardinality = ReferenceCardinality.MANDATORY)
	})
@Designate(ocd = ProductManagementResourceConfig.class)
@CustomRsProvider(ProductManagementResourceProperties.ADDRESS)
// @formatter:on
public class ProductManagementResourceImpl extends AbstractResource<ProductManagementContext, ProductManagementResourceConfig> implements ProductManagementResource {
	@Reference
	private volatile SampleDAO sampleDAO;

	@Activate
	public void activate(ComponentContext ctx, ProductManagementResourceConfig config) {
		prepare(ctx, config);
	}
	
	@Modified
	public void modified(ProductManagementResourceConfig config) {
		prepare(config);
	}
	
	@Override
	@RequiresPermissions(ProductManagementResourceProperties.PERMISSION_READ)
	public SampleDTO getSample(Integer id) {
		return ProductManagementResourceMapper.INSTANCE.toSample(sampleDAO.getSample(id));
	}

	@Override
	@RequiresPermissions(ProductManagementResourceProperties.PERMISSION_READ)
	public List<SampleDTO> searchSamples(String expression) {
		return ProductManagementResourceMapper.INSTANCE.toSampleList(sampleDAO.searchSamples(expression));
	}

	@Override
	@RequiresPermissions(ProductManagementResourceProperties.PERMISSION_CREATE)
	public SampleDTO addSample(SampleDTO sample) {
		return ProductManagementResourceMapper.INSTANCE.toSample(sampleDAO.addSample(ProductManagementResourceMapper.INSTANCE.toSample(sample)));
	}

	@Override
	@RequiresPermissions(ProductManagementResourceProperties.PERMISSION_UPDATE)
	public SampleDTO updateSample(SampleDTO sample) {
		return ProductManagementResourceMapper.INSTANCE.toSample(sampleDAO.updateSample(ProductManagementResourceMapper.INSTANCE.toSample(sample)));
	}

	@Override
	@RequiresPermissions(ProductManagementResourceProperties.PERMISSION_DELETE)
	public void deleteSample(Integer id) {
		sampleDAO.deleteSample(id);
	}
}
