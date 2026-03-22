package com.salil.githubreport.github_access_report.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class GithubClient {

    private final WebClient webClient;

    @Value("${github.org}")
    private String org;

    public GithubClient(@Value("${github.token}") String token) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.github.com")
                .defaultHeader("Authorization", "Bearer " + token)
                .build();
    }

    public List<Map> getRepos() {
        return webClient.get()
                .uri("/users/" + org + "/repos?per_page=100")
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }

    public List<Map> getCollaborators(String repo) {
        return webClient.get()
                .uri("/repos/" + org + "/" + repo + "/collaborators")
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }
}