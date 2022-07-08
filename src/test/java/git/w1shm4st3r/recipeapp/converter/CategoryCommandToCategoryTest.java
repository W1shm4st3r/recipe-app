package git.w1shm4st3r.recipeapp.converter;

import git.w1shm4st3r.recipeapp.commands.CategoryCommand;
import git.w1shm4st3r.recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";
    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    void convert() {
        //GIVEN
        CategoryCommand command = new CategoryCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);

        //WHEN
        Category category = converter.convert(command);

        //THEN
        assertNotNull(category);
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}