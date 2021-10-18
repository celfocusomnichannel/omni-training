package io.digitaljourney.platform.plugins.modules.productservice.service.api.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.ws.api.dao.WSData;
import io.digitaljourney.platform.modules.ws.rs.api.RSProperties;
import io.digitaljourney.platform.modules.ws.rs.api.client.AbstractWSRSClient;
import io.digitaljourney.platform.modules.ws.rs.api.dao.WSRSConfig;
import io.digitaljourney.platform.plugins.modules.productservice.service.api.ProductServiceResource;
import io.digitaljourney.platform.plugins.modules.productservice.service.api.dto.BookProductDTO;
import io.digitaljourney.platform.plugins.modules.productservice.service.api.dto.MusicProductDTO;

//@formatter:off
@Component(
	configurationPid = ProductServiceClientConfiguration.CPID,
	configurationPolicy = ConfigurationPolicy.REQUIRE,
	reference = {
		@Reference(
			name = RSProperties.REF_CONTEXT,
			service = ProductServiceClientContext.class,
			cardinality = ReferenceCardinality.MANDATORY
		)
	},
	property = {
		org.osgi.framework.Constants.SERVICE_RANKING+":Integer=10",
		"digitaljourney.http.client=Product Service"
	}
)
@Designate(ocd = ProductServiceClientConfiguration.class)
//@formatter:on
public class ProductServiceClient
		extends AbstractWSRSClient<ProductServiceClientContext, ProductServiceClientConfiguration>
		implements ProductServiceResource {

	/**
	 * Creates a new default instance of the Service Client.
	 */
	public ProductServiceClient() {
		// empty constructor
	}

	@Override
	public void loadConfigurations() {
		// create an empty configuration and go with it
		WSRSConfig config = WSRSConfig.of(getServiceConfigurations(ProductServiceClientConfiguration.CPID));
		config.setName("productservice-httpclient");
		if (config.getAddress() == null) {
			config.setAddress(getAddress());
		}
		if (config.getUserName() == null) {
			config.setUserName(getUsername());
		}
		if (config.getPassword() == null) {
			config.setPassword(getPassword());
		}

		super.prepare(config);
	}

	/**
	 * Method called whenever the component is activated.
	 *
	 * @param ctx Component context
	 */
	@Activate
	public void activate(ComponentContext ctx) {
		info("ProductService HTTP Client implementation activated  ({})", ctx);
		super.prepare(ctx);
		prepare((ProductServiceClientConfiguration) null);
	}

	/**
	 * Method called whenever the component is activated.
	 *
	 * @param ctx    Component context
	 * @param config Intent configuration
	 */
	@Activate
	public void activate(ComponentContext ctx, ProductServiceClientConfiguration config) {
		info("ProductService HTTP Client implementation activated ({}, {})", ctx, config);
		super.prepare(ctx);
		prepare(config);
	}

	/**
	 * Method called whenever the intent provider configuration changes.
	 *
	 * @param config Intent configuration
	 */
	@Modified
	public void modified(ProductServiceClientConfiguration config) {
		info("ProductService HTTP Client configuration changed");
		prepare(config);
	}

	/**
	 * Method called whenever the endpoint intent is deactivated.
	 */
	@Deactivate
	public void deactivated() {
		info("ProductService HTTP Client implementation deactivated.");
	}

	@Override
	protected String getAddress() {
		String address = System.getenv("OMNICHANNEL_PRODUCTSERVICE_ENDPOINT");
		if (address == null && getConfig() != null) {
			address = getConfig().address();
		}
		if (address == null) {
			address = super.getAddress();
		}
		return address;
	}

	@Override
	protected String getUsername() {
		String username = System.getenv("OMNICHANNEL_PRODUCTSERVICE_USERNAME");
		if (username == null && getConfig() != null) {
			username = getConfig().userName();
		}
		return username;
	}

	@Override
	protected String getPassword() {
		String password = System.getenv("OMNICHANNEL_PRODUCTSERVICE_PASSWORD");
		if (password == null && getConfig() != null) {
			password = getConfig().password();
		}
		return decryptPassword(password);
	}

	@Override
	public List<MusicProductDTO> getArtistMusics(String artistName, String limit) {
		WSData<List<MusicProductDTO>> result = super.invoke((WebClient client) -> {
			WebClient clientPath = super.client.type(MediaType.APPLICATION_JSON_TYPE).path("/search");
			query(clientPath, "term", artistName);
			query(clientPath, "limit", limit);
			
			// allow making changes to the client
			executeClientHandlers(clientPath);
			Collection<? extends MusicProductDTO> responseCall = clientPath.getCollection(MusicProductDTO.class);
			List<MusicProductDTO> responseCollection = null;
			if (responseCall != null) {
				responseCollection = new ArrayList<>(responseCall);
			}
			return WSData.of(responseCollection).build();
		});
		if (!result.success() || result.data() == null) {
			throw resultException(result);
		}
		return result.data();
	}

	@Override
	public List<BookProductDTO> getWriterBooks(String writerName, String limit) {
		WSData<List<BookProductDTO>> result = invoke((WebClient client) -> {
			WebClient clientPath = client.type(MediaType.APPLICATION_JSON_TYPE).path("");
			query(clientPath, "term", writerName);
			query(clientPath, "limit", limit);
			
			// allow making changes to the client
			executeClientHandlers(clientPath);
			Collection<? extends BookProductDTO> responseCall = clientPath.getCollection(BookProductDTO.class);
			List<BookProductDTO> responseCollection = null;
			if (responseCall != null) {
				responseCollection = new ArrayList<>(responseCall);
			}
			return WSData.of(responseCollection).build();
		});
		if (!result.success() || result.data() == null) {
			throw resultException(result);
		}
		return result.data();
	}
}
