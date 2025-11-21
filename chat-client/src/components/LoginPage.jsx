import React, { useState, useContext } from 'react';
import WebSocketContext from '../context/WebSocketContext';
import './LoginPage.css';

const LoginPage = () => {
  const [username, setUsername] = useState('');
  const { connect } = useContext(WebSocketContext);

  const handleLogin = (e) => {
    e.preventDefault();
    if (username.trim()) {
      const randomSuffix = Math.floor(Math.random() * 1000) + 1;
      const finalUsername = `${username.trim()}_${randomSuffix}`;
      connect(finalUsername);
    }
  };

  return (
    <div className="login-page">
      <div className="login-container">
        <h1>채팅 시작하기</h1>
        <form onSubmit={handleLogin} className="login-form">
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="사용자 이름을 입력하세요"
            className="login-input"
            required
          />
          <button type="submit" className="login-button">
            입장
          </button>
        </form>
      </div>
    </div>
  );
};

export default LoginPage;
