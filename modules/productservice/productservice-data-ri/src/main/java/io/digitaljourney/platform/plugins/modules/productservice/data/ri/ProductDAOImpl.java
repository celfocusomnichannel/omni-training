package io.digitaljourney.platform.plugins.modules.productservice.data.ri;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.HttpMethod;

import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.ws.api.dao.WSData;
import io.digitaljourney.platform.modules.ws.rs.api.RSProperties;
import io.digitaljourney.platform.plugins.modules.productservice.data.api.ProductDAO;
import io.digitaljourney.platform.plugins.modules.productservice.entity.BookProduct;
import io.digitaljourney.platform.plugins.modules.productservice.entity.MusicProduct;

//@formatter:off
@Component(
	configurationPid = ProductDAOConfig.CPID,
	configurationPolicy = ConfigurationPolicy.REQUIRE,
	reference = {
		@Reference(
			name = RSProperties.REF_CONTEXT,
			service = WSContext.class,
			cardinality = ReferenceCardinality.MANDATORY) })
@Designate(ocd = ProductDAOConfig.class)
//@formatter:on
public final class ProductDAOImpl extends AbstractProductDAO<ProductDAOConfig> implements ProductDAO {

	public static final String TERM_KEY = "term";
	public static final String ATRIBUTTE_KEY = "attribute";
	public static final String ATRIBUTTE_VALUE = "artistTerm";
	public static final String MEDIA_KEY = "media";
	public static final String MEDIA_VALUE = "music";
	public static final String LIMIT_KEY = "limit";

	public static final String ARTIST_NAME_KEY = "artistName";
	public static final String COLLECTION_NAME_KEY = "collectionName";
	public static final String KIND_KEY = "kind";
	public static final String TRACK_NAME_KEY = "trackName";
	public static final String TRACK_VIEW_KEY = "trackViewUrl";
	public static final String ARTWORK_KEY = "artworkUrl100";
	public static final String COUNTRY_KEY = "country";
	public static final String GENRE_KEY = "primaryGenreName";
	
	public static final String EBOOK_MEDIA_VALUE = "ebook";

	@Activate
	public void activate(ComponentContext ctx, ProductDAOConfig config) {
		prepare(ctx, config);
	}

	@Modified
	public void modified(ProductDAOConfig config) {
		prepare(config);
	}

	@Override
	public List<MusicProduct> getArtistMusics(String artistName, String limit) {
		try {
			WSData<String> rsp = invoke((WebClient client) -> {
				String response = prepare(client).path(getConfig().address()).query(TERM_KEY, artistName)
						.query(ATRIBUTTE_KEY, ATRIBUTTE_VALUE).query(MEDIA_KEY, MEDIA_VALUE).query(LIMIT_KEY, limit)
						.invoke(HttpMethod.GET, null, String.class);
				return WSData.of(response).build();
			});

			if (rsp.success()) {
				return createMusicProductsArray(rsp);
			}
			return null;
		} catch (Throwable e) {
			getLogger().error("Failed to get artist musics", e);
			return null;
		}
	}

	/**
	 * @param rsp - request response
	 * @return an array of music products
	 */
	private List<MusicProduct> createMusicProductsArray(WSData<String> rsp) {
		List<MusicProduct> res = new ArrayList<>();
		JSONObject responseJson = new JSONObject(rsp.data());
		JSONArray responseResultsArray = responseJson.getJSONArray("results");
		for (int i = 0; i < responseResultsArray.length(); i++) {
			JSONObject resultsElement = responseResultsArray.getJSONObject(i);

			MusicProduct musicProduct = new MusicProduct();
			musicProduct.setArtistName(resultsElement.optString(ARTIST_NAME_KEY));
			musicProduct.setCollectionName(resultsElement.optString(COLLECTION_NAME_KEY));
			musicProduct.setKind(resultsElement.optString(KIND_KEY));
			musicProduct.setTrackName(resultsElement.optString(TRACK_NAME_KEY));
			musicProduct.setTrackViewUrl(resultsElement.optString(TRACK_VIEW_KEY));
			musicProduct.setArtworkUrl100(resultsElement.optString(ARTWORK_KEY));
			musicProduct.setCountry(resultsElement.optString(COUNTRY_KEY));
			musicProduct.setPrimaryGenreName(resultsElement.optString(GENRE_KEY));
			res.add(musicProduct);
		}
		return res;
	}

	@Override
	public List<BookProduct> getWriterBooks(String writerName, String limit) {
		try {
			WSData<String> rsp = invoke((WebClient client) -> {
				String response = prepare(client).path(getConfig().address())
						.query(TERM_KEY, writerName)
						.query(MEDIA_KEY, EBOOK_MEDIA_VALUE)
						.query(LIMIT_KEY, limit)
						.invoke(HttpMethod.GET, null, String.class);
				return WSData.of(response).build();
			});

			if (rsp.success()) {
				return createBookProductsArray(rsp);
			}
			return null;
		} catch (Throwable e) {
			getLogger().error("Failed to get writer books", e);
			return null;
		}
	}
	
	public static final String GENRES_KEY = "genres";
	public static final String DESCRIPTION_KEY = "genres";
	public static final String RELEASE_DATE_KEY = "releaseDate";
	public static final String PRICE_KEY = "price";
	
	private List<BookProduct> createBookProductsArray(WSData<String> rsp) {
		List<BookProduct> res = new ArrayList<>();
		JSONObject responseJson = new JSONObject(rsp.data());
		JSONArray responseResultsArray = responseJson.getJSONArray("results");
		for (int i = 0; i < responseResultsArray.length(); i++) {
			JSONObject resultsElement = responseResultsArray.getJSONObject(i);

			BookProduct bookProduct = new BookProduct();
			bookProduct.setArtistName(resultsElement.optString(ARTIST_NAME_KEY));
			bookProduct.setPrice(resultsElement.optString(PRICE_KEY));
			bookProduct.setReleaseDate(resultsElement.optString(RELEASE_DATE_KEY));
			bookProduct.setTrackName(resultsElement.optString(TRACK_NAME_KEY));
			bookProduct.setTrackViewUrl(resultsElement.optString(TRACK_VIEW_KEY));
			bookProduct.setArtworkUrl100(resultsElement.optString(ARTWORK_KEY));
			bookProduct.setDescription(resultsElement.optString(DESCRIPTION_KEY));
			bookProduct.setArtistName(resultsElement.optString(ARTIST_NAME_KEY));
			res.add(bookProduct);
		}
		return res;
	}
}
