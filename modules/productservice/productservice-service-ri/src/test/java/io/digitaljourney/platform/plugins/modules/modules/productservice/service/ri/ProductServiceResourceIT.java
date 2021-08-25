package io.digitaljourney.platform.plugins.modules.modules.productservice.service.ri;

import static io.digitaljourney.platform.modules.paxexam.BundleOptions.mavenBundles;
import static io.digitaljourney.platform.modules.paxexam.BundleOptions.rsProviderWithRSClient;
import static org.ops4j.pax.exam.cm.ConfigurationAdminOptions.newConfiguration;
import static io.digitaljourney.platform.modules.paxexam.ConfigOptions.rsProviderWithRSClientConfiguration;
import static org.assertj.core.api.Assertions.assertThat;
import static org.ops4j.pax.exam.CoreOptions.composite;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;

import io.digitaljourney.platform.plugins.modules.modules.productservice.service.api.ProductServiceResource;

import io.digitaljourney.platform.modules.paxexam.base.BaseTestSupport;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class ProductServiceResourceIT extends BaseTestSupport {
	@Inject 
	private ProductServiceResource productserviceResource;

	@Override
	protected Option bundles() {
		return composite(super.bundles(), rsProviderWithRSClient(),
				mavenBundles("io.digitaljourney.platform.plugins.modules.modules", 
						"productservice-entity", "productservice-data-ri", "productservice-service-api"),
				testBundle("io.digitaljourney.platform.plugins.modules.modules", "productservice-service-ri"));
	}

	@Override
	protected Option configurations() {
		return composite(super.configurations(),
                rsProviderWithRSClientConfiguration("productservice", newConfiguration(ProductServiceResourceConfig.CPID)));
	}

	@Test
	public void testBundle() {
		assertBundleActive("io.digitaljourney.platform.plugins.modules.modules.productservice-service-ri");
	}

	@Test
	public void testDao() {
		assertThat(productserviceResource).isNotNull();
	}

}
