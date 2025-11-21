import React from 'react';
import MessageList from './MessageList';
import MessageInput from './MessageInput';
import './ChatPage.css';

const ChatPage = () => {
  return (
    <div className="chat-page">
      <header className="chat-header">
        <h1>채팅방</h1>
      </header>
      <MessageList />
      <MessageInput />
    </div>
  );
};

export default ChatPage;
