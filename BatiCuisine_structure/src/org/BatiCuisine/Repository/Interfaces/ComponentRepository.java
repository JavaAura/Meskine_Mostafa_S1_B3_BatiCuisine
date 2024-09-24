package org.BatiCuisine.Repository.Interfaces;

import org.BatiCuisine.Model.Component;

import java.util.List;
import java.util.UUID;

public interface ComponentRepository {
    void addComponent(Component component);

    Component getComponentById(UUID componentID);

    List<Component> getAllComponents();

    public List<Component> getProjectComponents(UUID projectID);
}
