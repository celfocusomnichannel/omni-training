package io.digitaljourney.platform.plugins.apps.appkar.mvc.controller;

import java.util.List;

import org.eclipse.gemini.blueprint.extensions.annotation.ServiceReference;
import org.osgi.service.component.annotations.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.digitaljourney.platform.plugins.apps.appkar.AppProperties;
import io.digitaljourney.platform.plugins.apps.appkar.api.AppKarResource;
import io.digitaljourney.platform.plugins.apps.appkar.common.api.facade.AppKarFacade;
import io.digitaljourney.platform.plugins.apps.appkar.model.FlagResponseDTO;
import io.digitaljourney.platform.plugins.apps.appkar.model.MusicProductResponseDTO;
import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CmsRsProvider;

//@formatter:off
@Controller
@RequestMapping(AppProperties.ADDRESS)
@Component(
	property = {
		"digitaljourney.service.name=AppKar"
	}
)
@CmsRsProvider(value = AppProperties.ADDRESS + "/app")
//@formatter:on
public class AppKarController extends AbstractAppController implements AppKarResource {

	@ServiceReference
	private AppKarFacade facade;

	@Override
	@GetMapping(path = "/{isoCode}") 
	public FlagResponseDTO getFlag(@PathVariable String isoCode) {
		return facade.getFlag(isoCode);
	}

	@Override
	@GetMapping(path = "/search")
	public List<MusicProductResponseDTO> getArtistMusics(@RequestParam String artistName,@RequestParam String limit) {
		return facade.getArtistMusics(artistName, limit);
	}

}
