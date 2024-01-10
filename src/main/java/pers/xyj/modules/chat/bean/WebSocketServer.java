package pers.xyj.modules.chat.bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;

import lombok.extern.slf4j.Slf4j;
import pers.xyj.modules.chat.domain.entity.ChatMessage;
import pers.xyj.modules.chat.domain.vo.ChatMessageVo;
import pers.xyj.modules.chat.service.ChatMessageService;
import pers.xyj.modules.chat.utils.ServerEncoder;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;
import pers.xyj.modules.common.exception.SystemException;
import pers.xyj.modules.common.utils.BeanCopyUtils;
import pers.xyj.modules.market.domain.entity.User;
import pers.xyj.modules.market.service.UserService;

@Slf4j
// @ServerEndpoint("/websocket/{username}")
@ServerEndpoint("/websocket")
@Component // 此注解千万千万不要忘记，它的主要作用就是将这个监听器纳入到Spring容器中进行管理
//@Import({ChatMessageMapper.class})
public class WebSocketServer {
    //    静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    //    //与某个客户端的连接会话，需要通过它来给客户端发送数据
//    private Session session;
    private static Map<String, Session> map = new HashMap<>();

    private static CopyOnWriteArraySet<WebSocketServer> clients = new CopyOnWriteArraySet<>();

    private Session session;


    private static ChatMessageService chatMessageService;
    private static UserService userService;

    @Autowired
    public void setChatMessageService(ChatMessageService chatMessageService) {
        WebSocketServer.chatMessageService = chatMessageService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        WebSocketServer.userService = userService;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        clients.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("有新连接加入！当前在线人数为" + getOnlineCount());
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }
    //	//连接打开时执行
    //	@OnOpen
    //	public void onOpen(@PathParam("user") String user, Session session) {
    //		currentUser = user;
    //		System.out.println("Connected ... " + session.getId());
    //	}

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        clients.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException, EncodeException {

        log.info("来自客户端的消息:" + message);


        JSONObject jsonObject = JSON.parseObject(message);
        String string = jsonObject.getString("_value");
        ChatMessage chatMessage = JSON.parseObject(string, new TypeReference<ChatMessage>() {
        });
        try {
            boolean insert = chatMessageService.save(chatMessage);
            if (!insert) {
                throw new SystemException(AppHttpCodeEnum.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("来自客户端的消息:" + chatMessage);
        ChatMessageVo chatMessageVo = BeanCopyUtils.copeBean(chatMessage, ChatMessageVo.class);
        User to = userService.getById(chatMessageVo.getToUser());
        User from = userService.getById(chatMessageVo.getFromUser());
        chatMessageVo.setFromUserName(from.getNickName());
        chatMessageVo.setToUserName(to.getNickName());
        //群发消息
        for (WebSocketServer item : clients) {
            item.session.getBasicRemote().sendText(JSON.toJSONString(chatMessageVo));
        }
    }

    /**
     * 发生错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) throws IOException {
        log.info(message);
        for (WebSocketServer item : clients) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}