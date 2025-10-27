import './App.css'
import { useState } from 'react';
import axios from 'axios';

function App() {
  const [emailContent, setEmailContent] = useState('');
  const [tone, setTone] = useState('professional');
  const [generatedReply, setGeneratedReply] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const toneOptions = ['professional', 'friendly', 'formal', 'casual', 'assertive', 'apologetic', 'grateful'];

  const handleGenerateReply = async () => {
    if (!emailContent.trim()) {
      setError('Please enter email content');
      return;
    }

    setLoading(true);
    setError('');
    setGeneratedReply('');

    try {
      const response = await axios.post('http://localhost:8080/api/email/generate', {
        emailContent: emailContent,
        tone: tone
      });

      setGeneratedReply(response.data);
    } catch (err) {
      setError(err.response?.data || err.message || 'Error generating reply');
    } finally {
      setLoading(false);
    }
  };

  const handleCopyToClipboard = () => {
    navigator.clipboard.writeText(generatedReply);
    alert('Reply copied to clipboard!');
  };

  const handleClear = () => {
    setEmailContent('');
    setTone('professional');
    setGeneratedReply('');
    setError('');
  };

  return (
    <div className="container">
      <h1>Email Reply Generator</h1>

      {error && (
        <div className="error">
          {error}
          <button onClick={() => setError('')}>×</button>
        </div>
      )}

      <div className="content">
        <div className="section">
          <h2>Original Email</h2>
          <textarea
            value={emailContent}
            onChange={(e) => setEmailContent(e.target.value)}
            placeholder="Paste the email you want to reply to..."
            rows="10"
          />

          <div className="controls">
            <div>
              <label>Tone:</label>
              <select value={tone} onChange={(e) => setTone(e.target.value)}>
                {toneOptions.map((option) => (
                  <option key={option} value={option}>
                    {option.charAt(0).toUpperCase() + option.slice(1)}
                  </option>
                ))}
              </select>
            </div>

            <div className="buttons">
              <button 
                onClick={handleGenerateReply} 
                disabled={loading || !emailContent.trim()}
                className="btn-primary"
              >
                {loading ? 'Generating...' : 'Generate Reply'}
              </button>
              <button onClick={handleClear} className="btn-secondary">
                Clear
              </button>
            </div>
          </div>
        </div>

        <div className="section">
          <h2>Generated Reply</h2>
          {generatedReply ? (
            <>
              <textarea
                value={generatedReply}
                readOnly
                rows="10"
              />
              <button onClick={handleCopyToClipboard} className="btn-primary">
                Copy to Clipboard
              </button>
            </>
          ) : (
            <div className="placeholder">
              {loading ? 'Generating your reply...' : 'Your reply will appear here'}
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default App
