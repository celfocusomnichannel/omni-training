package io.digitaljourney.platform.plugins.modules.productmanagement.service.ri.dummy;

import java.util.List;

import io.digitaljourney.platform.plugins.modules.productmanagement.data.api.*;
import io.digitaljourney.platform.plugins.modules.productmanagement.entity.*;

public interface ProductDummyDAO extends ProductDAO {
	
	default Product createProduct(Product product) { return null; }
  
	default Product updateProduct(Integer id, Product product) { return null; }

	default Product getProduct(Integer id) { return null; }

	default Product deleteProduct(Integer id) { return null; }

	default List<Product> getProducts() { return null; }
  
}
