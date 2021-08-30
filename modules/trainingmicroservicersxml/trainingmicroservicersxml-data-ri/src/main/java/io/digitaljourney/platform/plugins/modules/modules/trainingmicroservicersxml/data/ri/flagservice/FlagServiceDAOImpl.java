package io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.data.ri.flagservice;

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
import io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.data.api.FlagServiceDAO;
import io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.data.ri.WSContext;
import io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.data.ri.ws.flagservice.proxy.CountryInfoServiceSoapType;


// @formatter:off
@Component(
	configurationPid = FlagServiceDAOConfig.CPID,
	configurationPolicy = ConfigurationPolicy.REQUIRE,
	reference = {
		@Reference(
			name = XMLProperties.REF_CONTEXT,
			service = WSContext.class, 
			cardinality = ReferenceCardinality.MANDATORY) })
@Designate(ocd = FlagServiceDAOConfig.class)
// @formatter:on
public final class FlagServiceDAOImpl extends AbstractXMLDAO<CountryInfoServiceSoapType, FlagServiceDAOConfig>
    implements FlagServiceDAO {

  public FlagServiceDAOImpl() {
    super(CountryInfoServiceSoapType.class);
  }

  @Activate
  public void activate(ComponentContext ctx, FlagServiceDAOConfig config) {
    prepare(ctx, config);
  }

  @Modified
  public void modified(FlagServiceDAOConfig config) {
    prepare(config);
  }

  @Override
  public String getFlag(String isoCode) {
    WSData<String> result = invoke((CountryInfoServiceSoapType proxy) -> {
      String output = proxy.countryFlag(isoCode);
      return WSData.of(output).build();
    });
    if (!result.success()) {
      throw resultException(result);
    }
    return result.data();
  }


}
