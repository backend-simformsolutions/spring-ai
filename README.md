# Spring Boot + OpenAI Integration (POC)

This proof-of-concept (POC) project demonstrates how to integrate OpenAI with a Spring Boot application to leverage various AI capabilities, including:

- **Prompt-based text response (Chat/Completion)**
- **Image to Text (Image Captioning / OCR)**
- **Text to Image (Image Generation)**
- **Audio to Text (Speech-to-Text/Transcription)**
- **Text to Audio (Text-to-Speech)**

---

## 🛠️ Technologies Used

- Java 17+
- Spring Boot 3.x
- OpenAI API
- RestTemplate / WebClient
- Maven
- Multipart file handling
- Lombok (optional)

---

## 🔧 Setup and Integration Steps

### 1. **Clone the Repository**
```bash
git clone https://github.com/your-repo/springboot-openai-poc.git
cd springboot-openai-poc
```

### 2. **Create/OpenAI API Key**
- Go to [OpenAI Platform](https://platform.openai.com/account/api-keys)
- Generate your API key.

### 3. **Configure API Key**
Add your OpenAI API key to `application.yml` or `application.properties`:

**application.yml**
```yaml
openai:
  api:
    key: YOUR_OPENAI_API_KEY
    url: https://api.openai.com/v1
```

**application.properties**
```properties
openai.api.key=YOUR_OPENAI_API_KEY
openai.api.url=https://api.openai.com/v1
```

---

## 💬 Features Overview

### 1. **Prompt-Based Response**
- **Endpoint**: `GET /{prompt}`
- **Request**: Prompt string
- **Uses**: OpenAI `gpt-3.5-turbo` or `gpt-4` models
- **Example**:
```json
{
  "prompt": "Explain the concept of polymorphism in Java."
}
```

---

### 2. **Image to Text**
- **Endpoint**: `GET /image-to-text`
- **Request**:  Image file is already added
- **Uses**: OpenAI `gpt-4-vision-preview` or vision model

---

### 3. **Text to Image**
- **Endpoint**: `GET /text-to-image/{prompt}`
- **Request**: Image description
- **Uses**: OpenAI DALL·E API
- **Example**:
```json
{
  "prompt": "A futuristic city floating in the clouds"
}
```

---

### 4. **Audio to Text**
- **Endpoint**: `GET /audio-to-text`
- **Request**: Audio file is already added.
  - **Uses**: OpenAI Whisper model for transcription

---

### 5. **Text to Audio**
- **Endpoint**: `GET /text-to-audio/{prompt}`
- **Response**: Audio file (MP3)
- **Uses**: OpenAI `tts-1` or TTS-compatible model

---

## 🔐 Security

Ensure that your API key is **not hardcoded** or exposed in logs. Use environment variables or secrets management tools for production-grade applications.

---

## 🛆 Build and Run

### Build:
```bash
mvn clean install
```

### Run:
```bash
mvn spring-boot:run
```

---
