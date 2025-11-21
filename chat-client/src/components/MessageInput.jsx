import React from 'react';
import './MessageInput.css';

const MessageInput = () => {
  return (
    <div className="message-input-container">
      <input
        type="text"
        className="message-input"
        placeholder="메시지를 입력하세요..."
      />
      <button className="send-button">전송</button>
    </div>
  );
};

export default MessageInput;
