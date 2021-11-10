package io.digitaljourney.platform.plugins.modules.temperatureconverter.data.ri;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.ws.api.dao.WSData;
import io.digitaljourney.platform.modules.ws.xml.api.XMLProperties;
import io.digitaljourney.platform.modules.ws.xml.api.dao.AbstractXMLDAO;
import io.digitaljourney.platform.plugins.modules.temperatureconverter.data.api.TemperatureConverterDAO;
import io.digitaljourney.platform.plugins.modules.temperatureconverter.data.ri.ws.tempconvert.proxy.TempConvertSoap;


@Component(
		configurationPid = TemperatureConverterDAOConfig.CPID,
		configurationPolicy = ConfigurationPolicy.REQUIRE,
		reference = {
			@Reference(
				name = XMLProperties.REF_CONTEXT,
				service = WSContext.class,
				cardinality = ReferenceCardinality.MANDATORY) })
@Designate(ocd = TemperatureConverterDAOConfig.class)
public class TemperatureConverterDAOImpl extends AbstractXMLDAO<TempConvertSoap, TemperatureConverterDAOConfig>
		implements TemperatureConverterDAO {

	public TemperatureConverterDAOImpl() {
		super(TempConvertSoap.class);
	}

	@Activate
	public void activate(ComponentContext ctx, TemperatureConverterDAOConfig config) {
		prepare(ctx, config);
	}

	@Modified
	public void modified(TemperatureConverterDAOConfig config) {
		prepare(config);
	}

	@Override
	public String getFahrenheitTemp(Double tempCelsius) {
		WSData<String> result = invoke((TempConvertSoap proxy) ->{
			String output = proxy.celsiusToFahrenheit(String.valueOf(tempCelsius));
			
			return WSData.of(output).build();
		});
		
		if(!result.success()) {
			throw resultException(result);
		}
		
		return result.data();
	}

}
