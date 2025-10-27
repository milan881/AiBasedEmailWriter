package com.email.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.email.model.EmailRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class EmailGeneratorService {

	private final WebClient webClient;
	
	@Value("${gemini.api.url}")
	private String geminiApiUrl;
	
	@Value("${gemini.api.key}")
	private String geminiApiKey;
	
	public EmailGeneratorService(WebClient.Builder WebClientBuilder) {
		this.webClient = WebClientBuilder.build();
	}
	
	public String generateEmailReply(EmailRequest emailRequest) {
		
		//Build Prompt
		String prompt = buildPrompt(emailRequest);
		
		
		//Craft the request
		Map<String, Object> requestBody = Map.of(
			    "contents", new Object[] {
			        Map.of("parts", new Object[] {
			            Map.of("text", prompt)
			        })
			    }
			);

			String response = webClient.post()
					.uri(geminiApiUrl)
                    //.header("X-goog-api-key", geminiApiKey)
                    .header(geminiApiUrl+geminiApiKey)
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
					.bodyValue(requestBody)
					.retrieve()
					.bodyToMono(String.class)
					.block();
		//return response and return
		return extractResponseContent(response);
	}

	private String extractResponseContent(String response) {
		// TODO Auto-generated method stub
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(response);
			return rootNode.path("candidates")
							.get(0)
							.path("content")
							.path("parts")
							.get(0)
							.path("text")
							.asText();
		} catch (Exception e) {
			// TODO: handle exception
			return "Error Processing request"+e.getMessage();
		}
		

	}

	private String buildPrompt(EmailRequest emailRequest) {
		// TODO Auto-generated method stub
		StringBuilder prompt = new StringBuilder();
		
		prompt.append("Generate a professional email reply for the fallowing email content. please dont generate a subject line ");
		/*
		if(emailRequest.getTone() != null && emailRequest.getTone().isEmpty()) {
			prompt.append("use a ").append(emailRequest.getTone()).append("tone.");
		}
		*/
		if (emailRequest.getTone() != null && !emailRequest.getTone().isEmpty()) {
		    prompt.append(" Use a ").append(emailRequest.getTone()).append(" tone.");
		}

		
		prompt.append("\nOriginal email: \n").append(emailRequest.getEmailContent());
		
		
		return prompt.toString();
	}
	
}