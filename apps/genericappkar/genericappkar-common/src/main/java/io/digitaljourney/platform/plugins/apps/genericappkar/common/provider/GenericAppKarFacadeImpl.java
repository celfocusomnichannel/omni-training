package io.digitaljourney.platform.plugins.apps.genericappkar.common.provider;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.security.api.AbstractSecurityComponent;
import io.digitaljourney.platform.plugins.apps.genericappkar.AppProperties;
import io.digitaljourney.platform.plugins.apps.genericappkar.agent.GenericAppKarCoreAgent;
import io.digitaljourney.platform.plugins.apps.genericappkar.common.api.facade.GenericAppKarFacade;
import io.digitaljourney.platform.plugins.apps.genericappkar.dto.BookProductAppDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.dto.MusicProductAppDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.dto.TemperatureAppDTO;

//@formatter:off
@Component(
        service = { GenericAppKarFacade.class },
        configurationPid = GenericAppKarFacadeConfig.CPID,
        configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = GenericAppKarFacadeConfig.class)
//@formatter:on
public class GenericAppKarFacadeImpl extends AbstractSecurityComponent<GenericAppKarFacadeContext, GenericAppKarFacadeConfig> implements GenericAppKarFacade {

    @Reference(cardinality = ReferenceCardinality.OPTIONAL, policyOption = ReferencePolicyOption.GREEDY, policy = ReferencePolicy.DYNAMIC)
    private volatile GenericAppKarCoreAgent coreAgent;

    private GenericAppKarCoreAgent getCoreAgent() {
        if (coreAgent == null) {
            coreAgent = getService(GenericAppKarCoreAgent.class);
        }
        return coreAgent;
    }

	@Override
	@RequiresAuthentication
	@RequiresPermissions(AppProperties.PERMISSION_READ)
	public TemperatureAppDTO getTemperatureCelsius(Double tempCelsius) {
		return getCoreAgent().getTemperatureCelsius(tempCelsius);
	}

	@Override
	@RequiresAuthentication
	@RequiresPermissions(AppProperties.PERMISSION_READ)	
	public TemperatureAppDTO getTemperatureFahrenheit(Double tempFahrenheit) {
		return getCoreAgent().getTemperatureFahrenheit(tempFahrenheit);
	}

	@Override
	@RequiresAuthentication
	@RequiresPermissions(AppProperties.PERMISSION_READ)	
	public List<MusicProductAppDTO> getArtistMusics(String artistName, String limit) {
		return getCoreAgent().getArtistMusics(artistName, limit);
	}

	@Override
	@RequiresAuthentication
	@RequiresPermissions(AppProperties.PERMISSION_READ)	
	public List<BookProductAppDTO> getWriterBooks(String writer, String limit) {
		return getCoreAgent().getWriterBooks(writer, limit);
	}
}
