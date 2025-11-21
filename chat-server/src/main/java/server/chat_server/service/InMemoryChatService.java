package server.chat_server.service;

import org.springframework.stereotype.Service;
import server.chat_server.entity.ChatMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class InMemoryChatService {

    // 동시성 문제를 방지하기 위해 synchronizedList 사용
    private final List<ChatMessage> messageHistory = Collections.synchronizedList(new ArrayList<>());

    public void saveMessage(ChatMessage chatMessage) {
        messageHistory.add(chatMessage);
    }

    public List<ChatMessage> getMessageHistory() {
        return new ArrayList<>(messageHistory); // 원본 리스트의 복사본을 반환
    }
}
