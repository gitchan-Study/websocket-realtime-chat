import React, { createContext, useState, useCallback } from 'react';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

const WebSocketContext = createContext(null);

export const WebSocketProvider = ({ children }) => {
  const [stompClient, setStompClient] = useState(null);
  const [messages, setMessages] = useState([]);
  const [user, setUser] = useState(null);
  const [isConnected, setIsConnected] = useState(false);

  const connect = useCallback((username) => {
    const socket = new SockJS('http://localhost:8080/ws'); // 서버 WebSocket 엔드포인트
    const client = new Client({
      webSocketFactory: () => socket,
      debug: (str) => {
        console.log(new Date(), str);
      },
      onConnect: () => {
        console.log('Connected to WebSocket');
        setIsConnected(true);
        setUser({ username });

        client.subscribe('/topic/messages', (message) => {
          const receivedMessage = JSON.parse(message.body);
          setMessages((prevMessages) => [...prevMessages, receivedMessage]);
        });
      },
      onStompError: (frame) => {
        console.error('Broker reported error: ' + frame.headers['message']);
        console.error('Additional details: ' + frame.body);
      },
    });

    client.activate();
    setStompClient(client);
  }, []);

  const disconnect = useCallback(() => {
    if (stompClient) {
      stompClient.deactivate();
      setIsConnected(false);
      setUser(null);
      setMessages([]);
      console.log('Disconnected');
    }
  }, [stompClient]);

  const sendMessage = useCallback((messageText) => {
    if (stompClient && isConnected && user) {
      const chatMessage = {
        sender: user.username,
        content: messageText,
        type: 'CHAT'
      };
      stompClient.publish({
        destination: '/app/chat.sendMessage',
        body: JSON.stringify(chatMessage),
      });
    }
  }, [stompClient, isConnected, user]);

  const value = {
    connect,
    disconnect,
    sendMessage,
    messages,
    user,
    isConnected,
  };

  return (
    <WebSocketContext.Provider value={value}>
      {children}
    </WebSocketContext.Provider>
  );
};

export default WebSocketContext;
