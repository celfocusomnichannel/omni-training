package io.digitaljourney.platform.plugins.modules.productmanagement.service.ri;

import static io.digitaljourney.platform.modules.paxexam.BundleOptions.mavenBundles;
import static io.digitaljourney.platform.modules.paxexam.BundleOptions.rsProviderWithJPAClient;
import static io.digitaljourney.platform.modules.paxexam.ConfigOptions.rsProviderWithJPAClientConfiguration;
import static org.ops4j.pax.exam.CoreOptions.composite;
import static org.ops4j.pax.exam.cm.ConfigurationAdminOptions.newConfiguration;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;

import io.digitaljourney.platform.modules.paxexam.base.BaseTestSupport;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.ProductManagementResource;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class ProductManagementResourceIT extends BaseTestSupport{
	@SuppressWarnings("unused")
	@Inject private ProductManagementResource productmanagementResource;

@Override
protected Option bundles() {
	return composite(super.bundles(), rsProviderWithJPAClient(),
			mavenBundles("io.digitaljourney.platform.plugins.modules", 
					"productmanagement-entity", "productmanagement-data-ri", "productmanagement-service-api"),
			testBundle("io.digitaljourney.platform.plugins.modules", "productmanagement-service-ri"));
}

@Override
protected Option configurations() {
	return composite(super.configurations(), rsProviderWithJPAClientConfiguration("productmanagement", newConfiguration(ProductManagementResourceConfig.CPID)));
}

@Test
public void testBundle() {
	assertBundleActive("io.digitaljourney.platform.plugins.modules.productmanagement-service-ri");
}
}
