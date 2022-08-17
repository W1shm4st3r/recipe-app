package git.w1shm4st3r.recipeapp.services;

import git.w1shm4st3r.recipeapp.domain.Recipe;
import git.w1shm4st3r.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ImageServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    ImageService imageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    void saveImageFile() throws IOException {
        //GIVEN
        Long id = 1L;
        MultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                "Git W1shm4st3r".getBytes());

        Recipe recipe = Recipe.builder().id(id).build();
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        //WHEN
        imageService.saveImageFile(id, multipartFile);

        //THEN
        verify(recipeRepository, times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
    }
}