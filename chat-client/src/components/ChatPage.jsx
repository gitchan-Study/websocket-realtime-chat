import React, { useContext } from 'react';
import MessageList from './MessageList';
import MessageInput from './MessageInput';
import WebSocketContext from '../context/WebSocketContext';
import './ChatPage.css';

const ChatPage = () => {
  const { user, disconnect } = useContext(WebSocketContext);

  return (
    <div className="chat-page">
      <header className="chat-header">
        <h1>{user ? `${user.username}님` : '채팅방'}</h1>
        <button onClick={disconnect} className="logout-button">나가기</button>
      </header>
      <MessageList />
      <MessageInput />
    </div>
  );
};

export default ChatPage;
