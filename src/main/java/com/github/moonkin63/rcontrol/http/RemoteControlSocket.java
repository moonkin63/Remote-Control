package com.github.moonkin63.rcontrol.http;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Created by Moonkin63 on 05.04.2015.
 */
@WebSocket
public class RemoteControlSocket {
    static Robot robot;
    private Session session;

    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Connect: " + session.getRemoteAddress().getAddress());
        try {
            this.session = session;
            session.getRemote().sendString("Got your connect message");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnWebSocketMessage
    public void onText(String message) {
        System.out.println("text: " + message);
        try {
            switch (message) {
                case "space":
                    System.out.println("press space(stop or play)");
                    try {
                        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
                        GraphicsDevice screen = env.getDefaultScreenDevice();
                        robot = new Robot(screen);
                        robot.keyPress(KeyEvent.VK_SPACE);
                        robot.keyRelease(KeyEvent.VK_SPACE);
                    } catch (AWTException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "up":
                    System.out.println("press up");
                    try {
                        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
                        GraphicsDevice screen = env.getDefaultScreenDevice();
                        robot = new Robot(screen);
                        robot.keyPress(KeyEvent.VK_UP);
                        robot.keyRelease(KeyEvent.VK_UP);
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                    break;
                case "down":
                    System.out.println("press down");
                    try {
                        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
                        GraphicsDevice screen = env.getDefaultScreenDevice();
                        robot = new Robot(screen);
                        robot.keyPress(KeyEvent.VK_DOWN);
                        robot.keyRelease(KeyEvent.VK_DOWN);
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                    break;
                case "left":
                    System.out.println("press left");
                    try {
                        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
                        GraphicsDevice screen = env.getDefaultScreenDevice();
                        robot = new Robot(screen);
                        robot.keyPress(KeyEvent.VK_LEFT);
                        robot.keyRelease(KeyEvent.VK_LEFT);
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                    break;
                case "right":
                    System.out.println("press right");
                    try {
                        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
                        GraphicsDevice screen = env.getDefaultScreenDevice();
                        robot = new Robot(screen);
                        robot.keyPress(KeyEvent.VK_RIGHT);
                        robot.keyRelease(KeyEvent.VK_RIGHT);
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            this.session.getRemote().sendString(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason);
    }
}