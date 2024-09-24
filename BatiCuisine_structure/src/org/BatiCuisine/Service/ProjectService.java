package org.BatiCuisine.Service;

import org.BatiCuisine.Model.Project;
import org.BatiCuisine.Repository.Interfaces.ProjectRepository;

import java.util.List;
import java.util.UUID;

public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void addProject(Project project) {
        projectRepository.addProject(project);
    }

    public Project getProjectById(UUID projectId) {
        return projectRepository.getProjectById(projectId);
    }

    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }
}
