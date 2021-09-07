package io.digitaljourney.platform.plugins.modules.productmanagement.data.ri.product;

import java.util.List;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.transaction.control.TransactionControl;
import org.osgi.service.transaction.control.jpa.JPAEntityManagerProvider;

import io.digitaljourney.platform.modules.commons.PAsserts;
import io.digitaljourney.platform.modules.rdb.jpa.api.JPAProperties;
import io.digitaljourney.platform.modules.rdb.jpa.api.dao.AbstractJPADAO;
import io.digitaljourney.platform.plugins.modules.productmanagement.data.api.ProductDAO;
import io.digitaljourney.platform.plugins.modules.productmanagement.data.ri.RDBContext;
import io.digitaljourney.platform.plugins.modules.productmanagement.entity.Product;

// @formatter:off
@Component(
	configurationPid = ProductDAOConfig.CPID,
	configurationPolicy = ConfigurationPolicy.REQUIRE,
	reference = {
		@Reference(
			name = JPAProperties.REF_CONTEXT,
			service = RDBContext.class, 
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = JPAProperties.REF_TX_CONTROL,
			service = TransactionControl.class,
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = JPAProperties.REF_JPA_ENTITY_MANAGER,
			service = JPAEntityManagerProvider.class,
			cardinality = ReferenceCardinality.MANDATORY)
		})
@Designate(ocd = ProductDAOConfig.class)
// @formatter:on
public final class ProductDAOImpl extends AbstractJPADAO<ProductDAOConfig> implements ProductDAO {
	@Activate
	public void activate(ComponentContext ctx, ProductDAOConfig config) {
		prepare(ctx, config);
	}

	@Modified
	public void modified(ProductDAOConfig config) {
		prepare(config);
	}

	@Override
	public Product createProduct(Product product) {
		info("Entered createProduct");
		PAsserts.notNull(product, ATTRIBUTE_IS_NULL_MSG);
		return save(product);
	}
	
	@Override
	public Product getProduct(Integer id) {
		info("Entered getProduct");
		return findOne(Product.class, id);
	}
	
	@Override
	public List<Product> getProducts() {
		info("Entered getProducts");
		return findAll(Product.class);
	}

	@Override
	public Product updateProduct(Integer id, Product product) {
		info("Entered updateProduct");
		PAsserts.notNull(product, ATTRIBUTE_IS_NULL_MSG);
		product.setProductId(id.intValue());
		return merge(product);
	}

	@Override
	public Product deleteProduct(Integer id) {
		info("Entered deleteProduct");
		PAsserts.notNull(id, ATTRIBUTE_IS_NULL_MSG);
		Product product = findOne(Product.class, id);
		deleteOne(Product.class, id);
		return product;
	}

}
