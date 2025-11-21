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
        System.out.println("Broadcasting message: " +
                           "From: " + chatMessage.getSender() +
                           ", To: " + chatMessage.getRecipient() +
                           ", Content: " + chatMessage.getContent());

        // 모든 구독자에게 메시지 브로드캐스트
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
}