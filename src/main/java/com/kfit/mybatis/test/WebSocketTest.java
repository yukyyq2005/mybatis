package com.kfit.mybatis.test;

import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class WebSocketTest {

    private String deviceId;

    private Session session;

    public WebSocketTest () {
    }

    public WebSocketTest (String deviceId) {
        this.deviceId = deviceId;
    }

    /*protected*/public boolean start() {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        //ws://localhost:80/websocket
//        String uri = "ws://192.168.12.161/orderapp/recommend?role=1&group=recommend&dldm="+ deviceId;
        String uri = "ws://localhost:80/websocket/"+ deviceId;
        System.out.println("Connecting to " + uri);
        try {
            session = container.connectToServer(WebSocketTest.class, URI.create(uri));
            System.out.println("count: " + deviceId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 1; i< 5; i++) {
            WebSocketTest wSocketTest = new WebSocketTest(String.valueOf(i));
            if (!wSocketTest.start()) {
                System.out.println("测试结束！");
                break;
            }
        }
    }
}