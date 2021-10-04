package io.digitaljourney.platform.plugins.apps.ordermanagement.websocket;

import java.io.IOException;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.http.HttpService;

@Component(
        name = "ordermanagement-websocket",
        immediate = true,
        configurationPid = "io.digitaljourney.platform.plugins.apps.ordermanagement.websocket"
)
@WebSocket
public class KarafWebSocket {
	private static String JOURNEY_ID_TOKEN = "/";
	
    @Reference
    private HttpService httpService;	
	
	
    @Activate
    public void activate() throws Exception {
        httpService.registerServlet("/ordermanagement/v1/websocket", new KarafWebSocketServlet(), null, null);
    }

    @Deactivate
    public void deactivate() throws Exception {
        httpService.unregister("/ordermanagement/v1/websocket");
    }	
	
	@OnWebSocketConnect
	public void onOpen(Session session) {
		String journeyId = getJourneyId(session);
		if(journeyId == null) {
			session.close();
		}
		
		KarafWebSocketServlet.registerConnection(session, journeyId);
	}

	@OnWebSocketClose
	public void onClose(Session session, int statusCode, String reason) {
		String journeyId = getJourneyId(session);
		if(journeyId != null) {
			KarafWebSocketServlet.unregisterConnection(session, journeyId);
		}
	}

	@OnWebSocketMessage
	public void onText(Session session, String message) throws IOException {
		String journeyId = getJourneyId(session);
		if(journeyId != null) {
			KarafWebSocketServlet.onMessage(message, journeyId);
		}
	}
	
	private String getJourneyId(Session session) {
		String path = session.getUpgradeRequest().getRequestURI().getPath();
		String[] parts = path.split(JOURNEY_ID_TOKEN);
		return parts == null ? null : parts[1];
	}
}

