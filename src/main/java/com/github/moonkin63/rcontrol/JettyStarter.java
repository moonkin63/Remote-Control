package com.github.moonkin63.rcontrol;

import com.github.moonkin63.rcontrol.http.RemoteControlWebSocketServlet;
import com.github.moonkin63.utils.FileUtils;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Moonkin63 on 05.04.2015.
 */
public class JettyStarter {
    public static void main(String[] args) {
        List<String> params = Arrays.asList(args);
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.addConnector(connector);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{"index.html"});
        if (params.contains("-d")) {
            resource_handler.setResourceBase("./web");
        } else {
            resource_handler.setResourceBase(FileUtils.getCurrentJARPath() + "web");
        }

        HandlerList handlers = new HandlerList();

        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setContextPath("/api/");
        contextHandler.addServlet(new ServletHolder(new RemoteControlWebSocketServlet()), "/*");
        handlers.setHandlers(new Handler[]{contextHandler, resource_handler});

        server.setHandler(handlers);

        try {
            server.start();
            server.join();
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }
}
