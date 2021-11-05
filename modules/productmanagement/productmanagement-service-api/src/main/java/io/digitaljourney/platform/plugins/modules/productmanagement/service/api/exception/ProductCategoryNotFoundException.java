package io.digitaljourney.platform.plugins.modules.productmanagement.service.api.exception;

import io.digitaljourney.platform.modules.commons.context.Context;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.ProductManagementProperties;

public class ProductCategoryNotFoundException extends ProductManagementException {

	private static final long serialVersionUID = -3054715754432738653L;

	protected ProductCategoryNotFoundException(Context ctx, Object... args) {
		super(ProductManagementProperties.PRODUCTMANAGEMENT001, ctx, args);
	}
	
	public static ProductCategoryNotFoundException of(Context ctx, Integer categoryId) {
		return new ProductCategoryNotFoundException(ctx, categoryId.toString());
	}

}
