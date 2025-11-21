package server.chat_server.application;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import server.chat_server.entity.ChatMessage;
import server.chat_server.service.InMemoryChatService;

@Controller
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final InMemoryChatService chatService;

    public ChatController(SimpMessageSendingOperations messagingTemplate, InMemoryChatService chatService) {
        this.messagingTemplate = messagingTemplate;
        this.chatService = chatService;
    }

    @MessageMapping("/chat.privateMessage")
    public void sendPrivateMessage(@Payload ChatMessage chatMessage) {
        // 메시지를 인메모리 리스트에 저장
        chatService.saveMessage(chatMessage);

        // 수신된 메시지 로깅
        System.out.println("Received private message: " +
                           "From: " + chatMessage.getSender() +
                           ", To: " + chatMessage.getRecipient() +
                           ", Content: " + chatMessage.getContent());

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
