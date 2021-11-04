package io.digitaljourney.platform.plugins.modules.productmanagement.service.ri.dummy;

import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.commons.AbstractConfigurableComponent;
import io.digitaljourney.platform.plugins.modules.productmanagement.data.api.*;
import io.digitaljourney.platform.plugins.modules.productmanagement.entity.*;

@Component(
		service = { Object.class, ProductDAO.class },
		immediate = true,
		configurationPid = ProductDummyDAOConfig.CPID, 
		configurationPolicy = ConfigurationPolicy.REQUIRE
	)
@Designate(ocd = ProductDummyDAOConfig.class)
public class ProductDummyDAOImpl extends AbstractConfigurableComponent<ProductDummyDAOConfig> implements ProductDummyDAO {

	@Activate
	public void activate(ProductDummyDAOConfig config) {
		prepare(config);
	}

	@Override
	public Product updateProduct(Integer id, Product product) {
		Product updProduct = new ProductBuilder().withProductId(id).build();
		return updProduct;
	}

}
