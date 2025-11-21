package server.chat_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.chat_server.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
