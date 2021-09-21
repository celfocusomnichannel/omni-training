package io.digitaljourney.platform.plugins.apps.genericappkar.cxf.resource;

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
import io.digitaljourney.platform.plugins.apps.genericappkar.AppProperties;
import io.digitaljourney.platform.plugins.apps.genericappkar.api.GenericAppKarResource;
import io.digitaljourney.platform.plugins.apps.genericappkar.common.api.facade.GenericAppKarFacade;
import io.digitaljourney.platform.plugins.apps.genericappkar.cxf.GenericAppKarConfiguration;
import io.digitaljourney.platform.plugins.apps.genericappkar.cxf.GenericAppKarContext;
import io.digitaljourney.platform.plugins.apps.genericappkar.model.FlagResponseDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.model.MusicProductResponseDTO;
import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CustomRsProvider;

//@formatter:off
//OSGi setup and configuration.
@Component(
	service = { GenericAppKarResource.class },
	configurationPid = GenericAppKarConfiguration.CPID,
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	reference = {
		@Reference (
			name = AppProperties.REF_CONTEXT,
			service = GenericAppKarContext.class,
			cardinality = ReferenceCardinality.MANDATORY
		)
	},
	property = {
		"digitaljourney.service.name=GenericAppKar"
	}
)
//@formatter:on
@Designate(ocd = GenericAppKarConfiguration.class)
@CustomRsProvider(AppProperties.ADDRESS)
public class GenericAppKarResourceImpl extends AbstractResource<GenericAppKarContext, GenericAppKarConfiguration>
	implements GenericAppKarResource {

	@Reference
	private volatile GenericAppKarFacade facade;

	/**
	 * Method called whenever the component is activated.
	 *
	 * @param ctx    Component context
	 * @param config Component configuration
	 */
	@Activate
	public void activate(ComponentContext ctx, GenericAppKarConfiguration config) {
		prepare(ctx, config);
	}

	/**
	 * Method called whenever the component configuration is modified.
	 *
	 * @param config Component configuration
	 */
	@Modified
	public void modified(GenericAppKarConfiguration config) {
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
