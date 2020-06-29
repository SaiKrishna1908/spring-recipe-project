package com.spring.project.recipe.Controller;

import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.Service.ImageService;
import com.spring.project.recipe.Service.RecipeService;
import com.spring.project.recipe.commands.RecipeCommand;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RequiredArgsConstructor
@Controller
public class ImageController {

    private final RecipeService recipeService;
    private  final ImageService imageService;

    @GetMapping("/recipe/{id}/image")
    public String getImage(@PathVariable String id, Model model){
        RecipeCommand recipeCommand = recipeService.getById(Long.valueOf(id));

        model.addAttribute("recipe", recipeCommand);
        return "recipe/ImageForm";
    }

    @PostMapping("/recipe/{id}/image")
    public String postImage(@PathVariable String id,
                            @RequestParam("imagefile") MultipartFile file){
        imageService.saveImageFile(Long.valueOf(id), file);
        return "redirect:/recipe/"+Long.valueOf(id)+"/show";
    }

    @GetMapping("/recipe/{id}/recipeimage")
    public void renderImage(@PathVariable String id, HttpServletResponse response) throws Exception{
        RecipeCommand recipeCommand = recipeService.getById(Long.valueOf(id));

        if(recipeCommand.getImage() != null){

            byte[] bytes = new byte[recipeCommand.getImage().length];

            int i=0;

            for(Byte b : recipeCommand.getImage()){
                bytes[i++] = b;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(bytes);
            IOUtils.copy(is, response.getOutputStream());
        }
    }

}
