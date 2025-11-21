import React, { useContext } from 'react';
import WebSocketContext from '../context/WebSocketContext';
import './RecipientInput.css';

const RecipientInput = () => {
  const { recipient, setRecipient } = useContext(WebSocketContext);

  return (
    <div className="recipient-input-container">
      <input
        type="text"
        className="recipient-input"
        placeholder="받는 사람 닉네임을 입력하세요..."
        value={recipient}
        onChange={(e) => setRecipient(e.target.value)}
      />
    </div>
  );
};

export default RecipientInput;
