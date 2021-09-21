package io.digitaljourney.platform.plugins.apps.genericappkar.mvc.controller;

import java.util.List;

import org.eclipse.gemini.blueprint.extensions.annotation.ServiceReference;
import org.osgi.service.component.annotations.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.digitaljourney.platform.plugins.apps.genericappkar.AppProperties;
import io.digitaljourney.platform.plugins.apps.genericappkar.api.GenericAppKarResource;
import io.digitaljourney.platform.plugins.apps.genericappkar.common.api.facade.GenericAppKarFacade;
import io.digitaljourney.platform.plugins.apps.genericappkar.model.FlagResponseDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.model.MusicProductResponseDTO;
import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CmsRsProvider;

//@formatter:off
@Controller
@RequestMapping(AppProperties.ADDRESS + "/app")
@CmsRsProvider(value = AppProperties.ADDRESS + "/app")
@Component(
	property = {
		"digitaljourney.service.name=GenericAppKar"
	}
)
//@formatter:on
public class GenericAppKarController extends AbstractAppController implements GenericAppKarResource {

	@ServiceReference
	private GenericAppKarFacade facade;

	@Override
	@GetMapping(path = "/{isoCode}") 
	public @ResponseBody FlagResponseDTO getFlag(@PathVariable String isoCode) {
		return facade.getFlag(isoCode);
	}

	@Override
	@GetMapping(path = "/search")
	public @ResponseBody List<MusicProductResponseDTO> getArtistMusics(@RequestParam String artistName,@RequestParam String limit) {
		return facade.getArtistMusics(artistName, limit);
	}
}
