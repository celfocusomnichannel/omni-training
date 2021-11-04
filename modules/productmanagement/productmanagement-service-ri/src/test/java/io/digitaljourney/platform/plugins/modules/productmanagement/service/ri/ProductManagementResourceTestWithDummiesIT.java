package io.digitaljourney.platform.plugins.modules.productmanagement.service.ri;

import static io.digitaljourney.platform.modules.paxexam.BundleOptions.*;
import static io.digitaljourney.platform.modules.paxexam.ConfigOptions.*;
import static org.assertj.core.api.Assertions.*;
import static org.ops4j.pax.exam.CoreOptions.*;
import static org.ops4j.pax.exam.cm.ConfigurationAdminOptions.newConfiguration;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.*;
import org.osgi.framework.Constants;

import io.digitaljourney.platform.modules.paxbuilder.api.core.PaxBundles;
import io.digitaljourney.platform.modules.paxexam.base.BaseTestSupport;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.ProductManagementResource;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.*;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.ri.dummy.*;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class ProductManagementResourceTestWithDummiesIT extends BaseTestSupport {
	
	@Inject
	private ProductManagementResource productmanagementResource;

	@Override
	protected Option bundles() {
		return composite(super.bundles(), rsProviderWithJPAClient(),
				streamBundle(paxBundle()
						.set(Constants.BUNDLE_SYMBOLICNAME, "io.digitaljourney.platform.plugins.modules.productmanagement-data-dummy-ri")
						.add(CategoryDummyDAOConfig.class)
						.add(CategoryDummyDAO.class)
						.add(CategoryDummyDAOImpl.class)
						.add(ProductDummyDAOConfig.class)
						.add(ProductDummyDAO.class)
						.add(ProductDummyDAOImpl.class)
						.build(PaxBundles.withBnd())),
		    mavenBundles("io.digitaljourney.platform.plugins.modules", "productmanagement-entity", "productmanagement-data-ri", "productmanagement-service-api"),
		    testBundle("io.digitaljourney.platform.plugins.modules", "productmanagement-service-ri"));
	}

	@Override
	protected Option configurations() {
		return composite(super.configurations(),
				newConfiguration(CategoryDummyDAOConfig.CPID).asOption(),
				newConfiguration(ProductDummyDAOConfig.CPID).asOption(),
				
				rsProviderWithJPAClientConfiguration("productmanagement", newConfiguration(ProductManagementResourceConfig.CPID)));
	}

	@Test
	public void testBundle() {
//		printBundles();
//		printServices();
		assertBundleActive("io.digitaljourney.platform.plugins.modules.productmanagement-data-dummy-ri");
		assertBundleActive("io.digitaljourney.platform.plugins.modules.productmanagement-service-ri");
	}
	
	@Test
	public void testResourceInjection() {
		assertThat(productmanagementResource).isNotNull();
	}
	
	@Test
	public void testUpdateProduct() {
		SaveCategoryDTO categoryDTO = new SaveCategoryDTOBuilder().withCategoryId(1).build();
		ProductDTO productDTO = new ProductDTOBuilder().withCategory(categoryDTO).build();
		ProductDTO product = productmanagementResource.updateProduct(1, productDTO);
		assertThat(product).isNotNull();
	}
}
