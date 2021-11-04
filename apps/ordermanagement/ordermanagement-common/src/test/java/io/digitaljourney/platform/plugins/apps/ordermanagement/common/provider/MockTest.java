package io.digitaljourney.platform.plugins.apps.ordermanagement.common.provider;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.mockito.Mockito;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import io.digitaljourney.platform.modules.security.api.AuthenticationUtils;
import io.digitaljourney.platform.modules.security.api.interceptor.AspectjAnnotationsAuthorizingMethodInterceptor;
import io.digitaljourney.platform.modules.security.api.token.TokenService;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.session.JourneySession;

@PrepareForTest({JourneySession.class, FrameworkUtil.class, SecurityUtils.class, AuthenticationUtils.class})
public class MockTest {
	
	Subject subject;

	@SuppressWarnings("unchecked")
	public void before() throws Exception {
		
		PowerMockito.mockStatic(JourneySession.class);
		PowerMockito.mockStatic(FrameworkUtil.class);
		PowerMockito.mockStatic(SecurityUtils.class);
		PowerMockito.mockStatic(AuthenticationUtils.class);
		
		Bundle bundle = Mockito.mock(Bundle.class, Mockito.RETURNS_DEEP_STUBS);
		BundleContext bundleContext = Mockito.mock(BundleContext.class, Mockito.RETURNS_DEEP_STUBS);
		ServiceReference<TokenService> refTokenService = (ServiceReference<TokenService>) Mockito.mock(ServiceReference.class, Mockito.RETURNS_DEEP_STUBS);
		ServiceReference<WebSecurityManager> refWebSecurityManager = (ServiceReference<WebSecurityManager>) Mockito.mock(ServiceReference.class, Mockito.RETURNS_DEEP_STUBS);
		Mockito.when(bundleContext.getServiceReference(TokenService.class)).thenReturn(refTokenService);
		Mockito.when(bundleContext.getServiceReference(WebSecurityManager.class)).thenReturn(refWebSecurityManager);
		TokenService tokenService = Mockito.mock(TokenService.class, Mockito.RETURNS_DEEP_STUBS);
		WebSecurityManager webSecurityManager = Mockito.mock(WebSecurityManager.class, Mockito.RETURNS_DEEP_STUBS);
		Mockito.doReturn(tokenService).when(bundleContext).getService(refTokenService);
		Mockito.doReturn(webSecurityManager).when(bundleContext).getService(refWebSecurityManager);
		Mockito.when(bundle.getBundleContext()).thenReturn(bundleContext);
		PowerMockito.when(FrameworkUtil.getBundle(AspectjAnnotationsAuthorizingMethodInterceptor.class)).thenReturn(bundle);
		
		subject = Mockito.mock(Subject.class, Mockito.RETURNS_DEEP_STUBS);
		Mockito.doReturn(true).when(subject).isAuthenticated();
		Mockito.doReturn(true).when(subject).isPermitted(Mockito.anyString());
		PowerMockito.when(SecurityUtils.getSubject()).thenReturn(subject);
		
	}

}
