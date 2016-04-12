package controller.save;

import controller.modelResources.General;

@FunctionalInterface
public interface SaveControllerInterface {
    void save(General g);
}