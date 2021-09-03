package io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.ri;

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

import io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.api.FlagMicroServiceRSXMLResource;
import io.digitaljourney.platform.modules.paxexam.base.BaseTestSupport;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class FlagMicroServiceRSXMLResourceIT extends BaseTestSupport {

	@SuppressWarnings("unused")
	@Inject 
	private FlagMicroServiceRSXMLResource flagmicroservicersxmlResource;

	@Override
	protected Option bundles() {
		return composite(super.bundles(), rsProviderWithXMLClient(),
				mavenBundles("io.digitaljourney.platform.plugins.modules", 
						"flagmicroservicersxml-entity", "flagmicroservicersxml-data-ri", "flagmicroservicersxml-service-api"),
				testBundle("io.digitaljourney.platform.plugins.modules", "flagmicroservicersxml-service-ri"));
	}

	@Override
	protected Option configurations() {
		return composite(super.configurations(), 
				rsProviderWithXMLClientConfiguration("flagmicroservicersxml", newConfiguration(FlagMicroServiceRSXMLResourceConfig.CPID)));
	}

	@Test
	public void testBundle() {
		assertBundleActive("io.digitaljourney.platform.plugins.modules.flagmicroservicersxml-service-ri");
	}

}
