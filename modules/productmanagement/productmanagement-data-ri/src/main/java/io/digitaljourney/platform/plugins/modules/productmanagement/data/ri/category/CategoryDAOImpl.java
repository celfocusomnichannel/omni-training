package io.digitaljourney.platform.plugins.modules.productmanagement.data.ri.category;

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
import io.digitaljourney.platform.plugins.modules.productmanagement.data.api.CategoryDAO;
import io.digitaljourney.platform.plugins.modules.productmanagement.data.ri.RDBContext;
import io.digitaljourney.platform.plugins.modules.productmanagement.entity.Category;

// @formatter:off
@Component(
	configurationPid = CategoryDAOConfig.CPID,
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
@Designate(ocd = CategoryDAOConfig.class)
// @formatter:on
public final class CategoryDAOImpl extends AbstractJPADAO<CategoryDAOConfig> implements CategoryDAO {
	@Activate
	public void activate(ComponentContext ctx, CategoryDAOConfig config) {
		prepare(ctx, config);
	}

	@Modified
	public void modified(CategoryDAOConfig config) {
		prepare(config);
	}

	@Override
	public Category createCategory(Category category) {
		info("Entered createCategory");
		PAsserts.notNull(category, ATTRIBUTE_IS_NULL_MSG);
		return save(category);
	}
	
	@Override
	public Category getCategory(Integer id) {
		info("Entered getCategory");
		PAsserts.notNull(id, ATTRIBUTE_IS_NULL_MSG);
		return findOne(Category.class, id);
	}

	@Override
	public Category updateCategory(Integer id, Category category) {
		info("Entered updateCategory");
		PAsserts.notNull(category, ATTRIBUTE_IS_NULL_MSG);
		category.setCategoryId(id.intValue());
		return merge(category);
	}

	@Override
	public Category deleteCategory(Integer id) {
		info("Entered deleteCategory");
		PAsserts.notNull(id, ATTRIBUTE_IS_NULL_MSG);
		Category category = findOne(Category.class, id);
		deleteOne(Category.class, id);
		return category;
	}
	
}
