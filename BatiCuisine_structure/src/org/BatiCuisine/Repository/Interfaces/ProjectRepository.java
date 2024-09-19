package org.BatiCuisine.Repository.Interfaces;

import org.BatiCuisine.Model.Project;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository {
    void addProject(Project project);

    Project getProjectById(UUID id);

    void updateProject(Project project);

    boolean removeProject(UUID id);

    List<Project> getAllProjects();
}
