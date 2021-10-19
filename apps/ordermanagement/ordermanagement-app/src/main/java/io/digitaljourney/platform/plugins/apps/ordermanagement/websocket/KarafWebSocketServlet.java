package io.digitaljourney.platform.plugins.apps.ordermanagement.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
public class KarafWebSocketServlet extends WebSocketServlet {
	private static final Map<String, List<Session>> clientSessions = Collections.synchronizedMap(new HashMap<String, List<Session>>());
	public static final long WEBSOCKET_TIMEOUT = -1;

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.register(KarafWebSocket.class);
	}

	@Override
	public void init() throws javax.servlet.ServletException {
		super.init();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	public static void registerConnection(Session session, String journeyId) {
		session.setIdleTimeout(KarafWebSocketServlet.WEBSOCKET_TIMEOUT);

		synchronized (clientSessions) {
			if(!clientSessions.containsKey(journeyId)) {
				clientSessions.put(journeyId, Collections.synchronizedList(new ArrayList<>()));
			}

			List<Session> sessions = clientSessions.get(journeyId);
			sessions.add(session);
		}
	}

	public static void unregisterConnection(Session session, String journeyId) {
		synchronized (clientSessions) {
			List<Session> sessions = clientSessions.get(journeyId);
			sessions.remove(session);

			if(sessions.isEmpty()) {
				clientSessions.remove(journeyId);
			}
		}
	}

	public static void onMessage(String message, String journeyId) throws IOException {
		List<Session> sessions = clientSessions.get(journeyId);
		if(sessions != null) {
			for (Session session : sessions) {
				if(session.isOpen()) {
					sendJson(session, message);
				}
			}
		}
	}

	public static void publishMessage(Object obj, String journeyId) throws IOException {

		List<Session> sessions = clientSessions.get(journeyId);
		if(sessions != null) {
			for (Session session : sessions) {
				if(session.isOpen()) {
					sendJson(session, obj);
				}
			}
		}
	}

	private static void sendJson(Session session, Object obj) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		session.getRemote().sendString(mapper.writeValueAsString(obj));
	}
}

