package io.digitaljourney.platform.plugins.modules.temperatureconverter.service.ri;

import static io.digitaljourney.platform.modules.paxexam.BundleOptions.mavenBundles;
import static io.digitaljourney.platform.modules.paxexam.BundleOptions.rsProviderWithXMLClient;
import static io.digitaljourney.platform.modules.paxexam.ConfigOptions.rsProviderWithXMLClientConfiguration;
import static org.ops4j.pax.exam.CoreOptions.composite;
import static org.ops4j.pax.exam.cm.ConfigurationAdminOptions.newConfiguration;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;

import io.digitaljourney.platform.modules.paxexam.BundleOptions;
import io.digitaljourney.platform.modules.paxexam.base.BaseTestSupport;
import io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.TemperatureConverterResource;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class TemperatureConverterResourceIT extends BaseTestSupport {

	@SuppressWarnings("unused")
	@Inject
	private TemperatureConverterResource temperatureconverterResource;

	@Override
	protected Option bundles() {
		return composite(super.bundles(), rsProviderWithXMLClient(),
				BundleOptions.mavenBundles("org.apache.neethi", "neethi"),
				BundleOptions.mavenBundles("org.apache.cxf", "cxf-rt-ws-policy", "cxf-rt-ws-addr"),
				mavenBundles("io.digitaljourney.platform.plugins.modules", 
				"temperatureconverter-entity", "temperatureconverter-data-ri", "temperatureconverter-service-api"),
				testBundle("io.digitaljourney.platform.plugins.modules", "temperatureconverter-service-ri"));
	}

	@Override
	protected Option configurations() {
		return composite(super.configurations(),
				rsProviderWithXMLClientConfiguration("temperatureconverter", newConfiguration(TemperatureConverterResourceConfig.CPID)));
	}

	@Test
	public void testBundle() {
		assertBundleActive("io.digitaljourney.platform.plugins.modules.temperatureconverter-service-ri");
	}

}
