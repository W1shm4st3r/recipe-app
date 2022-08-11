package git.w1shm4st3r.recipeapp.converter;

import git.w1shm4st3r.recipeapp.commands.IngredientCommand;
import git.w1shm4st3r.recipeapp.commands.UnitOfMeasureCommand;
import git.w1shm4st3r.recipeapp.domain.Ingredient;
import git.w1shm4st3r.recipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID_VALUE = 1L;
    public static final Long UOM_ID = 2L;
    IngredientCommandToIngredient converter;

    @BeforeEach
    public void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() {
        //GIVEN
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        command.setUom(unitOfMeasureCommand);

        //WHEN
        Ingredient ingredient = converter.convert(command);

        //THEN
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }

    @Test
    public void convertWithNullUOM() {
        //GIVEN
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();


        //WHEN
        Ingredient ingredient = converter.convert(command);

        //THEN
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
    }
}