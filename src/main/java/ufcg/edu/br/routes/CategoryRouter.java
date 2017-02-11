package ufcg.edu.br.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.edu.br.controllers.CategoryController;
import ufcg.edu.br.models.Category;

import java.util.Collection;

/**
 * Created by Italo on 08/02/2017.
 */
@RestController
@RequestMapping("/categories")
public class CategoryRouter {

    @Autowired
    private CategoryController categoryController;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Category>> getAllCategorys() {
        return new ResponseEntity<Collection<Category>>(categoryController.getAllCategories(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveCategory(@RequestBody Category taskCategory) {
        categoryController.saveCategory(taskCategory);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getOneCategory(@PathVariable Long categoryId) {
        return new ResponseEntity<Category>(categoryController.getOneCategory(categoryId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity removeOneCategory(@PathVariable Long categoryId) {
        categoryController.removeOneCategory(categoryId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity removeAllCategories() {
        categoryController.removeAllCategories();
        return new ResponseEntity(HttpStatus.OK);
    }
}
