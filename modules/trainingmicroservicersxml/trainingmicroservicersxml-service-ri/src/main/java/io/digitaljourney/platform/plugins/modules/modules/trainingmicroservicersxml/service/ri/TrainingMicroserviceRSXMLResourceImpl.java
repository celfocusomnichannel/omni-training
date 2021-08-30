package io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.service.ri;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.ws.rs.api.resource.AbstractResource;
import io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.data.api.FlagServiceDAO;
import io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.service.api.TrainingMicroserviceRSXMLResource;
import io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.service.api.dto.FlagDTO;
import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CustomRsProvider;

// @formatter:off
@Component(
	configurationPid = TrainingMicroserviceRSXMLResourceConfig.CPID, 
	configurationPolicy = ConfigurationPolicy.REQUIRE, 
	reference = {
		@Reference(
			name = TrainingMicroserviceRSXMLResourceProperties.REF_CONTEXT,
			service = TrainingMicroserviceRSXMLContext.class, 
			cardinality = ReferenceCardinality.MANDATORY)
	})
@Designate(ocd = TrainingMicroserviceRSXMLResourceConfig.class)
@CustomRsProvider(TrainingMicroserviceRSXMLResourceProperties.ADDRESS)
// @formatter:on
public class TrainingMicroserviceRSXMLResourceImpl extends AbstractResource<TrainingMicroserviceRSXMLContext, TrainingMicroserviceRSXMLResourceConfig> 
  implements TrainingMicroserviceRSXMLResource {

	@Reference
	 private volatile FlagServiceDAO dao;
	
	@Activate
	public void activate(ComponentContext ctx, TrainingMicroserviceRSXMLResourceConfig config) {
		prepare(ctx, config);
	}
	
	@Modified
	public void modified(TrainingMicroserviceRSXMLResourceConfig config) {
		prepare(config);
	}

	@Override
	@RequiresAuthentication
	public FlagDTO getFlag(String isoCode) {
		return new FlagDTO(isoCode, dao.getFlag(isoCode));
	}
	
}
