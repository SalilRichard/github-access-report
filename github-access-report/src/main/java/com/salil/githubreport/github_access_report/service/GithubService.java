package com.salil.githubreport.github_access_report.service;

import com.salil.githubreport.github_access_report.client.GithubClient;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GithubService {

    private final GithubClient client;

    public GithubService(GithubClient client) {
        this.client = client;
    }

    public Map<String, List<String>> getUserRepoMap() {

        List<Map> repos = client.getRepos();
        Map<String, List<String>> userMap = new HashMap<>();

        for (Map repo : repos) {
            String repoName = (String) repo.get("name");

            List<Map> users = client.getCollaborators(repoName);

            for (Map user : users) {
                String username = (String) user.get("login");

                userMap.computeIfAbsent(username, k -> new ArrayList<>())
                        .add(repoName);
            }
        }

        return userMap;
    }
}