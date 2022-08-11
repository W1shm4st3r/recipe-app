package git.w1shm4st3r.recipeapp.services;

import git.w1shm4st3r.recipeapp.commands.IngredientCommand;
import git.w1shm4st3r.recipeapp.domain.Ingredient;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
//    IngredientCommand saveIngredientCommand(IngredientCommand command);
//    void deleteById(Long recipeId, Long idToDelete);
}
