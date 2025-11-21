package server.chat_server.service;

import org.springframework.stereotype.Service;
import server.chat_server.entity.ChatMessage;
import server.chat_server.repository.ChatMessageRepository;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessage saveMessage(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }
}
