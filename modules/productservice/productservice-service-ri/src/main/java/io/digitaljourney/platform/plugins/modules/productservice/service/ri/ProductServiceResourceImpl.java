package io.digitaljourney.platform.plugins.modules.productservice.service.ri;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.ws.rs.api.resource.AbstractResource;
import io.digitaljourney.platform.plugins.modules.productservice.data.api.ProductDAO;
import io.digitaljourney.platform.plugins.modules.productservice.service.api.ProductServiceResource;
import io.digitaljourney.platform.plugins.modules.productservice.service.api.dto.MusicProductDTO;
import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CustomRsProvider;

//@formatter:off
@Component(
	configurationPid = ProductServiceResourceConfig.CPID,
	configurationPolicy = ConfigurationPolicy.REQUIRE,
	reference = {
		@Reference(
			name = ProductServiceResourceProperties.REF_CONTEXT,
			service = ProductServiceContext.class,
			cardinality = ReferenceCardinality.MANDATORY)
})
@Designate(ocd = ProductServiceResourceConfig.class)
@CustomRsProvider (ProductServiceResourceProperties.ADDRESS)
//@formatter:on
public class ProductServiceResourceImpl extends AbstractResource<ProductServiceContext, ProductServiceResourceConfig>
		implements ProductServiceResource {

	@Reference
	private volatile ProductDAO productDao;

	@Activate
	public void activate(ComponentContext ctx, ProductServiceResourceConfig config) {
		prepare(ctx, config);
	}

	@Override
	@RequiresAuthentication
	@RequiresPermissions(ProductServiceResourceProperties.PERMISSION_READ)
	public List<MusicProductDTO> getArtistMusics(String artistName, String limit) {
		return ProductServiceResourceMapper.INSTANCE.toMusicProductDTO(productDao.getArtistMusics(artistName, limit));
	}
}
