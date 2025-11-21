import React, { useState, useContext } from 'react';
import WebSocketContext from '../context/WebSocketContext';
import './MessageInput.css';

const MessageInput = () => {
  const [messageText, setMessageText] = useState('');
  const { sendMessage, recipient } = useContext(WebSocketContext);

  const handleSendMessage = (e) => {
    e.preventDefault();
    if (messageText.trim() && recipient.trim()) {
      sendMessage(messageText, recipient);
      setMessageText('');
    }
  };

  return (
    <form className="message-input-container" onSubmit={handleSendMessage}>
      <input
        type="text"
        className="message-input"
        placeholder="메시지를 입력하세요..."
        value={messageText}
        onChange={(e) => setMessageText(e.target.value)}
      />
      <button type="submit" className="send-button">전송</button>
    </form>
  );
};

export default MessageInput;
