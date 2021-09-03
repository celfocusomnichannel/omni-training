package io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.ri;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CustomRsProvider;
import io.digitaljourney.platform.modules.ws.rs.api.resource.AbstractResource;
import io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.api.FlagServiceDAO;
import io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.api.FlagMicroServiceRSXMLResource;
import io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.api.dto.FlagDTO;

// @formatter:off
@Component(
	configurationPid = FlagMicroServiceRSXMLResourceConfig.CPID, 
	configurationPolicy = ConfigurationPolicy.REQUIRE, 
	reference = {
		@Reference(
			name = FlagMicroServiceRSXMLResourceProperties.REF_CONTEXT,
			service = FlagMicroServiceRSXMLContext.class, 
			cardinality = ReferenceCardinality.MANDATORY)
	})
@Designate(ocd = FlagMicroServiceRSXMLResourceConfig.class)
@CustomRsProvider(FlagMicroServiceRSXMLResourceProperties.ADDRESS)
// @formatter:on
public class FlagMicroServiceRSXMLResourceImpl extends AbstractResource<FlagMicroServiceRSXMLContext, FlagMicroServiceRSXMLResourceConfig> implements FlagMicroServiceRSXMLResource {

	
	@Reference
	private volatile FlagServiceDAO dao;
	
	@Activate
	public void activate(ComponentContext ctx, FlagMicroServiceRSXMLResourceConfig config) {
		prepare(ctx, config);
	}
	
	@Modified
	public void modified(FlagMicroServiceRSXMLResourceConfig config) {
		prepare(config);
	}

	@Override
	@RequiresAuthentication
	public FlagDTO getFlag(String isoCode) {
		return new FlagDTO(isoCode, dao.getFlag(isoCode));
	}
	
}
