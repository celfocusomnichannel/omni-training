package io.digitaljourney.platform.plugins.apps.genericappkar.common.provider;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.security.api.AbstractSecurityComponent;
import io.digitaljourney.platform.plugins.apps.genericappkar.agent.GenericAppKarCoreAgent;
import io.digitaljourney.platform.plugins.apps.genericappkar.common.api.facade.GenericAppKarFacade;
import io.digitaljourney.platform.plugins.apps.genericappkar.model.FlagResponseDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.model.MusicProductResponseDTO;

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
	public FlagResponseDTO getFlag(String alphaCode) {
		return getCoreAgent().getFlag(alphaCode);
	}

	@Override
	public List<MusicProductResponseDTO> getArtistMusics(String artistName, String limit) {
		List<MusicProductResponseDTO> music = getCoreAgent().getArtistMusics(artistName, limit);
		return music;
	}
}
