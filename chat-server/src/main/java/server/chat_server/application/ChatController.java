package server.chat_server.application;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import server.chat_server.entity.ChatMessage;

@Controller
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;

    public ChatController(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.privateMessage")
    public void sendPrivateMessage(@Payload ChatMessage chatMessage) {
        // 수신자에게 메시지 전송
        messagingTemplate.convertAndSendToUser(
            chatMessage.getRecipient(),
            "/queue/messages",
            chatMessage
        );

        // 발신자에게도 메시지 전송 (자신이 보낸 메시지를 볼 수 있도록)
        messagingTemplate.convertAndSendToUser(
            chatMessage.getSender(),
            "/queue/messages",
            chatMessage
        );
    }
}