package git.w1shm4st3r.recipeapp.services;

import git.w1shm4st3r.recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
