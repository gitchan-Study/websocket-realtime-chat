import React, { useContext, useEffect, useRef } from 'react';
import WebSocketContext from '../context/WebSocketContext';
import './MessageList.css';

const MessageList = () => {
  const { messages, user } = useContext(WebSocketContext);
  const messagesEndRef = useRef(null);

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  };

  useEffect(() => {
    scrollToBottom();
  }, [messages]);

  return (
    <div className="message-list">
      {messages.map((message, index) => (
        <div 
          key={index} 
          className={`message-item ${message.sender === user.username ? 'me' : 'other'}`}
        >
          <div className="message-bubble">
            {message.sender !== user.username && <div className="sender-name">{message.sender}</div>}
            {message.content}
          </div>
        </div>
      ))}
      <div ref={messagesEndRef} />
    </div>
  );
};

export default MessageList;
