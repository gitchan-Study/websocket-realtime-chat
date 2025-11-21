import React from 'react';
import './MessageList.css';

const MessageList = () => {
  // Sample message data
  const messages = [
    { id: 1, text: '안녕하세요!', sender: 'other' },
    { id: 2, text: '네, 안녕하세요! 반갑습니다.', sender: 'me' },
    { id: 3, text: '채팅 UI가 정말 깔끔하네요.', sender: 'other' },
    { id: 4, text: '감사합니다! LINE 메신저 스타일로 만들어봤어요.', sender: 'me' },
  ];

  return (
    <div className="message-list">
      {messages.map(message => (
        <div key={message.id} className={`message-item ${message.sender}`}>
          <div className="message-bubble">
            {message.text}
          </div>
        </div>
      ))}
    </div>
  );
};

export default MessageList;
