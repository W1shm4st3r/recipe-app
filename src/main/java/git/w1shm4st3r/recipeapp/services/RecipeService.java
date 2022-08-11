package git.w1shm4st3r.recipeapp.services;

import git.w1shm4st3r.recipeapp.commands.RecipeCommand;
import git.w1shm4st3r.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand findCommandById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
