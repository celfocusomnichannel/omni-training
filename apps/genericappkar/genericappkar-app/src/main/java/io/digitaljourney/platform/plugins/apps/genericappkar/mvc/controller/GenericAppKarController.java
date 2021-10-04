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

import javax.cache.annotation.CacheResult;

import io.digitaljourney.platform.modules.cache.api.Cache;
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
	@CacheResult(cacheName = AppProperties.GENERICAPPKAR_CACHE)
	public @ResponseBody FlagResponseDTO getFlag(@PathVariable String isoCode) {
		return facade.getFlag(isoCode);
	}

	@Override
	@GetMapping(path = "/search")
	public @ResponseBody List<MusicProductResponseDTO> getArtistMusics(@RequestParam String artistName,@RequestParam String limit) {
		Cache c = getCtx().getCacheManager().getCache(AppProperties.GENERICAPPKAR_CACHE);
		List<MusicProductResponseDTO> dto = null;
		String[] key = {artistName, limit};
		if(c!=null) {
			dto = c.get(key, getClass().getClassLoader());
		}
		if(dto==null) {
			dto = facade.getArtistMusics(artistName, limit);
			c.put(key, dto);
		}
		return dto;
	}
}
