package io.digitaljourney.platform.plugins.modules.productmanagement.data.api;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

import io.digitaljourney.platform.plugins.modules.productmanagement.entity.Product;

@ProviderType
public interface ProductDAO {

  Product createProduct(Product product);
  
  Product updateProduct(Integer id, Product product);

  Product getProduct(Integer id);

  Product deleteProduct(Integer id);

  List<Product> getProducts();
}
