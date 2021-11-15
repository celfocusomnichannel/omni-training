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
import io.digitaljourney.platform.plugins.apps.genericappkar.dto.BookProductAppDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.dto.MusicProductAppDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.dto.TemperatureAppDTO;
import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CmsRsProvider;

//@formatter:off
@Controller
@RequestMapping(AppProperties.ADDRESS)
@CmsRsProvider(value = AppProperties.ADDRESS)
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
	@GetMapping(path="/fahrenheit/{temperature}")
	public @ResponseBody TemperatureAppDTO convertCelsius(@PathVariable Double temperature) {
		return facade.getTemperatureFahrenheit(temperature);
	}

	@Override
	@GetMapping(path="/celsius/{temperature}")
	public @ResponseBody TemperatureAppDTO convertFahrenheit(@PathVariable Double temperature) {
		return facade.getTemperatureCelsius(temperature);
	}

	@Override
	@GetMapping(path="/searchArtists")
	public @ResponseBody List<MusicProductAppDTO> getArtistMusics(@RequestParam String artistName, @RequestParam String limit) {
		return facade.getArtistMusics(artistName, limit);
	}

	@Override
	@GetMapping(path="/searchWriters")
	public @ResponseBody List<BookProductAppDTO> getWriterBooks(@RequestParam String writerName, @RequestParam String limit) {
		return facade.getWriterBooks(writerName, limit);
	}

}
