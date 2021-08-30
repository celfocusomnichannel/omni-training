package io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.service.ri;

import static io.digitaljourney.platform.modules.paxexam.BundleOptions.mavenBundles;
import static io.digitaljourney.platform.modules.paxexam.BundleOptions.rsProviderWithXMLClient;
import static org.ops4j.pax.exam.cm.ConfigurationAdminOptions.newConfiguration;
import static io.digitaljourney.platform.modules.paxexam.ConfigOptions.rsProviderWithXMLClientConfiguration;
import static org.ops4j.pax.exam.CoreOptions.composite;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;

import io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.service.api.TrainingMicroserviceRSXMLResource;
import io.digitaljourney.platform.modules.paxexam.base.BaseTestSupport;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class TrainingMicroserviceRSXMLResourceIT extends BaseTestSupport {

	@SuppressWarnings("unused")
	@Inject 
	private TrainingMicroserviceRSXMLResource trainingmicroservicersxmlResource;

	@Override
	protected Option bundles() {
		return composite(super.bundles(), rsProviderWithXMLClient(),
				mavenBundles("io.digitaljourney.platform.plugins.modules.modules", 
						"trainingmicroservicersxml-entity", "trainingmicroservicersxml-data-ri", "trainingmicroservicersxml-service-api"),
				testBundle("io.digitaljourney.platform.plugins.modules.modules", "trainingmicroservicersxml-service-ri"));
	}

	@Override
	protected Option configurations() {
		return composite(super.configurations(), 
				rsProviderWithXMLClientConfiguration("trainingmicroservicersxml", newConfiguration(TrainingMicroserviceRSXMLResourceConfig.CPID)));
	}

	@Test
	public void testBundle() {
		assertBundleActive("io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml-service-ri");
	}

}
