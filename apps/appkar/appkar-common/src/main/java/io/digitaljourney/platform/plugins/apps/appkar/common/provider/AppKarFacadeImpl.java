package io.digitaljourney.platform.plugins.apps.appkar.common.provider;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.security.api.AbstractSecurityComponent;
import io.digitaljourney.platform.plugins.apps.appkar.agent.AppKarCoreAgent;
import io.digitaljourney.platform.plugins.apps.appkar.common.api.facade.AppKarFacade;
import io.digitaljourney.platform.plugins.apps.appkar.model.FlagResponseDTO;
import io.digitaljourney.platform.plugins.apps.appkar.model.MusicProductResponseDTO;

//@formatter:off
@Component(
        service = { AppKarFacade.class },
        configurationPid = AppKarFacadeConfig.CPID,
        configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = AppKarFacadeConfig.class)
//@formatter:on
public class AppKarFacadeImpl extends AbstractSecurityComponent<AppKarFacadeContext, AppKarFacadeConfig> implements AppKarFacade {

    @Reference(cardinality = ReferenceCardinality.OPTIONAL, policyOption = ReferencePolicyOption.GREEDY, policy = ReferencePolicy.DYNAMIC)
    private volatile AppKarCoreAgent coreAgent;

    private AppKarCoreAgent getCoreAgent() {
        if (coreAgent == null) {
            coreAgent = getService(AppKarCoreAgent.class);
        }
        return coreAgent;
    }

	@Override
	public FlagResponseDTO getFlag(String alphaCode) {
		return getCoreAgent().getFlag(alphaCode);
	}

	@Override
	public List<MusicProductResponseDTO> getArtistMusics(String artistName, String limit) {
		List<MusicProductResponseDTO> music = getCoreAgent().getArtistMusics(artistName, limit);
		return music;
	}
}
