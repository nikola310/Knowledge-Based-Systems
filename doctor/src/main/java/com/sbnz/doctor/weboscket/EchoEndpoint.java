package com.sbnz.doctor.weboscket;

import javax.naming.NamingException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/echo")
public class EchoEndpoint {

	@OnOpen
	public void onOpen(Session session) throws NamingException {
		System.out.println("opened sesseion");
	}

	@OnMessage
	public void echoTextMessage(Session session, String msg, boolean last) throws Exception {
	}

	@OnClose
	public void close(Session session) throws NamingException {
	}

	@OnError
	public void error(Session session, Throwable t) throws NamingException {
	}
}
