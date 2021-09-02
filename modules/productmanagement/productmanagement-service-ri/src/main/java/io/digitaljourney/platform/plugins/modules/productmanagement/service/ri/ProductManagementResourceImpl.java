package io.digitaljourney.platform.plugins.modules.productmanagement.service.ri;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.ws.rs.api.resource.AbstractResource;
import io.digitaljourney.platform.plugins.modules.productmanagement.data.api.CategoryDAO;
import io.digitaljourney.platform.plugins.modules.productmanagement.data.api.ProductDAO;
import io.digitaljourney.platform.plugins.modules.productmanagement.entity.Category;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.ProductManagementResource;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.CategoryDTO;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.ProductDTO;
import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CustomRsProvider;

// @formatter:off
@Component(
	configurationPid = ProductManagementResourceConfig.CPID, 
	configurationPolicy = ConfigurationPolicy.REQUIRE, 
	reference = {
		@Reference(
			name = ProductManagementResourceProperties.REF_CONTEXT,
			service = ProductManagementContext.class, 
			cardinality = ReferenceCardinality.MANDATORY)
	})
@Designate(ocd = ProductManagementResourceConfig.class)
@CustomRsProvider(ProductManagementResourceProperties.ADDRESS)
// @formatter:on
public class ProductManagementResourceImpl extends AbstractResource<ProductManagementContext, ProductManagementResourceConfig> implements ProductManagementResource {
	
	@Reference
	private volatile CategoryDAO categoryDAO;
	
	@Reference
	private volatile ProductDAO productDAO;
	
	@Activate
	public void activate(ComponentContext ctx, ProductManagementResourceConfig config) {
		prepare(ctx, config);
	}

	@Modified
	public void modified(ProductManagementResourceConfig config) {
		prepare(config);
	}

	@Override
	@RequiresPermissions(ProductManagementResourceProperties.PERMISSION_CREATE)
	public CategoryDTO createCategory(CategoryDTO category) {
		return ProductManagementResourceMapper.INSTANCE.toCategory(categoryDAO.createCategory(ProductManagementResourceMapper.INSTANCE.toCategory(category)));
	}
	
	@Override
	@RequiresPermissions(ProductManagementResourceProperties.PERMISSION_READ)
	public CategoryDTO getCategory(Integer id) {
		return ProductManagementResourceMapper.INSTANCE.toCategory(categoryDAO.getCategory(id));
	}

	@Override
	@RequiresPermissions(ProductManagementResourceProperties.PERMISSION_UPDATE)
	public CategoryDTO updateCategory(Integer id, CategoryDTO category) {
		return ProductManagementResourceMapper.INSTANCE.toCategory(categoryDAO.updateCategory(id, ProductManagementResourceMapper.INSTANCE.toCategory(category)));
	}

	@Override
	@RequiresPermissions(ProductManagementResourceProperties.PERMISSION_DELETE)
	public CategoryDTO deleteCategory(Integer id) {
		return ProductManagementResourceMapper.INSTANCE.toCategory(categoryDAO.deleteCategory(id));
	}

	@Override
	@RequiresPermissions(ProductManagementResourceProperties.PERMISSION_CREATE)
	public ProductDTO createProduct(ProductDTO product) {
		updateProductCategory(product);
		return ProductManagementResourceMapper.INSTANCE.toProduct(productDAO.createProduct(ProductManagementResourceMapper.INSTANCE.toProduct(product)));
	}
	
	private void updateProductCategory(ProductDTO product) {
		Category category = categoryDAO.getCategory(product.getCategory().getCategoryId());
		if(category == null)
			throw getCtx().productCategoryNotFoundException(product.getCategory().getCategoryId());
		product.setCategory(ProductManagementResourceMapper.INSTANCE.toSaveCategory(category));
	}

	@Override
	@RequiresPermissions(ProductManagementResourceProperties.PERMISSION_READ)
	public ProductDTO getProduct(Integer id) {
		return ProductManagementResourceMapper.INSTANCE.toProduct(productDAO.getProduct(id));
	}
	
	@Override
	@RequiresPermissions(ProductManagementResourceProperties.PERMISSION_UPDATE)
	public ProductDTO updateProduct(Integer id, ProductDTO product) {
		updateProductCategory(product);
		return ProductManagementResourceMapper.INSTANCE.toProduct(productDAO.updateProduct(id, ProductManagementResourceMapper.INSTANCE.toProduct(product)));
	}

	@Override
	@RequiresPermissions(ProductManagementResourceProperties.PERMISSION_DELETE)
	public ProductDTO deleteProduct(Integer id) {
		return ProductManagementResourceMapper.INSTANCE.toProduct(productDAO.deleteProduct(id));
	}

}
