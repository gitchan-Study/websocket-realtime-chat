package server.chat_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.chat_server.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
