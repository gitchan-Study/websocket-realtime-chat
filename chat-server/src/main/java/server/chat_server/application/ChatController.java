package server.chat_server.application;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import server.chat_server.entity.ChatMessage;
import server.chat_server.service.ChatService;

import java.time.LocalDateTime;

@Controller
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatService chatService;

    public ChatController(SimpMessageSendingOperations messagingTemplate, ChatService chatService) {
        this.messagingTemplate = messagingTemplate;
        this.chatService = chatService;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        ChatMessage messageWithTimestamp = ChatMessage.builder()
                .id(chatMessage.getId())
                .roomId(chatMessage.getRoomId())
                .userId(chatMessage.getUserId())
                .message(chatMessage.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        chatService.saveMessage(messageWithTimestamp);
        messagingTemplate.convertAndSend("/topic/public", messageWithTimestamp);
    }
}
