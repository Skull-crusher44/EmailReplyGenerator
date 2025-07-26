# EmailReplyGenerator

EmailReplyGenerator is a Java-based backend service designed to generate AI-powered responses to emails. Built with Spring Boot and Google GenAI, this project provides an API for creating intelligent, automated email replies.

## Features

- Generate contextually relevant email replies using AI (Google GenAI integration)
- RESTful API built with Spring Boot (supports both Web and Reactive WebFlux)
- Simple, extensible Java codebase
- Easy to run locally or deploy in Docker

## Project Structure

- Main language: Java
- Build system: Maven
- Key dependencies: Spring Boot, WebFlux, Google GenAI, Lombok

## Getting Started

### Prerequisites

- Java 19 or higher
- Maven 3.x
- (Optional) Docker

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Skull-crusher44/EmailReplyGenerator.git
   cd EmailReplyGenerator
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
   The backend will start on `http://localhost:8080`.

4. **(Optional) Run with Docker:**
   ```bash
   docker build -t email-reply-generator .
   docker run -p 8080:8080 email-reply-generator
   ```

## Usage

The application exposes API endpoints to generate AI-based email replies. Example usage:

- `POST /api/reply` with an email body in the request will return a suggested reply.

_Example Java class:_
```java
public class EmailReply {
    String reply;
}
```

## Dependencies

- Spring Boot
- Spring WebFlux
- Google GenAI
- Lombok

See [`pom.xml`](./pom.xml) for a full list.

## License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.

## Contributing

Contributions are welcome! Please open issues or submit pull requests on GitHub.

---

For more details, visit the [GitHub repository](https://github.com/Skull-crusher44/EmailReplyGenerator).
