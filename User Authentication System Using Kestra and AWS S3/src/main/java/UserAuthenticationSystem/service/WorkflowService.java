package com.auth.service;
import com.auth.config.AppConfig;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WorkflowService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String kestraApiUrl;

    public WorkflowService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        this.kestraApiUrl = AppConfig.getKestraApiUrl();
    }

    public void triggerRegistrationWorkflow(String username, String email) {
        Map<String, String> inputs = Map.of(
            "username", username,
            "email", email
        );
        triggerWorkflow("user_registration", inputs);
    }

    public void triggerPasswordResetWorkflow(String username, String email, String resetToken) {
        String resetLink = generateResetLink(resetToken);
        Map<String, String> inputs = Map.of(
            "username", username,
            "email", email,
            "resetLink", resetLink
        );
        triggerWorkflow("password_reset", inputs);
    }

    public void triggerSecurityAlert(String username, String ipAddress) {
        Map<String, String> inputs = Map.of(
            "username", username,
            "ipAddress", ipAddress
        );
        triggerWorkflow("security_monitoring", inputs);
    }

    public void triggerBackupWorkflow() {
        triggerWorkflow("data_backup", Map.of());
    }

    private void triggerWorkflow(String workflowId, Map<String, String> inputs) {
        try {
            String jsonInputs = objectMapper.writeValueAsString(inputs);
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(kestraApiUrl + "/api/v1/executions"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonInputs))
                .build();

            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    if (response.statusCode() != 200) {
                        System.err.println("Error triggering workflow: " + response.body());
                    }
                });
        } catch (Exception e) {
            System.err.println("Error triggering workflow: " + e.getMessage());
        }
    }

    private String generateResetLink(String token) {
        return String.format("https://auth-system.com/reset-password?token=%s", token);
    }
}
