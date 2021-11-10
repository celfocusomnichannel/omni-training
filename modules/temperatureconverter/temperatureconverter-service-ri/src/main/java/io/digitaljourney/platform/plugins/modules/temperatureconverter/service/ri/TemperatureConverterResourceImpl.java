package io.digitaljourney.platform.plugins.modules.temperatureconverter.service.ri;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.plugins.modules.temperatureconverter.data.api.TemperatureConverterDAO;
import io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.TemperatureConverterResource;
import io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.dto.TemperatureDTO;
import io.digitaljourney.platform.modules.ws.rs.api.resource.AbstractResource;
import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CustomRsProvider;

// @formatter:off
@Component(
	configurationPid = TemperatureConverterResourceConfig.CPID,
	configurationPolicy = ConfigurationPolicy.REQUIRE,
	reference = {
		@Reference(
			name = TemperatureConverterResourceProperties.REF_CONTEXT,
			service = TemperatureConverterContext.class,
			cardinality = ReferenceCardinality.MANDATORY)
})
@Designate(ocd = TemperatureConverterResourceConfig.class)
@CustomRsProvider(TemperatureConverterResourceProperties.ADDRESS)
// @formatter:on
public class TemperatureConverterResourceImpl extends AbstractResource<TemperatureConverterContext, TemperatureConverterResourceConfig> implements TemperatureConverterResource {

	
	@Reference
	private TemperatureConverterDAO tempDAO;
	
	/**
	 * Method called whenever the component is activated.
	 * 
	 * @param ctx    Component context
	 * @param config Component configuration
	 */
	@Activate
	public void activate(ComponentContext ctx, TemperatureConverterResourceConfig config) {
		prepare(ctx, config);
	}
	
	/**
	 * Method called whenever the component configuration is modified.
	 * 
	 * @param config Component configuration
	 */
	@Modified
	public void modified(TemperatureConverterResourceConfig config) {
		prepare(config);
	}

	@Override
	public TemperatureDTO convertCelsius(Double temperature) {
		
		return new TemperatureDTO(tempDAO.getFahrenheitTemp(temperature), "F");
	}

}
