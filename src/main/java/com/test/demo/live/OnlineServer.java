package com.test.demo.live;


import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Vector;

@ServerEndpoint("/onlineServer")
@Component
public class OnlineServer {
    //当前一个客户端访问创建一个onlineServer实例
    //通过session服务向客户端发送消息
    private Session session;
    //建立一个静态集合
    private static Vector<OnlineServer> clients = new Vector<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        clients.add(this);
        System.out.println("新连接链接");
    }

    @OnClose
    public void onClose() {
        clients.remove(this);
        System.out.println("关闭一个新连接");
    }

    /**
     * 客户端向服务器发送消息
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        //群发广播消息
        for (OnlineServer client : clients) {
            try {
                client.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
