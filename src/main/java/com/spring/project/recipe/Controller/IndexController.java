package com.spring.project.recipe.Controller;

import com.spring.project.recipe.Model.Category;
import com.spring.project.recipe.Model.UnitOfMeasure;
import com.spring.project.recipe.Repos.CategoryRepository;
import com.spring.project.recipe.Repos.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class IndexController {

    private UnitOfMeasureRepository unitOfMeasureRepository;
    private CategoryRepository categoryRepository;

    public IndexController(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(){
        Optional<Category> category= categoryRepository.findByDescription("Indian");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByUom("table-spoon");

        System.out.println("Category id: "+category.get().getId());
        System.out.println("Unit of measure id: "+unitOfMeasure.get().getId());
        return "index";
    }
}
