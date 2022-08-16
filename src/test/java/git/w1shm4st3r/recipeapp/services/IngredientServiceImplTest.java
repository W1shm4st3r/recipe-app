package git.w1shm4st3r.recipeapp.services;

import git.w1shm4st3r.recipeapp.commands.IngredientCommand;
import git.w1shm4st3r.recipeapp.converter.IngredientCommandToIngredient;
import git.w1shm4st3r.recipeapp.converter.IngredientToIngredientCommand;
import git.w1shm4st3r.recipeapp.converter.UnitOfMeasureCommandToUnitOfMeasure;
import git.w1shm4st3r.recipeapp.converter.UnitOfMeasureToUnitOfMeasureCommand;
import git.w1shm4st3r.recipeapp.domain.Ingredient;
import git.w1shm4st3r.recipeapp.domain.Recipe;
import git.w1shm4st3r.recipeapp.repositories.RecipeRepository;
import git.w1shm4st3r.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    //Converters initiation
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient, recipeRepository, unitOfMeasureRepository);
    }

    @Test
    void findByRecipeIdAndIngredientId() {
    }

    @Test
    void findByRecipeIdAndIngredientIdHappyPath() {
        //GIVEN
        Recipe recipe = new Recipe();
        recipe.setId(1L);

//        Ingredient ingredient1 = Ingredient.builder().id(1L).build();
//        Ingredient ingredient2 = Ingredient.builder().id(2L).build();
//        Ingredient ingredient3 = Ingredient.builder().id(3L).build();

        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();
        Ingredient ingredient3 = new Ingredient();
        ingredient1.setId(1L);
        ingredient2.setId(2L);
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //WHEN
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //THEN
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    void saveIngredientCommand() {
        //GIVEN
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        //WHEN
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        //THEN
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    void deleteById() {
        //GIVEN
        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        ingredient.setId(3L);
        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //WHEN
        ingredientService.deleteById(1L, 3L);

        //THEN
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
}