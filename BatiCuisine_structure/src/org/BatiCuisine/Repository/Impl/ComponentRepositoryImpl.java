package org.BatiCuisine.Repository.Impl;

import org.BatiCuisine.Enum.ComponentType;
import org.BatiCuisine.Model.Component;
import org.BatiCuisine.Model.Labor;
import org.BatiCuisine.Model.Material;
import org.BatiCuisine.Dao.Interfaces.LaborDao;
import org.BatiCuisine.Dao.Interfaces.MaterialDao;
import org.BatiCuisine.Repository.Interfaces.ComponentRepository;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ComponentRepositoryImpl implements ComponentRepository {

    private final LaborDao laborDao;
    private final MaterialDao materialDao;

    public ComponentRepositoryImpl(LaborDao laborDao, MaterialDao materialDao) {
        this.laborDao = laborDao;
        this.materialDao = materialDao;
    }

    @Override
    public void addComponent(Component component) {
        if (component.getComponentType() == ComponentType.LABOR) {
            laborDao.create((Labor) component);
        }

        if (component.getComponentType() == ComponentType.MATERIAL) {
            materialDao.create((Material) component);
        }
    }

    @Override
    public Component getComponentById(UUID componentID) {
        Component component = laborDao.read(componentID);
        if (component == null) {
            component = materialDao.read(componentID);
        }
        return component;
    }

    @Override
    public List<Component> getAllComponents() {
        List<Component> components = new ArrayList<>();
        components.addAll(laborDao.getAll());
        components.addAll(materialDao.getAll());
        return components;
    }

    @Override
    public void updateComponent(Component component) {
        if (component.getComponentType() == ComponentType.LABOR) {
            laborDao.update((Labor) component);
        }

        if (component.getComponentType() == ComponentType.MATERIAL) {
            materialDao.update((Material) component);
        }
    }


    @Override
    public boolean removeComponent(UUID componentID) {
        boolean removed = laborDao.delete(componentID);
        if (!removed) {
            removed = materialDao.delete(componentID);
        }
        return removed;
    }

    public List<Component> getProjectComponents(UUID projectID) {
        List<Component> components = new ArrayList<>();
        components.addAll(materialDao.getAll());
        components.addAll(laborDao.getAll());

        components = components.stream()
                .filter(component->component.getProject().getProjectID() == projectID)
                .toList();

        return components;
    }
}
