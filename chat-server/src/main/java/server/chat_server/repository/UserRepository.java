package server.chat_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.chat_server.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
}
