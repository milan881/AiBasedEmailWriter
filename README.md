# 📧 AI-Powered Email Writer

An intelligent email reply generator that uses Google's Gemini AI to craft professional email responses with customizable tones. Built with React frontend and Spring Boot backend.

## 🌟 Features

- **AI-Powered Email Generation**: Leverages Google Gemini AI to generate contextually relevant email replies
- **Multiple Tone Options**: Choose from 7 different tones:
  - Professional
  - Friendly
  - Formal
  - Casual
  - Assertive
  - Apologetic
  - Grateful
- **Modern UI**: Clean and intuitive interface built with React and Material-UI
- **Real-time Generation**: Fast API responses with loading states and error handling
- **Copy to Clipboard**: Easily copy generated replies
- **Responsive Design**: Works seamlessly across devices

## 🏗️ Project Structure

The project consists of two main components:

```
Email Project/
├── email-writer-react/          # React Frontend
│   ├── src/
│   │   ├── App.jsx              # Main application component
│   │   ├── App.css              # Application styles
│   │   └── main.jsx             # Application entry point
│   ├── package.json
│   └── vite.config.js
│
└── EmailWriter/                 # Spring Boot Backend
    ├── src/
    │   └── main/
    │       └── java/com/email/
    │           ├── EmailWriterApplication.java
    │           ├── controller/
    │           │   └── EmailGeneratorController.java
    │           ├── model/
    │           │   └── EmailRequest.java
    │           └── service/
    │               └── EmailGeneratorService.java
    └── pom.xml
```

## 🚀 Getting Started

### Prerequisites

- **Node.js** (v16 or higher)
- **Java** (JDK 25 or compatible version)
- **Maven** (v3.6 or higher)
- **Google Gemini API Key** ([Get one here](https://makersuite.google.com/app/apikey))

### Backend Setup

1. **Navigate to the backend directory**:
   ```powershell
   cd EmailWriter
   ```

2. **Set up environment variables**:
   Create environment variables for your Gemini API credentials:
   ```powershell
   $env:geminiApiUrl="https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent"
   $env:geminiApiKey="YOUR_GEMINI_API_KEY"
   ```

3. **Build the project**:
   ```powershell
   mvn clean install
   ```

4. **Run the Spring Boot application**:
   ```powershell
   mvn spring-boot:run
   ```

   The backend will start on `http://localhost:8080`

### Frontend Setup

1. **Navigate to the frontend directory**:
   ```powershell
   cd email-writer-react
   ```

2. **Install dependencies**:
   ```powershell
   npm install
   ```

3. **Start the development server**:
   ```powershell
   npm run dev
   ```

   The frontend will start on `http://localhost:5173` (or the next available port)

## 🔧 Configuration

### Backend Configuration

Edit `EmailWriter/src/main/resources/application.properties`:

```properties
spring.application.name=EmailWriter
gemini.api.url=${geminiApiUrl}
gemini.api.key=${geminiApiKey}
```

### Frontend Configuration

The frontend is configured to connect to the backend at `http://localhost:8080`. If you change the backend port, update the API URL in `src/App.jsx`:

```javascript
const response = await axios.post('http://localhost:8080/api/email/generate', {
  emailContent: emailContent,
  tone: tone
});
```

## 📖 Usage

1. **Open the application** in your browser (typically `http://localhost:5173`)
2. **Paste the email** you want to reply to in the text area
3. **Select the desired tone** from the dropdown menu
4. **Click "Generate Reply"** to create an AI-powered response
5. **Review the generated reply** in the output section
6. **Copy to clipboard** or clear to start over

## 🛠️ Tech Stack

### Frontend
- **React 19.1.1** - UI library
- **Vite 7.1.7** - Build tool and dev server
- **Material-UI 7.3.4** - Component library
- **Axios 1.12.2** - HTTP client
- **ESLint** - Code linting

### Backend
- **Spring Boot 3.5.7** - Application framework
- **Spring WebFlux** - Reactive web framework
- **WebClient** - HTTP client for external API calls
- **Maven** - Dependency management

### AI Service
- **Google Gemini API** - Generative AI model

## 📡 API Endpoints

### Generate Email Reply

**Endpoint**: `POST /api/email/generate`

**Request Body**:
```json
{
  "emailContent": "Original email content here...",
  "tone": "professional"
}
```

**Response**: Plain text containing the generated email reply

**Example**:
```bash
curl -X POST http://localhost:8080/api/email/generate \
  -H "Content-Type: application/json" \
  -d '{"emailContent":"Hello, I need help with my order","tone":"professional"}'
```

## 🔐 Security Notes

- ⚠️ **CORS is currently configured to allow all origins** (`@CrossOrigin(origins = "*")`). For production, restrict this to your frontend domain.
- 🔑 **API keys should be stored securely** using environment variables, never commit them to version control.
- 🛡️ Consider implementing rate limiting and authentication for production use.

## 🐛 Troubleshooting

### Backend Issues

**Error: Cannot find Java 25**
- Ensure Java 25 is installed or modify `pom.xml` to use a compatible Java version (17+)

**Error: API Key not found**
- Verify environment variables are set correctly
- Restart the Spring Boot application after setting environment variables

### Frontend Issues

**Error: Network Error**
- Ensure the backend is running on `http://localhost:8080`
- Check CORS configuration in the backend

**Error: Module not found**
- Run `npm install` to install all dependencies
- Clear node_modules and reinstall: `rm -r node_modules; npm install`

## 📝 Development Scripts

### Frontend
```powershell
npm run dev      # Start development server
npm run build    # Build for production
npm run preview  # Preview production build
npm run lint     # Run ESLint
```

### Backend
```powershell
mvn clean install        # Build the project
mvn spring-boot:run      # Run the application
mvn test                 # Run tests
```

## 🚢 Deployment

### Frontend
```powershell
npm run build
```
Deploy the `dist` folder to your hosting service (Vercel, Netlify, etc.)

### Backend
```powershell
mvn clean package
```
Deploy the generated JAR file from `target/` directory to your server or cloud platform (AWS, Heroku, Azure, etc.)

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is open source and available for educational purposes.

## 👤 Author

Milan

## 🙏 Acknowledgments

- Google Gemini AI for providing the generative AI capabilities
- Spring Boot team for the excellent framework
- React and Vite teams for modern frontend tooling
- Material-UI for beautiful React components

---

**Made with ❤️ and ☕**
