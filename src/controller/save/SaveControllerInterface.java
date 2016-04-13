package controller.save;

import model.resources.General;

@FunctionalInterface
public interface SaveControllerInterface {
    void save(General g);
}