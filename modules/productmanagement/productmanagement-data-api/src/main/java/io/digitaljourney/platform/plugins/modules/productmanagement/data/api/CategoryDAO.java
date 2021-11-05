package io.digitaljourney.platform.plugins.modules.productmanagement.data.api;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

import io.digitaljourney.platform.plugins.modules.productmanagement.entity.Category;

@ProviderType
public interface CategoryDAO {

  Category createCategory(Category category);
  
  Category updateCategory(Integer id, Category category);

  Category getCategory(Integer id);

  Category deleteCategory(Integer id);
  
  List<Category> getCategories();
  
}
