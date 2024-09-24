package org.BatiCuisine.Repository.Impl;

import org.BatiCuisine.Dao.Interfaces.ProjectDao;
import org.BatiCuisine.Model.Project;
import org.BatiCuisine.Repository.Interfaces.ProjectRepository;

import java.util.List;
import java.util.UUID;

public class ProjectRepositoryImpl implements ProjectRepository {

    private final ProjectDao projectDao;

    public ProjectRepositoryImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public void addProject(Project project) {
        projectDao.create(project);
    }

    @Override
    public Project getProjectById(UUID id) {
        return projectDao.read(id);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectDao.getAll();
    }
}
