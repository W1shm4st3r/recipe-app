package git.w1shm4st3r.recipeapp.services;

import git.w1shm4st3r.recipeapp.commands.IngredientCommand;
import git.w1shm4st3r.recipeapp.converter.IngredientToIngredientCommand;
import git.w1shm4st3r.recipeapp.domain.Recipe;
import git.w1shm4st3r.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isEmpty()) {
            //TODO impl error handling
            log.error("Recipe id not found. Id: " + recipeId);
        }
        Recipe recipe = recipeOptional.get();
        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
        if (ingredientCommandOptional.isEmpty()) {
            //TODO impl error handling
            log.error("Ingredient id not found. Id: " + ingredientId);
        }
        return ingredientCommandOptional.get();
    }

//    @Override
//    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
//        return null;
//    }
//
//    @Override
//    public void deleteById(Long recipeId, Long idToDelete) {
//
//    }
}
