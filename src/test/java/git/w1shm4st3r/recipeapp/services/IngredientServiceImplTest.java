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
        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, recipeRepository);
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
}