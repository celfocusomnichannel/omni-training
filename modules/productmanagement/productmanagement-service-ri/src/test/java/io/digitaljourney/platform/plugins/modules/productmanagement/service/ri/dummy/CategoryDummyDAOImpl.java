package io.digitaljourney.platform.plugins.modules.productmanagement.service.ri.dummy;

import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.commons.AbstractConfigurableComponent;
import io.digitaljourney.platform.plugins.modules.productmanagement.data.api.CategoryDAO;
import io.digitaljourney.platform.plugins.modules.productmanagement.entity.*;

@Component(
		service = { Object.class, CategoryDAO.class },
		immediate = true,
		configurationPid = CategoryDummyDAOConfig.CPID, 
		configurationPolicy = ConfigurationPolicy.REQUIRE
	)
@Designate(ocd = CategoryDummyDAOConfig.class)
public class CategoryDummyDAOImpl extends AbstractConfigurableComponent<CategoryDummyDAOConfig> implements CategoryDummyDAO {

	@Activate
	public void activate(CategoryDummyDAOConfig config) {
		prepare(config);
	}

	@Override
	public Category getCategory(Integer id) {
		Category category = new CategoryBuilder().withCategoryId(id).build();
		return category;
	}

}
