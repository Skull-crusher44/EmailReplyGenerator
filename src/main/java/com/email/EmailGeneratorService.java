package com.email;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmailGeneratorService {

    private final GeminiClient geminiClient;
    private final OpenAIClient openAIClient;

    public EmailGeneratorService(WebClient.Builder webClientBuilder,
                                 GeminiClient geminiClient,
                                 OpenAIClient openAIClient) {
        this.geminiClient = geminiClient;
        this.openAIClient = openAIClient;
    }

    public String generateEmailService(EmailType emailRequest) {
        String prompt = buildPrompt(emailRequest);
        String model = emailRequest.getModel();

        if ("openai".equalsIgnoreCase(model)) {
            String additionalPrompt = emailRequest.getAdditionalInput();
            return openAIClient.generateResponse(prompt,additionalPrompt);
        } else if ("gemini".equalsIgnoreCase(model)) {
            return geminiClient.generateResponse(prompt);
        } else {
            return "Unsupported model: " + model;
        }
    }

    private String buildPrompt(EmailType emailRequest) {
        if (emailRequest == null || emailRequest.getEmail() == null || emailRequest.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email content cannot be empty");
        }

        StringBuilder prompt = new StringBuilder();
        prompt.append("Task: Generate a professional email response\n");
        prompt.append("Requirements:\n");
        prompt.append("1. Do not include a subject line\n");
        prompt.append("2. Keep the response according to tone  and concise\n");
        String additionalPrompt = emailRequest.getAdditionalInput();

        if(additionalPrompt.isEmpty())
            prompt.append( "Make good response");
        else{
            prompt.append(" Specially Focus on this point: ");
            prompt.append(additionalPrompt);
        }

        if (emailRequest.getTone() != null && !emailRequest.getTone().trim().isEmpty()) {
            prompt.append("3. Use a ").append(emailRequest.getTone().trim()).append(" tone\n");
        }

        prompt.append("\nOriginal Email:\n");
        prompt.append("-------------------\n");
        prompt.append(emailRequest.getEmail().trim());
        prompt.append("\n-------------------\n");
        prompt.append("\nPlease generate an appropriate response to the above email.");

        return prompt.toString();
    }
}
