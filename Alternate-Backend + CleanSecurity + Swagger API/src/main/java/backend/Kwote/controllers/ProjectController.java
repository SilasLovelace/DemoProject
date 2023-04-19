package backend.Kwote.controllers;

import backend.Kwote.dtos.ProjectPostDto;
import backend.Kwote.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/project/")
@EnableMethodSecurity
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @PostMapping
    ResponseEntity <?> postProject (@RequestBody ProjectPostDto projectPostDto){
        return ResponseEntity.ok(projectService.postProject(projectPostDto));
    }
}
