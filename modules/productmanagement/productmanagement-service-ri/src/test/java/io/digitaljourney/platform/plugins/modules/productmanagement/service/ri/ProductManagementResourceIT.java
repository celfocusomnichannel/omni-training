package io.digitaljourney.platform.plugins.modules.productmanagement.service.ri;

import static io.digitaljourney.platform.modules.paxexam.BundleOptions.mavenBundles;
import static io.digitaljourney.platform.modules.paxexam.BundleOptions.rsProviderWithJPAClient;

import static org.ops4j.pax.exam.cm.ConfigurationAdminOptions.newConfiguration;
import static io.digitaljourney.platform.modules.paxexam.ConfigOptions.newConsumerConfiguration;
import static io.digitaljourney.platform.modules.paxexam.ConfigOptions.rsProviderWithJPAClientConfiguration;
import static org.assertj.core.api.Assertions.assertThat;
import static org.ops4j.pax.exam.CoreOptions.composite;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;

import io.digitaljourney.platform.modules.paxexam.base.BaseTestSupport;
import io.digitaljourney.platform.plugins.modules.productmanagement.data.ri.rdb.sample.SampleDAOConfig;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.ProductManagementResource;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.SampleDTO;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.SampleDTOBuilder;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class ProductManagementResourceIT extends BaseTestSupport{
	private static final String TITLE_1 = "Title 1";

	private static final String TITLE_2 = "Title 2";

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
		return composite(super.configurations(), rsProviderWithJPAClientConfiguration("productmanagement", newConfiguration(ProductManagementResourceConfig.CPID)),
				newConsumerConfiguration(SampleDAOConfig.CPID, "productmanagement").asOption());
	}

	@Test
	public void testBundle() {
		assertBundleActive("io.digitaljourney.platform.plugins.modules.productmanagement-service-ri");
	}

	private SampleDTO buildSample1() {
		return new SampleDTOBuilder().withTitle(TITLE_1).build();
	}

	private SampleDTO buildSample2() {
		return new SampleDTOBuilder().withTitle(TITLE_2).build();
	}

	@Test
	public void addSample1() {
		SampleDTO sample1 = buildSample1();
		sample1 = productmanagementResource.addSample(sample1);

		assertThat(sample1).isNotNull();
		assertThat(sample1.id).isNotNull();
	}

	@Test
	public void getSample1() {
		SampleDTO sample1 = buildSample1();
		sample1 = productmanagementResource.addSample(sample1);
		assertThat(sample1).isNotNull();

		sample1 = productmanagementResource.getSample(sample1.id);
		assertThat(sample1.title).isEqualTo(TITLE_1);
	}

	@Test
	public void getSamples1() {
		SampleDTO sample1 = buildSample1();
		SampleDTO sample2 = buildSample2();
		sample1 = productmanagementResource.addSample(sample1);
		sample2 = productmanagementResource.addSample(sample2);

		assertThat(sample1).isNotNull();
		assertThat(sample2).isNotNull();
		
		assertThat(productmanagementResource.searchSamples(null)).size().isGreaterThan(0);
	}
}
