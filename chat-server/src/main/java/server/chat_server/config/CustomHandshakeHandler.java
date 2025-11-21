package server.chat_server.config;

import com.sun.security.auth.UserPrincipal;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, java.util.Map<String, Object> attributes) {
        // URI에서 'username' 쿼리 파라미터 추출
        List<String> userNames = UriComponentsBuilder.fromUri(request.getURI()).build().getQueryParams().get("username");
        if (userNames != null && !userNames.isEmpty()) {
            // UserPrincipal 객체를 생성하여 반환 (이것이 세션의 사용자가 됨)
            return new UserPrincipal(userNames.get(0));
        }
        // username이 없으면 연결을 허용하지 않음 (null 반환)
        return null;
    }
}
