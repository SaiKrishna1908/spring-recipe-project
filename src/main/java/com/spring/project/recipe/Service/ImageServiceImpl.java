package com.spring.project.recipe.Service;

import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.Repos.RecipeRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file)  {

        try {
            Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

            if (optionalRecipe.isPresent()) {
                Recipe recipe = optionalRecipe.get();
                Byte[] fileBytes = new Byte[file.getBytes().length];
                int index= 0;

                for(Byte b : file.getBytes()){
                    fileBytes[index++] = b;
                }

                recipe.setImage(fileBytes);

                recipeRepository.save(recipe);
            }
        }
        catch (Exception e){
            log.error("Error occurred",e);
            e.printStackTrace();
        }
    }
}
