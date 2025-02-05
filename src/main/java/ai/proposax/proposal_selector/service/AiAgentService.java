package ai.proposax.proposal_selector.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AiAgentService {

    @Value("${openai.api.key}")
    private String apiKey;

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    public String queryChatGPTWithMultiplePDFs(List<String> fileUrls) {
        RestTemplate restTemplate = new RestTemplate();

        // Formatting the file URLs for ChatGPT
        String filesList = String.join("\n", fileUrls);

        // Custom prompt to instruct ChatGPT
        String prompt = "Compare the following proposals and provide insights on their similarities, differences, and the best against the provided RFP:\n\n" + filesList;

        // Request payload
        Map<String, Object> request = new HashMap<>();
        request.put("model", "gpt-4-turbo"); // Use GPT-4 Turbo for better document processing
        request.put("messages", List.of(Map.of("role", "user", "content", prompt)));
        request.put("temperature", 0.7);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        ResponseEntity<Map> response = restTemplate.exchange(OPENAI_API_URL, HttpMethod.POST, entity, Map.class);

        // Correct casting and extraction of the response
        Map<String, Object> responseBody = response.getBody();
        if (responseBody == null || !responseBody.containsKey("choices")) {
            throw new RuntimeException("Invalid response from OpenAI API");
        }

        // Get the list of choices
        List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
        if (choices.isEmpty()) {
            throw new RuntimeException("No choices returned from OpenAI API");
        }

        // Extract the first message content
        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
        if (message == null || !message.containsKey("content")) {
            throw new RuntimeException("Invalid message format in OpenAI response");
        }

        return (String) message.get("content");
    }
}

