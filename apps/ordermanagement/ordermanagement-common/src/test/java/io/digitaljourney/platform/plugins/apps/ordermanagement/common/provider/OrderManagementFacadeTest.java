package io.digitaljourney.platform.plugins.apps.ordermanagement.common.provider;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import io.digitaljourney.platform.plugins.apps.ordermanagement.agent.OrderManagementCoreAgent;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomJourneyDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.exception.OrderManagementException;
import io.digitaljourney.platform.plugins.apps.ordermanagement.instance.CustomJourneyInstance;
import io.digitaljourney.platform.plugins.modules.configurationmanager.service.api.dto.ConfigurationDTO;
import io.digitaljourney.platform.plugins.modules.configurationmanager.service.api.dto.ConfigurationDTOBuilder;
import io.digitaljourney.platform.plugins.modules.configurationmanager.service.api.dto.ConfigurationSearchResultDTO;
import io.digitaljourney.platform.plugins.modules.configurationmanager.service.api.dto.ConfigurationSearchResultDTOBuilder;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.JourneyProcess;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.session.JourneySession;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.ProductDTO;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.ProductDTOBuilder;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.SaveCategoryDTO;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.SaveCategoryDTOBuilder;

@RunWith(PowerMockRunner.class)
@PrepareForTest({OrderManagementFacadeImpl.class})
public class OrderManagementFacadeTest extends MockTest {

	private OrderManagementFacadeImpl facadeImpl;
	private OrderManagementCoreAgent agent;
	
	@Before
	public void before() throws Exception {
		super.before();
		Mockito.doReturn(false).when(subject).isAuthenticated();
		OrderManagementFacadeImpl facade = new OrderManagementFacadeImpl();
			
		agent = Mockito.mock(OrderManagementCoreAgent.class, Mockito.RETURNS_DEEP_STUBS);
		Field f = facade.getClass().getDeclaredField("coreAgent");
		f.setAccessible(true);
		f.set(facade, agent);
		
		facadeImpl = PowerMockito.spy(facade);
	}
	
	@Test(expected = OrderManagementException.class)
	public void getCategoryFromConfigurationResultIsEmptyTest() throws Exception {
		
		Collection<ConfigurationDTO> listCnofigurationDTO = new ArrayList<>();
		ConfigurationSearchResultDTO configurationSearchResultDTO = new ConfigurationSearchResultDTOBuilder().withResult(listCnofigurationDTO).build();
		Mockito.when(agent.getCategoryFromConfiguration()).thenReturn(configurationSearchResultDTO);
		
		OrderManagementFacadeContext ctx = Mockito.spy(new OrderManagementFacadeContext());
		Mockito.doReturn("").when(ctx).getMessage(Mockito.anyString(),Mockito.any());
		PowerMockito.doReturn(ctx).when(facadeImpl, "getCtx");
		
		List<ProductDTO> list = new ArrayList<>();
		Mockito.when(agent.getProducts()).thenReturn(list);
		
		facadeImpl.getProductList();
	}
	
	@Test
	public void getProductListTest() {
		
		Collection<ConfigurationDTO> listCnofigurationDTO = new ArrayList<>();
		ConfigurationDTO configurationDTO = new ConfigurationDTOBuilder().withValue("1").build();
		listCnofigurationDTO.add(configurationDTO);
		ConfigurationSearchResultDTO configurationSearchResultDTO = new ConfigurationSearchResultDTOBuilder().withResult(listCnofigurationDTO).build();
		Mockito.when(agent.getCategoryFromConfiguration()).thenReturn(configurationSearchResultDTO);
		
		List<ProductDTO> list = new ArrayList<>();
		SaveCategoryDTO saveCategoryDTO = new SaveCategoryDTOBuilder().withCategoryId(1).build();
		ProductDTO productDTO = new ProductDTOBuilder().withCategory(saveCategoryDTO).build();
		list.add(productDTO);
		Mockito.when(agent.getProducts()).thenReturn(list);
		
		List<ProductDTO> productList = facadeImpl.getProductList();
		assertThat(productList).isNotEmpty();
	}
	
	@Test
	public void getProductListShouldReturnEmptylistWhenCategoruDoesntMatchTest() {
		
		Collection<ConfigurationDTO> listCnofigurationDTO = new ArrayList<>();
		ConfigurationDTO configurationDTO = new ConfigurationDTOBuilder().withValue("1").build();
		listCnofigurationDTO.add(configurationDTO);
		ConfigurationSearchResultDTO configurationSearchResultDTO = new ConfigurationSearchResultDTOBuilder().withResult(listCnofigurationDTO).build();
		Mockito.when(agent.getCategoryFromConfiguration()).thenReturn(configurationSearchResultDTO);
		
		List<ProductDTO> list = new ArrayList<>();
		SaveCategoryDTO saveCategoryDTO = new SaveCategoryDTOBuilder().withCategoryId(2).build();
		ProductDTO productDTO = new ProductDTOBuilder().withCategory(saveCategoryDTO).build();
		list.add(productDTO);
		Mockito.when(agent.getProducts()).thenReturn(list);
		
		List<ProductDTO> productList = facadeImpl.getProductList();
		assertThat(productList).isEmpty();
	}
	
	@Test
	public void selectProductTest() {
		
		@SuppressWarnings("unchecked")
		JourneyProcess<CustomJourneyInstance> jp = Mockito.mock(JourneyProcess.class);
		Integer productId = 1;
		
		CustomJourneyInstance instance = Mockito.mock(CustomJourneyInstance.class);
		instance.products = new ArrayList<ProductDTO>();
		PowerMockito.when(JourneySession.getInstance(jp)).thenReturn(instance);
		
		ProductDTO product = new ProductDTOBuilder().build();
		Mockito.when(agent.getProduct(productId)).thenReturn(product);
		
		CustomJourneyDTO customJourneyDTO = facadeImpl.selectProduct(jp, productId);
		assertThat(customJourneyDTO).isNotNull();
		assertThat(customJourneyDTO.products).contains(product);
	}
	
	@Test(expected = OrderManagementException.class)
	public void selectProductShouldThrowExceptionWhenProductDoesntExistTest() throws Exception {
		
		@SuppressWarnings("unchecked")
		JourneyProcess<CustomJourneyInstance> jp = Mockito.mock(JourneyProcess.class);
		Integer productId = 1;
		
		CustomJourneyInstance instance = Mockito.mock(CustomJourneyInstance.class);
		instance.products = new ArrayList<ProductDTO>();
		PowerMockito.when(JourneySession.getInstance(jp)).thenReturn(instance);
		
		OrderManagementFacadeContext ctx = Mockito.spy(new OrderManagementFacadeContext());
		Mockito.doReturn("").when(ctx).getMessage(Mockito.anyString(),Mockito.any());
		PowerMockito.doReturn(ctx).when(facadeImpl, "getCtx");
		
		Mockito.when(agent.getProduct(productId)).thenReturn(null);
		
		facadeImpl.selectProduct(jp, productId);
	}

}