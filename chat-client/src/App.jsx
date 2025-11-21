import React, { useContext } from 'react';
import ChatPage from './components/ChatPage';
import LoginPage from './components/LoginPage';
import WebSocketContext from './context/WebSocketContext';

function App() {
  const { isConnected } = useContext(WebSocketContext);

  return (
    <div className="App">
      {isConnected ? <ChatPage /> : <LoginPage />}
    </div>
  );
}

export default App;