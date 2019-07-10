package com.it.demosso.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ServerEndpoint(value = "/flowSocket")
@Component
@Slf4j
public class FlowSocket {
    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static CopyOnWriteArraySet<FlowSocket> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    private static Pattern pattern = Pattern.compile("#\\{deployInstanceId:([0-9]*)\\}");

    private Integer deployInstanceId;


    public FlowSocket() {
    }

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        //加入set中
        webSocketSet.add(this);
        //在线数加1
        addOnlineCount();
        log.info("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从set中删除
        webSocketSet.remove(this);
        //在线数减1
        subOnlineCount();
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) {
        Matcher mat = pattern.matcher(message);
        if (mat.find()) {
            deployInstanceId = Integer.valueOf(mat.group(1));
        } else {
            sendLightRecord(message);
        }

    }

    /**
     * 发送亮灯历史数据
     */
    public void sendLightRecord(String message) {
//        for (int i = 0; i < 20; i++) {
            for (FlowSocket item : webSocketSet) {
                item.sendMessage(message);
            }
//        }

    }

    /**
     * 发生错误时调用
     *
     * @param session session
     * @param e       e
     */
    @OnError
    public void onError(Session session, Throwable e) {
        log.error("websocket错误", e);
    }

    /**
     * 发送消息
     *
     * @param message message
     * @throws IOException
     */
    public void sendMessage(String message) {
//        System.out.println("sendMessage：" + message);
        synchronized (session) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("发送websocket出现错误:deployInstanceId:" + deployInstanceId, e);
        }
        }
    }

    /**
     * 群发消息
     *
     * @param message message
     */
    public static void massSendMessage(String message) {

        for (FlowSocket item : webSocketSet) {
            item.sendMessage(message);
        }
    }

    /**
     * 根据deployInstanceId群发消息
     *
     * @param deployInstanceId deployInstanceId
     * @param message          message
     */
    public static void sendMessageById(Integer deployInstanceId, String message) {
        for (FlowSocket item : webSocketSet) {
            if (item.deployInstanceId.equals(deployInstanceId)) {
                item.sendMessage(message);
            }
        }

    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        FlowSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        FlowSocket.onlineCount--;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FlowSocket that = (FlowSocket) o;
        return Objects.equals(session, that.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session);
    }
}