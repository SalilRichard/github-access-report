package com.salil.githubreport.github_access_report.controller;

import com.salil.githubreport.github_access_report.service.GithubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessController {

    private final GithubService service;

    public AccessController(GithubService service) {
        this.service = service;
    }

    @GetMapping("/access-report")
    public Object getAccessReport() {
        return service.getUserRepoMap();
    }
}