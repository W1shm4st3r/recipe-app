package git.w1shm4st3r.recipeapp.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(Long recipeId, MultipartFile file);
}
