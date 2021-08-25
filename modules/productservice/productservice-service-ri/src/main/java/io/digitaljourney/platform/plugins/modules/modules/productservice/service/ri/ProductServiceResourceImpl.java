package io.digitaljourney.platform.plugins.modules.modules.productservice.service.ri;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.plugins.modules.modules.productservice.service.api.ProductServiceResource;
import io.digitaljourney.platform.modules.ws.rs.api.resource.AbstractResource;
import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CustomRsProvider;

// @formatter:off
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
@CustomRsProvider(ProductServiceResourceProperties.ADDRESS)
// @formatter:on
public class ProductServiceResourceImpl extends AbstractResource<ProductServiceContext, ProductServiceResourceConfig> implements ProductServiceResource {

	/**
	 * Method called whenever the component is activated.
	 * 
	 * @param ctx    Component context
	 * @param config Component configuration
	 */
	@Activate
	public void activate(ComponentContext ctx, ProductServiceResourceConfig config) {
		prepare(ctx, config);
	}
	
	/**
	 * Method called whenever the component configuration is modified.
	 * 
	 * @param config Component configuration
	 */
	@Modified
	public void modified(ProductServiceResourceConfig config) {
		prepare(config);
	}
	
	@Override
	@RequiresPermissions(ProductServiceResourceProperties.PERMISSION_READ)
	public String index() {
		return "ProductService is running.";
	}


}
