package no.lagalt.lagaltbackend.controller;

import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.service.ProjectService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
}
