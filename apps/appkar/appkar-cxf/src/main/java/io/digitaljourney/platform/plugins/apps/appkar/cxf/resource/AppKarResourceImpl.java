package io.digitaljourney.platform.plugins.apps.appkar.cxf.resource;

import java.util.List;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.ws.rs.api.resource.AbstractResource;
import io.digitaljourney.platform.plugins.apps.appkar.AppProperties;
import io.digitaljourney.platform.plugins.apps.appkar.api.AppKarResource;
import io.digitaljourney.platform.plugins.apps.appkar.common.api.facade.AppKarFacade;
import io.digitaljourney.platform.plugins.apps.appkar.cxf.AppKarConfiguration;
import io.digitaljourney.platform.plugins.apps.appkar.cxf.AppKarContext;
import io.digitaljourney.platform.plugins.apps.appkar.model.FlagResponseDTO;
import io.digitaljourney.platform.plugins.apps.appkar.model.MusicProductResponseDTO;
import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CustomRsProvider;

//@formatter:off
//OSGi setup and configuration.
@Component(
	service = { AppKarResource.class },
	configurationPid = AppKarConfiguration.CPID,
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	reference = {
		@Reference (
			name = AppProperties.REF_CONTEXT,
			service = AppKarContext.class,
			cardinality = ReferenceCardinality.MANDATORY
		)
	},
	property = {
		"digitaljourney.service.name=AppKar"
	}
)
//@formatter:on
@Designate(ocd = AppKarConfiguration.class)
@CustomRsProvider(AppProperties.ADDRESS)
public class AppKarResourceImpl extends AbstractResource<AppKarContext, AppKarConfiguration>
	implements AppKarResource {

	@Reference
	private volatile AppKarFacade facade;

	/**
	 * Method called whenever the component is activated.
	 *
	 * @param ctx    Component context
	 * @param config Component configuration
	 */
	@Activate
	public void activate(ComponentContext ctx, AppKarConfiguration config) {
		prepare(ctx, config);
	}

	/**
	 * Method called whenever the component configuration is modified.
	 *
	 * @param config Component configuration
	 */
	@Modified
	public void modified(AppKarConfiguration config) {
		prepare(config);
	}

	@Override
	public FlagResponseDTO getFlag(String isoCode) {
		return facade.getFlag(isoCode);
	}

	@Override
	public List<MusicProductResponseDTO> getArtistMusics(String artistName, String limit) {
		return facade.getArtistMusics(artistName, limit);
	}

}
