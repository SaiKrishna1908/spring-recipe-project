package com.spring.project.recipe.Controller;

import com.spring.project.recipe.Service.ImageService;
import com.spring.project.recipe.Service.RecipeService;
import com.spring.project.recipe.commands.RecipeCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    ImageController imageController;

    MockMvc mockMvc ;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        imageController = new ImageController(recipeService, imageService);
        mockMvc= MockMvcBuilders.standaloneSetup(imageController)
                .setControllerAdvice(new GlobalExceptionController()).build();
    }

    @Test
    void getImage() throws Exception {


        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        //when
        when(recipeService.getById(any())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/image")).andExpect(status().isOk())
                .andExpect(view().name("recipe/ImageForm"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void postImage() throws  Exception{

        //given a multi part file

        MockMultipartFile mockMultipartFile = new MockMultipartFile("imagefile", "testing.txt",
                "text/plain", "SpringFramework ".getBytes());
        mockMvc.perform(multipart("/recipe/1/image").file(mockMultipartFile))
                .andExpect(status().is3xxRedirection()).
                andExpect(header().string("Location", "/recipe/1/show"));
    }


    @Test
    void renderImage() throws Exception {
        //given

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        String test = "Testing for bytes";

        Byte bytes[] = new Byte[test.getBytes().length];

        int i = 0;

        for(Byte b : test.getBytes())
            bytes[i++] = b;

        recipeCommand.setImage(bytes);

        when(recipeService.getById(any())).thenReturn(recipeCommand);

        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
                    .andExpect(status().isOk()).andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();

        assertEquals(test.getBytes().length, responseBytes.length);
    }

    @Test
    void exceptionNumberFormat() throws Exception {
        mockMvc.perform(get("/recipe/a/image")).andExpect(status().isBadRequest())
        .andExpect(view().name("error/400BadRequest"));
    }
}