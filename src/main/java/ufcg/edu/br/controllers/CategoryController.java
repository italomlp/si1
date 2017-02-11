package ufcg.edu.br.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ufcg.edu.br.models.Category;
import ufcg.edu.br.repositories.CategoryRepository;

import java.util.Collection;

/**
 * Created by Italo on 08/02/2017.
 */
@RestController
public class CategoryController {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public Collection<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void saveCategory(Category category) {
        if (category != null) {
            Long id = category.getId();

            if (id == null){
                categoryRepository.save(category);
            } else {
                Category updatedCategory = categoryRepository.findOne(id);
                updatedCategory.setName(category.getName());
                categoryRepository.save(updatedCategory);
            }
        }
    }

    public Category getOneCategory(Long categoryId) {
        return categoryRepository.findOne(categoryId);
    }

    public void removeOneCategory(Long categoryId) { // remover conteudo tambem
        categoryRepository.delete(categoryId);
    }

    public void removeAllCategories() {
        categoryRepository.deleteAll();
    }
}
