package com.github.moonkin63.rcontrol.http;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Moonkin63 on 05.04.2015.
 */
@WebServlet(name = "WebSocket Servlet", urlPatterns = {"/ws"})
public class RemoteControlWebSocketServlet extends WebSocketServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST " + req.getHeaderNames());
        super.doPost(req, resp);
    }

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.getPolicy().setIdleTimeout(10000);
        webSocketServletFactory.register(RemoteControlSocket.class);
    }
}
