package io.digitaljourney.platform.plugins.modules.productmanagement.service.api.client;

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
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.ProductManagementResource;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.CategoryDTO;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.ProductDTO;

//@formatter:off
@Component(
	configurationPid = ProductManagementClientConfiguration.CPID,
	configurationPolicy = ConfigurationPolicy.REQUIRE,
	reference = {
		@Reference(
			name = RSProperties.REF_CONTEXT,
			service = ProductManagementClientContext.class,
			cardinality = ReferenceCardinality.MANDATORY
		)
	},
	property = {
		org.osgi.framework.Constants.SERVICE_RANKING+":Integer=10",
		"digitaljourney.http.client=Product Service"
	}
)
@Designate(ocd = ProductManagementClientConfiguration.class)
//@formatter:on
public class ProductManagementClient extends AbstractWSRSClient<ProductManagementClientContext, ProductManagementClientConfiguration> implements ProductManagementResource{
	
	/**
	 * Creates a new default instance of the Service Client.
	 */
	public ProductManagementClient() {
		// empty constructor
	}
	
	@Override
	public void loadConfigurations() {
		// create an empty configuration and go with it
		WSRSConfig config = WSRSConfig.of(getServiceConfigurations(ProductManagementClientConfiguration.CPID));
		config.setName("productmanagement-httpclient");
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
		info("ProductManagement HTTP Client implementation activated  ({})", ctx);
		super.prepare(ctx);
		prepare((ProductManagementClientConfiguration) null);
	}
	
	/**
	 * Method called whenever the component is activated.
	 *
	 * @param ctx    Component context
	 * @param config Intent configuration
	 */
	@Activate
	public void activate(ComponentContext ctx, ProductManagementClientConfiguration config) {
		info("ProductManagement HTTP Client implementation activated ({}, {})", ctx, config);
		super.prepare(ctx);
		prepare(config);
	}
	
	/**
	 * Method called whenever the intent provider configuration changes.
	 *
	 * @param config Intent configuration
	 */
	@Modified
	public void modified(ProductManagementClientConfiguration config) {
		info("ProductService HTTP Client configuration changed");
		prepare(config);
	}
	
	/**
	 * Method called whenever the endpoint intent is deactivated.
	 */
	@Deactivate
	public void deactivated() {
		info("ProductManagement HTTP Client implementation deactivated.");
	}
	
	@Override
	protected String getAddress() {
		String address = System.getenv("OMNICHANNEL_PRODUCTMANAGEMENT_ENDPOINT");
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
		String username = System.getenv("OMNICHANNEL_PRODUCTMANAGEMENT_USERNAME");
		if (username == null && getConfig() != null) {
			username = getConfig().userName();
		}
		return username;
	}

	@Override
	protected String getPassword() {
		String password = System.getenv("OMNICHANNEL_PRODUCTMANAGEMENT_USERNAME");
		if (password == null && getConfig() != null) {
			password = getConfig().password();
		}
		return decryptPassword(password);
	}

	@Override
	public CategoryDTO createCategory(CategoryDTO category) {
		WSData<CategoryDTO> result = super.invoke((WebClient client) -> {
			WebClient clientPath = client.type(MediaType.APPLICATION_JSON).path("/category/");
			// allow making changes to the client
			executeClientHandlers(clientPath);
			CategoryDTO responseCall = clientPath.post(category, CategoryDTO.class);
			return WSData.of(responseCall).build();
		});
		if (!result.success() || result.data() == null) {
			throw resultException(result);
		}
		return result.data();
	}

	@Override
	public CategoryDTO getCategory(Integer id) {
		WSData<CategoryDTO> result = super.invoke((WebClient client) -> {
			WebClient clientPath = client.type(MediaType.APPLICATION_JSON).path("/category/{id}", id);
			// allow making changes to the client
			executeClientHandlers(clientPath);
			CategoryDTO responseCall = clientPath.get(CategoryDTO.class);
			return WSData.of(responseCall).build();
		});
		if (!result.success()) {
			throw resultException(result);
		}
		return result.data();
	}

	@Override
	public CategoryDTO updateCategory(Integer id, CategoryDTO category) {
		WSData<CategoryDTO> result = super.invoke((WebClient client) -> {
			WebClient clientPath = client.type(MediaType.APPLICATION_JSON).path("/category/{id}", id);
			// allow making changes to the client
			executeClientHandlers(clientPath);
			CategoryDTO responseCall = clientPath.put(category, CategoryDTO.class);
			return WSData.of(responseCall).build();
		});
		if (!result.success() || result.data() == null) {
			throw resultException(result);
		}
		return result.data();
	}

	@Override
	public CategoryDTO deleteCategory(Integer id) {
		WSData<CategoryDTO> result = super.invoke((WebClient client) -> {
			WebClient clientPath = client.type(MediaType.APPLICATION_JSON_TYPE).path("/category/{id}", id);
			// allow making changes to the client
			executeClientHandlers(clientPath);
			CategoryDTO response = clientPath.delete().readEntity(CategoryDTO.class);
			return WSData.of(response).build();
		});
		if (!result.success()) {
			throw resultException(result);
		}
		return result.data();
	}

	@Override
	public ProductDTO createProduct(ProductDTO product) {
		WSData<ProductDTO> result = super.invoke((WebClient client) -> {
			WebClient clientPath = client.type(MediaType.APPLICATION_JSON).path("/product/");
			// allow making changes to the client
			executeClientHandlers(clientPath);
			ProductDTO responseCall = clientPath.post(product, ProductDTO.class);
			return WSData.of(responseCall).build();
		});
		if (!result.success() || result.data() == null) {
			throw resultException(result);
		}
		return result.data();
	}

	@Override
	public ProductDTO getProduct(Integer id) {
		WSData<ProductDTO> result = super.invoke((WebClient client) -> {
			WebClient clientPath = client.type(MediaType.APPLICATION_JSON).path("/product/{id}", id);
			// allow making changes to the client
			executeClientHandlers(clientPath);
			ProductDTO responseCall = clientPath.get(ProductDTO.class);
			return WSData.of(responseCall).build();
		});
		if (!result.success()) {
			throw resultException(result);
		}
		return result.data();
	}

	@Override
	public List<ProductDTO> getProducts() {
		WSData<List<ProductDTO>> result = super.invoke((WebClient client) -> {
			WebClient clientPath = client.type(MediaType.APPLICATION_JSON).path("/product/");
			// allow making changes to the client
			executeClientHandlers(clientPath);
			Collection<? extends ProductDTO> responseCall = clientPath.getCollection(ProductDTO.class);
			List<ProductDTO> responseCollection = null;
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
	public ProductDTO updateProduct(Integer id, ProductDTO product) {
		WSData<ProductDTO> result = super.invoke((WebClient client) -> {
			WebClient clientPath = client.type(MediaType.APPLICATION_JSON).path("/product/{id}", id);
			// allow making changes to the client
			executeClientHandlers(clientPath);
			ProductDTO responseCall = clientPath.put(product, ProductDTO.class);
			return WSData.of(responseCall).build();
		});
		if (!result.success() || result.data() == null) {
			throw resultException(result);
		}
		return result.data();
	}

	@Override
	public ProductDTO deleteProduct(Integer id) {
		WSData<ProductDTO> result = super.invoke((WebClient client) -> {
			WebClient clientPath = client.type(MediaType.APPLICATION_JSON).path("/product/{id}", id);
			// allow making changes to the client
			executeClientHandlers(clientPath);
			ProductDTO response = clientPath.delete().readEntity(ProductDTO.class);
			return WSData.of(response).build();
		});
		if (!result.success()) {
			throw resultException(result);
		}
		return result.data();
	}
}
