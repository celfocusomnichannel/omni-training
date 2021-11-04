package io.digitaljourney.platform.plugins.modules.productmanagement.service.ri.dummy;

import io.digitaljourney.platform.plugins.modules.productmanagement.data.api.CategoryDAO;
import io.digitaljourney.platform.plugins.modules.productmanagement.entity.Category;

public interface CategoryDummyDAO extends CategoryDAO {
	
  default Category createCategory(Category category) { return null; }
  
  default Category updateCategory(Integer id, Category category) { return null; }

  default Category getCategory(Integer id) { return null; }

  default Category deleteCategory(Integer id)  { return null; }

}
